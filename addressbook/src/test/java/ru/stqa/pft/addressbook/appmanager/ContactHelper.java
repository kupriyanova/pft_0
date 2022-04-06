package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void initCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());

        if(creation)
            new Select(wd.findElement(By.name("new_group")))
                    .selectByVisibleText(contactData.getGroup());
        else Assert.assertFalse(isElementPresent(By.name("new_group")));

    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }
    public void deleteSelected() {
        click(By.cssSelector("input[onclick='DeleteSel()']"));
    }

    public void openDetailsById(int id) {
        wd.findElement(By.cssSelector("a[href='view.php?id="+id+"']")).click();

    }
    public void modifyOpened() {
        click(By.name("modifiy"));
    }

    public void submitCreation() {
        wd.findElement(By.name("submit")).click();
    }
    public void submitModify() {
        wd.findElement(By.name("update")).click();
    }

    public void create(ContactData contactData) {
        initCreation();
        fillForm(contactData, true);
        submitCreation();
        returnToHomePage();
    }
    public void deleteById(ContactData deletedContact) {
        selectById(deletedContact.getId());
        deleteSelected();
        acceptAlert();
        returnToHomePage();
    }
    public void modify(ContactData contact, ContactData modContact) {
        openDetailsById(contact.getId());
        modifyOpened();
        fillForm(
                modContact, false);
        submitModify();
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    public Contacts all() {
        Contacts contactList = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstName)
                    .withLastname(lastName);
            contactList.add(contact);
        }
        return contactList;
    }
}
