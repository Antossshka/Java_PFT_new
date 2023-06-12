package ru.stqa.pft.mantis.tests;

import org.apache.hc.core5.http.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;


public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException, ParseException {
    HttpSession session = app.newSession();
    Assert.assertTrue(session.login("administrator", "root"));
    Assert.assertTrue(session.isLoggedInAs("administrator"));
  }

}