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
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withFirstname("test1")
                    .withLastname("test2")
                    .withMobile("test3"));
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
                .withMobile("mod_test3");

        app.contact().modify(contact, modContact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withOut(contact).withAdded(modContact)));
        verifyGContactListUI();
    }
}
