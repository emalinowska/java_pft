package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {

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
  public void testDeleteContactFromGroup() {
    app.goTo().homePage();
    ContactData contactBefore = app.db().contacts().iterator().next();
    Groups allGroups = app.db().groups();
    Groups contactGroupsBefore = contactBefore.getGroups();
    GroupData group = null;

    if (contactGroupsBefore.size() == 0) {
      group = allGroups.iterator().next();
      app.contact().addToGroup(contactBefore, group);
    } else {
      group = contactGroupsBefore.iterator().next();
    }

    app.contact().deleteFromGroup(contactBefore, group);
    ContactData contactAfter = app.db().contactById(contactBefore.getId());
    Groups contactGroupsAfter = contactAfter.getGroups();
    assertThat(contactGroupsAfter, equalTo(contactGroupsBefore.without(group)));
  }
}

