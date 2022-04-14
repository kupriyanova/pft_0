package ru.stqa.pft.addressbook.tests.contacs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactEmailTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().gotoHomePage();
            app.contact().create(new ContactData()
                    .withFirstname("test1")
                    .withLastname("test2")
//                    .withGroup("test1")
                    .withAddress("address1")
                    .withEmail2("email2@mail.com")
                    .withEmail3("email3@mail.com"));
        }
    }

    @Test
    public void testContactPhone() {
        ContactData contact = app.db().contacts().iterator().next();
        app.goTo().gotoHomePage();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact, equalTo(contactInfoFromEditForm));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

}
