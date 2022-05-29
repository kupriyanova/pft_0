package ru.stqa.pft.addressbook.tests.contacs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactFieldsTests extends TestBase {

    ContactData contact;
    ContactData contactInfoFromEditForm;

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().gotoHomePage();

        if (app.contact().all().size() == 0)
            app.contact().create(new ContactData()
                    .withFirstname("test1")
                    .withLastname("test2")
                    .withGroup("test1")
                    .withEmail("email1@mail.com")
                    .withEmail2("email2@mail.com")
                    .withEmail3("email3@mail.com")
                    .withHome("+7 (111) 111-11-11")
                    .withMobile("+7 (222) 222-22-22")
                    .withWork("+7 (333) 333-33-33")
                    .withFax("+7 (333) 444-33-33"));

        contact = app.contact().all().iterator().next();
        contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    }

    @Test
    public void testContactEmails() {
        assertThat("Имейлы контакта на главной странице должны совпадать с имейлами " +
                "этого контакта в форме редактирования",
                contact.getAllEmail(), equalTo(
                mergeEmails(contactInfoFromEditForm)));
    }

    @Test
    public void testContactPhones() {
        assertThat("Телефоны контакта на главной странице должны совпадать с телефонами " +
                "этого контакта в форме редактирования",
                contact.getAllPhones(), equalTo(
                mergePhones(contactInfoFromEditForm)));
    }

    /** Убирает лишние символы */
    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "")
                .replaceAll("[-()]", "");
    }

    /** Склеивает все номера телефонов, полученные из формы редактирования */
    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter(s -> !s.equals(""))
                .map(ContactFieldsTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    /** Склеивает все имейлы, полученные из формы редактирования */
    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail1(), contact.getEmail2(), contact.getEmail3())
                .filter(s -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

}
