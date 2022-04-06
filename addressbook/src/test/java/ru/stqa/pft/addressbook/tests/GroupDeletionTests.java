package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup())
            app.getGroupHelper().createGroup(
                    new GroupData("test1", "test2", "test3"));

        List<GroupData> before = app.getGroupHelper().getGroupList();

        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteSelectedGroup();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();

        assertEquals(after.size(), before.size() - 1);
        before.remove(before.size() - 1);

        before.sort(Comparator.comparingInt(GroupData::getId));
        after.sort(Comparator.comparingInt(GroupData::getId));

        assertEquals(before, after);
    }

}
