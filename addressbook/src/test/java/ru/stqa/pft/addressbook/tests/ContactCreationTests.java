package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static ru.stqa.pft.addressbook.appmanager.GroupHelper.createGroup;
import static ru.stqa.pft.addressbook.appmanager.GroupHelper.isThereAGroupWithName;
import static ru.stqa.pft.addressbook.appmanager.NavigationHelper.gotoGroupPage;
import static ru.stqa.pft.addressbook.appmanager.NavigationHelper.gotoHomePage;

public class ContactCreationTests extends TestBase {

    ContactData newContact = new ContactData(
            "test1", "test2", "test3", "test2");

    @Test
    public void testContactCreation() {
        // если нет группы с нужным наванием, то создаем
        gotoGroupPage();
        if (! isThereAGroupWithName(newContact.getGroup()))
            createGroup(new GroupData(newContact.getGroup(), "test2", "test3"));

        gotoHomePage();
        app.getContactHelper().createContact(newContact);
    }

}
