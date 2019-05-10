package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/pobrane.jpg");
    ContactData contact = new ContactData().withFirstName("Eliza").withLastName("Malinowska").
      withHomePhone("123123123").withMobilePhone("221232323").withWorkPhone("229998877").
      withEmail("elgruszczynska@gmail.com").withEmail2("test@testowy.pl").withEmail3("testowy@test.pl").
      withGroup("[none]").withPhoto(photo);
    app.contact().create(contact, true);
    assertEquals(app.contact().count(), before.size() + 1);
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
      before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
