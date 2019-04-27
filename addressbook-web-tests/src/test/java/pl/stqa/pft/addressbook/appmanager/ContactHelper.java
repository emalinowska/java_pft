package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitNewContactCreation() {
    click(By.name("submit"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void fillNewContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      if (!contactData.getGroup().isEmpty()) {
        Select groupSelect = new Select(wd.findElement(By.name("new_group")));
        groupSelect.selectByVisibleText(contactData.getGroup());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContacts() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void stopAlert() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillNewContactForm(contact, creation);
    submitNewContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillNewContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    returnToHomePage();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    contactCache = null;
    stopAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache !=null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> entries = wd.findElements(By.name("entry"));
    entries.forEach(entry -> {
      String firstName = entry.findElement(By.cssSelector("td:nth-of-type(3)")).getText().trim();
      String lastName = entry.findElement(By.cssSelector("td:nth-of-type(2)")).getText().trim();
      int id = Integer.parseInt(entry.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
    });

    return new Contacts(contactCache);
  }

}
