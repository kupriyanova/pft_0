package ru.stqa.pft.addressbook.tests.groups;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().groupPage();

        Groups before = app.group().all();
        GroupData group = new GroupData()
                .withName("test1")
                .withHeader("test2")
                .withFooter("test3");

        app.group().create(group);
        assertEquals(app.group().count(), before.size()+1);
        Groups after = app.group().all();

        assertThat(after, equalTo(
                before.withAdded(group
                        .withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadGroupCreation() {
        Groups before = app.group().all();
        GroupData group = new GroupData()
                .withName("test1'")
                .withHeader("test2")
                .withFooter("test3");

        app.group().create(group);
        assertEquals(app.group().count(), before.size());

        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
