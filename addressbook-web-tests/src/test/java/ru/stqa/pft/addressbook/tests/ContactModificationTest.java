package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

  @Test
  public void testContactModification() {
    app.navigationHelper().gotoHomePage();
    if (! app.contactHelper().isThereAContact()) {
      app.contactHelper().createContact(new ContactData("Dmitry", "Blinovsky", "Petrov", "89991112299", "petrov.db@mail.ru", "Moscow", "test1"), true);
    }
    app.contactHelper().selectContact();
    app.contactHelper().initContactModification();
    app.contactHelper().fillContactForm(new ContactData("Dmitry1", "Blinovsky2", "Petrov3", "89991112299", "petrov.db@mail.ru", "Mowcow", null), false);
    app.contactHelper().submitContactModification();
    app.contactHelper().returnToHomePage();
  }
}
