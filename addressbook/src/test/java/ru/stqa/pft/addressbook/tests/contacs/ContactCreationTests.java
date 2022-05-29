package ru.stqa.pft.addressbook.tests.contacs;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

        ContactData newContact = new ContactData()
                .withFirstname("test1")
                .withLastname("test2")
                .withGroup("test1")
                .withEmail("email1@mail.com")
                .withEmail2("email2@mail.com")
                .withEmail3("email3@mail.com")
                .withHome("+7 (111) 111-11-11")
                .withMobile("+7 (222) 222-22-22")
                .withWork("+7 (333) 333-33-33")
                .withFax("+7 (333) 444-33-33");


    @Test
    public void testContactCreation() {
        app.goTo().groupPage();
        /** если нет группы с нужным наванием, то создаем */
        if (! app.group().isThereAGroupWithName(newContact.getGroup()))
            app.group().create(
                    new GroupData().withName(newContact.getGroup())
                            .withHeader("test2")
                            .withFooter("test3"));

        app.goTo().gotoHomePage();

        Contacts before = app.contact().all();

        app.contact().create(newContact);
        assertEquals(app.contact().count(), before.size()+1);
        Contacts after = app.contact().all();

        assertThat(after, equalTo(
                before.withAdded(newContact
                        .withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }

}
