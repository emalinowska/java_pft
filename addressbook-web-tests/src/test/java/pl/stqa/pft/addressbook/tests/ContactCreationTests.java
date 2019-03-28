package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillNewContactForm(new ContactData("Eliza", "Malinowska", "123456789", "elgruszczynska@gmail.com"));
    app.getContactHelper().submitNewContactCreation();
    app.getContactHelper().returnToHomePage();
  }

}
