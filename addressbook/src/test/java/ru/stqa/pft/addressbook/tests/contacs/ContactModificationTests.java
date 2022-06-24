package ru.stqa.pft.addressbook.tests.contacs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                .withFirstname("mod_test1")
                .withLastname("mod_test2")
                .withMobile("mod_test3")
                .withHome("home")
                .withWork("work")
                .withEmail("email1@mail.com")
                .withEmail2("email2@mail.com")
                .withEmail3("email3@mail.com"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData contact = before.iterator().next();
        ContactData modContact = new ContactData()
                .withId(contact.getId())
                .withFirstname("mod_test1")
                .withLastname("mod_test2")
                .withMobile("mod_test3")
                .withHome("home")
                .withWork("work")
                .withEmail("email1@mail.com")
                .withEmail2("email2@mail.com")
                .withEmail3("email3@mail.com");

        app.contact().modify(contact, modContact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withOut(contact).withAdded(modContact)));
        verifyGContactListUI();
    }
}
