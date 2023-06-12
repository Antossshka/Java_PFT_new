package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.contact().list().size() == 0) {
      Groups groups = app.db().groups();
      app.contact().create(new ContactData().withFirstname("Dmitry").withLastname("Blinovsky").withMiddlename("Petrov")
              .withMobilePhone("89991112299").withEmail("petrov.db@mail.ru").withAddress("Mowcow").inGroup(groups.iterator().next()));
    }
  }

  @Test(enabled = true)
  public void testContactModification() {
    app.goTo().homePage();
    ensurePreconditions();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withFirstname("Dmitry").withLastname("Blinovsky").withMiddlename("Petrov")
            .withMobilePhone("89991112299").withEmail("petrov.db@mail.ru").withAddress("Mowcow").withId(modifiedContact.getId());
    app.contact().modify(contact);
    assertThat(app().contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();

    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUI(); //В конфигурации запуска -DverifyUI=true
  }


}
