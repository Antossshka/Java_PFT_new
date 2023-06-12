package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("unchecked")
public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.xml"))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xStream = new XStream();
      xStream.processAnnotations(ContactData.class);
      xStream.allowTypes(new Class[]{ContactData.class});
      List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
      return contacts.stream().map(g -> new Object[]{g}).toList().iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.json"))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map(c -> new Object[]{c}).toList().iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson", enabled = true)
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    File photo = new File("src/test/resources/photo.PNG");
    app.contact().create(contact.withPhoto(photo));
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after,
            equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/photo.PNG");
    ContactData newContact = new ContactData().withFirstname("Dmitry'").withLastname("Blinovsky").withMiddlename("Petrov")
            .withMobilePhone("89991112299").withEmail("petrov.db@mail.ru").withAddress("Mowcow").withPhoto(photo)
            .inGroup(groups.iterator().next());
    app.goTo().homePage();
    Contacts before = app.contact().all();
    app.contact().create(newContact);
    assertThat(app().contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/photo.PNG");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
    verifyContactListInUI(); //В конфигурации запуска -DverifyUI=true
  }
}


