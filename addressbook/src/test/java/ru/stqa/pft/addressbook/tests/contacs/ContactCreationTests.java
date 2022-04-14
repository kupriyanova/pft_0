package ru.stqa.pft.addressbook.tests.contacs;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(
                        new File("src/test/resources/contacts.json")))) {
            String line = reader.readLine();
            String json = "";
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<ContactData>>() {
            }.getType();
            List<ContactData> groups = gson.fromJson(json, type);
            return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) {
        Groups groups = app.db().groups();
        app.goTo().gotoHomePage();
        File photo = new File("src/test/resources/stru.png");

        ContactData newContact = new ContactData()
                .withFirstname(contact.getFirstname())
                .withLastname(contact.getLastname())
                .withMobile(contact.getMobilePhone())
                .withAddress(contact.getAddress())
                .withEmail(contact.getEmail())
                .withPhoto(photo)
                .inGroup(groups.iterator().next());


        Contacts before = app.db().contacts();

        app.contact().create(newContact);
        assertEquals(app.contact().count(), before.size()+1);
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(
                before.withAdded(newContact
                        .withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }

    @Test
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/stru.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}
