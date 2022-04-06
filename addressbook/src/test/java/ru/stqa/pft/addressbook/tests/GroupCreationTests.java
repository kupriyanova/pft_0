package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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

    Groups after = app.group().all();

    assertEquals(after.size(), before.size() + 1);
    assertThat(after, equalTo(
            before.withAdded(group
                    .withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}