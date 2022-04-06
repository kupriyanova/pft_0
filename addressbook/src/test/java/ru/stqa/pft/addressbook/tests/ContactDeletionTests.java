package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact())
      app.getContactHelper().createContact(
              new ContactData("test1", "test2", "test3", "test1"));

    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlert();
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();

    before.remove(before.size() - 1);
    assertEquals(after.size(), before.size());

    before.sort(Comparator.comparingInt(ContactData::getId));
    after.sort(Comparator.comparingInt(ContactData::getId));

    assertEquals(before, after);
  }
}
