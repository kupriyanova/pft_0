package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedGroup() {
        click(By.name("delete"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    /** Создает группу с переданными в параметре данными */
    public static void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
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
