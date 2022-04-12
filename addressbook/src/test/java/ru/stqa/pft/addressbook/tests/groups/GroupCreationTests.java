package ru.stqa.pft.addressbook.tests.groups;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.*;
import java.lang.reflect.Type;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {


    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/groups.xml")))) {
            String line = reader.readLine();
            String xml = "";
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
            return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }
    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(
                        new File("src/test/resources/groups.json")))) {
            String line = reader.readLine();
            String json = "";
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            Type type = new TypeToken<List<GroupData>>() {
            }.getType();
            List<GroupData> groups = gson.fromJson(json, type);
            return groups.stream().map(g -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group) {
        Groups before = app.db().groups();

        app.goTo().groupPage();
        app.group().create(group);
        assertEquals(app.group().count(), before.size()+1);
        Groups after = app.db().groups();

        assertThat(after, equalTo(
                before.withAdded(group
                        .withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        Groups before = app.db().groups();
        GroupData group = new GroupData()
                .withName("test1'")
                .withHeader("test2")
                .withFooter("test3");

        app.goTo().groupPage();
        app.group().create(group);
        assertEquals(app.group().count(), before.size());

        Groups after = app.db().groups();
        assertThat(after, equalTo(before));
    }
}
