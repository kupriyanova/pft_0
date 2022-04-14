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

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = element.findElements(By.tagName("td")).get(1).getText();
            String firstName = element.findElements(By.tagName("td")).get(2).getText();
            String address = element.findElements(By.tagName("td")).get(3).getText();
            String allEmail = element.findElements(By.tagName("td")).get(4).getText();
            String allPhones = element.findElements(By.tagName("td")).get(5).getText();
            contactCache.add(new ContactData().withId(id)
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withAddress(address)
                    .withAllEmail(allEmail)
                    .withAllPhones(allPhones));
        }
        return new Contacts(contactCache);
    }

    public void initCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void fillForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address"), contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto());

        if(creation) {
            if(contactData.getGroups().size() > 0) {
                Assert.assertEquals(contactData.getGroups().size(), 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else
            Assert.assertFalse(isElementPresent(By.name("new_group")));

    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
    }
    public void deleteSelected() {
        click(By.cssSelector("input[onclick='DeleteSel()']"));
    }

    public void initEditById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();

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
        contactCache = null;
        returnToHomePage();
    }
    public void deleteById(ContactData deletedContact) {
        selectById(deletedContact.getId());
        deleteSelected();
        acceptAlert();
        contactCache = null;
        returnToHomePage();
    }
    public void modify(ContactData contact, ContactData modContact) {
        initEditById(contact.getId());
        fillForm(modContact, false);
        submitModify();
        contactCache = null;
        returnToHomePage();
    }

    private void returnToHomePage() {
        click(By.linkText("home"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initEditById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withFirstname(firstName)
                .withLastname(lastName)
                .withHome(home)
                .withMobile(mobile)
                .withWork(work)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withAddress(address);
    }

    public void addToGroup(int id) {
        new Select(wd.findElement(By.name("to_group"))).selectByValue(String.valueOf(id));
        wd.findElement(By.name("add")).click();
        returnToHomePage();
    }

    public void filterContactsByGroup(int id) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(id));
    }

    public void removeFromGroup() {
        wd.findElement(By.name("remove")).click();
        returnToHomePage();
    }
}
