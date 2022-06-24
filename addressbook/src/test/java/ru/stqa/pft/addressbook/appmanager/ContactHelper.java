package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
//        attach(By.name("photo"), contactData.getPhoto());

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
