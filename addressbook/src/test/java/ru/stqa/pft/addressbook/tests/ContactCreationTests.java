package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> before = app.getContactHelper().getContactList();

        ContactData newContact = new ContactData("test1", "test2", "test3", "test1");
        app.getContactHelper().createContact(newContact);

        List<ContactData>  after = app.getContactHelper().getContactList();
        before.add(newContact);
        assertEquals(after.size(), before.size());

        before.sort(Comparator.comparingInt(ContactData::getId));
        after.sort(Comparator.comparingInt(ContactData::getId));

        assertEquals(before, after);
    }

}
