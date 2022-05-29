package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().gotoHomePage();

    // если нет ни одного контакта
    if (! app.getContactHelper().isThereAContact())
      app.getContactHelper().createContact(
              new ContactData("test1", "test2", "test3", "test1"));

    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlert();
  }
}
