package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

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
  public void testContactDeletion() {
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();

    app.contact().deleteById(deletedContact);
    Contacts after = app.contact().all();

    assertEquals(after.size(), before.size()-1);
    assertThat(after, equalTo(before.withOut(deletedContact)));
  }

}