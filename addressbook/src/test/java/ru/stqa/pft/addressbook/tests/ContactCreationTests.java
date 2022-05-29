package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

    ContactData newContact = new ContactData(
            "test1", "test2", "test3", "test2");

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoGroupPage();

        // если нет группы с нужным наванием, то создаем
        if (! app.getGroupHelper().isThereAGroupWithName(newContact.getGroup()))
            app.getGroupHelper().createGroup(
                    new GroupData(newContact.getGroup(), "test2", "test3"));

        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().createContact(newContact);
    }

}
