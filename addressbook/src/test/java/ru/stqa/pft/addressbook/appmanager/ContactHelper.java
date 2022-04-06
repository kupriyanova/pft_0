package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void deleteSelectedContact() {
        click(By.cssSelector("input[onclick='DeleteSel()']"));
    }

    public void openContactDetails(int index) {
        wd.findElements(By.cssSelector("img[title='Details']")).get(index).click();

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

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contactList = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            ContactData contact = new ContactData(id, firstName, lastName, null, null);
            contactList.add(contact);
        }
        return contactList;
    }
}
