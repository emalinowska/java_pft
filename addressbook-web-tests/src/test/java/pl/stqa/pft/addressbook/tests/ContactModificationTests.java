package pl.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Eliza").withLastName("Malinowska").
        withHomePhone("123123123").withMobilePhone("221232323").withWorkPhone("229998877").
        withEmail("elgruszczynska@gmail.com").withEmail2("test@testowy.pl").withEmail3("testowy@test.pl"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Eliza").withLastName("Malinowska")
      .withHomePhone("123123123").withMobilePhone("221232323").withWorkPhone("229998877").
        withEmail("elgruszczynska@gmail.com").withEmail2("test@testowy.pl").withEmail3("testowy@test.pl");
    app.contact().modify(contact);
    assertThat(app.contact().count(), CoreMatchers.equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
