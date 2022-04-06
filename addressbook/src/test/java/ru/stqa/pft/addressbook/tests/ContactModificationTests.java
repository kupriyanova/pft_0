package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().all().size() == 0)
      app.contact().create(new ContactData()
              .withFirstname("test1")
              .withLastname("test2")
              .withMobile("test3")
              .withGroup("test1"));
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData contact = before.iterator().next();
    ContactData modContact = new ContactData()
            .withId(contact.getId())
            .withFirstname("mod_test1")
            .withLastname("mod_test2")
            .withMobile("mod_test3");

    app.contact().modify(contact, modContact);

    Contacts after = app.contact().all();

    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withOut(contact).withAdded(modContact)));
  }


}
