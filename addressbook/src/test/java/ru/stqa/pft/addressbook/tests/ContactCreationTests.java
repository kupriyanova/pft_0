package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        app.goTo().gotoHomePage();
        Contacts before = app.contact().all();

        ContactData newContact = new ContactData()
                .withFirstname("test1")
                .withLastname("test2")
                .withMobile("test3")
                .withGroup("test1");
        app.contact().create(newContact);

        Contacts after = app.contact().all();

        assertEquals(after.size(), before.size()+1);
        assertThat(after, equalTo(
                before.withAdded(newContact
                        .withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}
