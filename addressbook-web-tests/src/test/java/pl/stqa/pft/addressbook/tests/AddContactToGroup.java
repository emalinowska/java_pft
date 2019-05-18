package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

public class AddContactToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test 1").withFooter("footer1").withHeader("header1"));
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstName("Eliza").withLastName("Malinowska").
        withHomePhone("123123123").withMobilePhone("221232323").withWorkPhone("229998877").
        withEmail("elgruszczynska@gmail.com").withEmail2("test@testowy.pl").withEmail3("testowy@test.pl"), true);
    }
  }

  @Test
  public void testAddContactToGroup() {
    app.goTo().homePage();
    ContactData contactBefore = app.db().contacts().iterator().next();
    Groups allGroups = app.db().groups();
    Groups contactGroupsBefore = contactBefore.getGroups();
    GroupData group = null;

    if (allGroups.equals(contactGroupsBefore)) {
      int id = allGroups.stream().mapToInt((g) -> g.getId()).max().getAsInt() + 1;
      group = new GroupData().withId(id).withName("test " + id).withHeader("").withFooter("");
      app.goTo().groupPage();
      app.group().create(group);
      app.goTo().homePage();
      app.contact().addToGroup(contactBefore, group);
    } else {
      Groups groups = app.db().groups();

      for (GroupData g : groups) {
        if (!contactGroupsBefore.contains(g)) {
          group = g;
          app.contact().addToGroup(contactBefore, group);
        }
      }
    }
  }
}
