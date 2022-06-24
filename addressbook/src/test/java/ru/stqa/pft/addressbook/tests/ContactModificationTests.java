package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactModificationTests extends TestBase {


  @Test
  public void testContactModification() {
    app.getContactHelper().checkPrecondition();

    app.getContactHelper().openContactDetails();
    app.getContactHelper().modifyOpenedContact();
    app.getContactHelper().fillContactForm(
            new ContactData("modify", "modify2", "modify3", null),
            false);
    app.getContactHelper().submitContactModify();
  }
}
