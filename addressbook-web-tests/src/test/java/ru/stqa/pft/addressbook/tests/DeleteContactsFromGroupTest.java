package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactsFromGroupTest extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      app.goTo().homePage();
      app.contact().create(new ContactData().withFirstname("Dmitry").withLastname("Blinovsky").withMiddlename("Petrov")
              .withMobilePhone("89991112299").withEmail("petrov.db@mail.ru").withAddress("Mowcow").inGroup(groups.iterator().next()));
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    if (contactWithGroup(contacts) == null) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testDel() {
    Groups before = app.db().groups();
    GroupData groupBefore = groupWithContact(before);

    Contacts contacts = app.db().contacts();
    ContactData contact = contactWithGroup(contacts);

    app.goTo().homePage();
    app.contact().selectListGroup(groupBefore.getName());
    app.contact().selectContactById(contact.getId());
    app.contact().removeContact();
    app.goTo().homePage();

    Groups after = app.db().groups();
    GroupData groupAfter = after.iterator().next().withName(groupBefore.getName());

    Assert.assertFalse(groupAfter.getContacts().contains(contact));
    assertThat(groupAfter.getContacts().size(), equalTo(groupBefore.getContacts().size() - 1));
  }

  public GroupData groupWithContact(Groups group) {
    Groups groups = app.db().groups();
    List<GroupData> groupsF = groups.stream().filter(g -> g.getContacts().size() != 0).toList();
    if (groupsF.isEmpty()) {
      return null;
    }
    return groupsF.iterator().next();
  }

  public ContactData contactWithGroup(Contacts contact) {
    Contacts contacts = app.db().contacts();
    List<ContactData> contactsF = contacts.stream().filter(c -> c.getGroups().size() != 0).toList();
    if (contactsF.isEmpty()) {
      return null;
    }
    return contactsF.iterator().next();
  }
}
