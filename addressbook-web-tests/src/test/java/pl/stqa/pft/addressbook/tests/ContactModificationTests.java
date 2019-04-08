package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().goToHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Eliza", "Malinowska", "123456789", "elgruszczynska@gmail.com", "test1"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillNewContactForm(new ContactData("Eliza", "Malinowska", "123456789", "elgruszczynska@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
  }

}
