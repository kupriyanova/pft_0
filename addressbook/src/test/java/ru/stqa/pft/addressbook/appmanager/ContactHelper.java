package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
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
}
