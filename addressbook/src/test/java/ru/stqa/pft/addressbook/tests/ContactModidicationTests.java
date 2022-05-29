package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModidicationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();

    // если нет ни одного контакта
    if (! app.getContactHelper().isThereAContact())
      app.getContactHelper().createContact(
              new ContactData("test1", "test2", "test3", "test1"));

    app.getContactHelper().openContactDetails();
    app.getContactHelper().modifyOpenedContact();
    app.getContactHelper().fillContactForm(
            new ContactData("modify", "modify2", "modify3", null),
            false);
    app.getContactHelper().submitContactModify();
  }
}
