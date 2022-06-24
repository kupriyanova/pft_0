package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public static void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public static void submitGroupCreation() {
        click(By.name("submit"));
    }

    public static void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public static void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelected() {
        click(By.name("delete"));
    }

    public void initModification() {
        click(By.name("edit"));
    }

    public void submitModification() {
        click(By.name("update"));
    }

    /** Создает группу с переданными в параметре данными */
    public static void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupPage();
    }
    public void modify(GroupData group) {
        selectGroupById(group.getId());
        initModification();
        fillForm(group);
        submitModification();
        groupCache = null;
        returnToGroupPage();
    }
    public void delete(GroupData group) {
        selectGroupById(group.getId());
        deleteSelected();
        groupCache = null;
        returnToGroupPage();
    }

    /** Проверяет есть ли на странице хотя бы одна группа */
    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    /** Проверяет есть ли на странице группа с названием переданным в параметре */
    public static boolean isThereAGroupWithName(String groupName) {
         Boolean result = isElementPresent(By.cssSelector(
                "input[title='Select ("+groupName+")']"));
        return result;
    }
}
