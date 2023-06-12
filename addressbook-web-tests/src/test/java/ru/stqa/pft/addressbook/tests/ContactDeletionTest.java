package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {

  @Test
  public void testContactDeletion() throws Exception {
    app.navigationHelper().gotoHomePage();
    if (! app.contactHelper().isThereAContact()) {
      app.contactHelper().createContact(new ContactData("Dmitry", "Blinovsky", "Petrov", "89991112299", "petrov.db@mail.ru", "Moscow", "test1"), true);
    }
    app.contactHelper().selectContact();
    app.contactHelper().deleteSelectedContacts();
    app.contactHelper().confirmDeletionContacts();
    app.contactHelper().returnToHomePage();
  }

}
