package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      Groups groups = app.db().groups();
      app.contact().create(new ContactData().withFirstname("Dmitry").withLastname("Blinovsky").withMiddlename("Petrov")
              .withMobilePhone("89991112299").withEmail("petrov.db@mail.ru").withAddress("Mowcow"));
    }
    if (app.db().groups().size() == 0) {
      app().goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    Groups groups = app.db().groups();
    if (groupWithoutContact(groups) == null) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testAddContactInGroup() {
    Contacts before = app.db().contacts();
    ContactData contactBefore = before.iterator().next();

    Groups groups = app.db().groups();
    GroupData group = groupWithoutContact(groups);

    app.goTo().homePage();
    app.contact().selectContactById(contactBefore.getId());
    app.contact().selectGroupByName(group.getName());
    app.contact().addInGroup();
    app.goTo().homePage();

    ContactData contactAfter = app.db().contacts().iterator().next().withId(contactBefore.getId());

    Assert.assertTrue(contactAfter.getGroups().contains(group));
    assertThat(contactAfter.getGroups().size(), equalTo(contactBefore.getGroups().size() + 1));
  }

  public GroupData groupWithoutContact(Groups group) {
    Groups groups = app.db().groups();
    List<GroupData> groupsF = groups.stream().filter(g -> g.getContacts().size() == 0).collect(Collectors.toList());
    if (groupsF.isEmpty()) {
      return null;
    }
    return groupsF.iterator().next();
  }
}
