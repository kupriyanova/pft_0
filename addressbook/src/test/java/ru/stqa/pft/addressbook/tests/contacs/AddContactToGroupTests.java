package ru.stqa.pft.addressbook.tests.contacs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class AddContactToGroupTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withFirstname("forAddingToGroup")
                    .withLastname("test2")
                    .withMobile("test3"));
        }
    }

    @Test
    public void testAddContactToGroup() {
        // получаем списоки котактов и групп
        Contacts dbContacts = app.db().contacts();
        Groups groups = app.db().groups();

        // выбираем произвольный контакт
        ContactData contact = dbContacts.iterator().next();
        Groups groupsFromContact = contact.getGroups();
        // проверяем, что у контакта добавлены не все существующие группы
        if (groupsFromContact.size() == groups.size()) {
            app.group().create(new GroupData().withName("group 12345")); // создаем новую
            groups = app.db().groups(); // обновляем список групп
        }

        // добавляем котакту группу которой еще нет
        app.contact().selectById(contact.getId());

        // фильтруем группы которых нет у контакта
        Set<GroupData> groupsNotContainContact = groups.stream().filter(
                g -> !groupsFromContact.contains(g)).collect(Collectors.toSet());
        GroupData groupToAdd = groupsNotContainContact.iterator().next();
        app.contact().addToGroup(groupToAdd.getId());

        // получаем список групп контакта после добавления
        Groups groupsFromContactAfter = app.db().contactWithId(contact.getId()).getGroups();

        // сравниваем списки групп у контакта, до и после
        assertEquals(groupsFromContactAfter, groupsFromContact.withAdded(groupToAdd),
                "Список групп у контакта не соответствует");
    }
}
