package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Eliza").withLastName("Malinowska").
        withHomePhone("123123123").withMobilePhone("221232323").withWorkPhone("229998877").
        withEmail("elgruszczynska@gmail.com").withEmail2("test@testowy.pl").withEmail3("testowy@test.pl"), true);
    }
  }

  @Test
  public void testContactDetails() {
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    String contactInfoFromDetailsForm = app.contact().infoFromDetailsForm(contact);

    assertThat(mergeEditData(contactInfoFromEditForm), equalTo(cleanDetailsData(contactInfoFromDetailsForm)));
  }

  private String mergeEditData(ContactData contact) {
    return Arrays.asList(contact.getFirstName(), contact.getLastName(),
      contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
      contact.getEmail(), contact.getEmail2(), contact.getEmail3())
      .stream().filter((s) -> !s.equals(""))
      .map(ContactDetailsTests::cleanedEditData)
      .collect(Collectors.joining(""));
  }

  public static String cleanedEditData(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

  public static String cleanDetailsData(String phone) {
    return phone.replaceAll("\\s", "")
      .replaceAll("\n", "")
      .replaceAll("[-()]", "")
      .replaceAll("H:", "")
      .replaceAll("M:", "")
      .replaceAll("W:", "");
  }
}

