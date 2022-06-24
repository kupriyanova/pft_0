package ru.stqa.pft.addressbook.tests.contacs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.time.Instant;

import static org.testng.Assert.assertEquals;

public class DeleteContactFromGroupTests extends TestBase {

    private Groups groupsFromContact;

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData groupData = new GroupData().withName("group" + Instant.now()).withHeader("Group header").withFooter("Group footer");
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(groupData); // создаем новую
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("forAddingToGroup")
                    .withLastname("test2")
                    .withMobile("test3")
                .inGroup(groupData));
        }

        app.goTo().homePage();
    }

    @Test
    public void testDeleteContactFromGroup() {
        // получаем списоки котактов и групп
        Contacts dbContacts = app.db().contacts();
        Groups groups = app.db().groups();

        // выбираем произвольный контакт
        ContactData contact = dbContacts.iterator().next();
        groupsFromContact = contact.getGroups();
        // проверяем, что у контакта есть хотя бы одна группа
        if (groupsFromContact.size() == 0) { //если нет, добавляем
            app.contact().selectById(contact.getId());
            app.contact().addToGroup(groups.iterator().next().getId()); // добавляем в любую группу
            groupsFromContact = app.db().contacts().stream()
                .filter(c -> c.getId() == contact.getId()).iterator().next()
                .getGroups(); // обновляем список
        }

        // Выбираем группу и удаляем из неё контакт
        GroupData groupForDelete = groupsFromContact.iterator().next();
        app.contact().filterContactsByGroup(groupForDelete.getId());
        app.contact().selectById(contact.getId());
        app.contact().removeFromGroup();

        // получаем список групп после удаления
        Groups groupsFromContactAfter = app.db().contactWithId(contact.getId()).getGroups();
        // сравниваем списки групп у контакта, до и после
        assertEquals(groupsFromContactAfter, groupsFromContact.withOut(groupForDelete),
                "Список групп у контакта не соответствует");
    }
}
