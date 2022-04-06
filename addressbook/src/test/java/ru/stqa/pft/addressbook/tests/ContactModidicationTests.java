package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactModidicationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact())
      app.getContactHelper().createContact(
              new ContactData("test1", "test2", "test3", "test1"));

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().openContactDetails(before.size() - 1);
    app.getContactHelper().modifyOpenedContact();
    ContactData modContact = new ContactData("modify", "modify2", "modify3", null);
    app.getContactHelper().fillContactForm(
            modContact, false);
    app.getContactHelper().submitContactModify();
    app.getNavigationHelper().returnToHomePage();

    List<ContactData>  after = app.getContactHelper().getContactList();

    before.remove(before.size() - 1);
    before.add(modContact);
    assertEquals(after.size(), before.size());

    before.sort(Comparator.comparingInt(ContactData::getId));
    after.sort(Comparator.comparingInt(ContactData::getId));

    assertEquals(before, after);
  }
}
