package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static ru.stqa.pft.addressbook.appmanager.GroupHelper.createGroup;
import static ru.stqa.pft.addressbook.appmanager.GroupHelper.isThereAGroupWithName;
import static ru.stqa.pft.addressbook.appmanager.NavigationHelper.gotoGroupPage;
import static ru.stqa.pft.addressbook.appmanager.NavigationHelper.gotoHomePage;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());

        if(creation)
            new Select(wd.findElement(By.name("new_group")))
                    .selectByVisibleText(contactData.getGroup());
        else Assert.assertFalse(isElementPresent(By.name("new_group")));

    }

    public void selectContact() {
        click(By.name("selected[]"));
    }
    public void deleteSelectedContact() {
        click(By.cssSelector("input[onclick='DeleteSel()']"));
    }

    public void openContactDetails() {
        click(By.cssSelector("img[title='Details']"));
    }
    public void modifyOpenedContact() {
        click(By.name("modifiy"));
    }

    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }
    public void submitContactModify() {
        wd.findElement(By.name("update")).click();
    }

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactForm(
                contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void checkPrecondition() {
        ContactData newContact = new ContactData(
            "test1", "test2", "test3", "test1");

        // если нет ни одного контакта
        if (! isThereAContact()) {
            // если нет группы с нужным наванием, то создаем
            gotoGroupPage();
            if (!isThereAGroupWithName(newContact.getGroup()))
                createGroup(new GroupData(newContact.getGroup(), "test2", "test3"));
            gotoHomePage();
            createContact(newContact);
        }
    }
}
