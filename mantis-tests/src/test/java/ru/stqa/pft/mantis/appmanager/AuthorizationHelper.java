package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class AuthorizationHelper extends HelperBase {

  public AuthorizationHelper(ApplicationManager app) {
    super(app);
  }

  public void authorizationAsAdmin() {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), "administrator");
    click(By.xpath("//input[@type='submit']"));
    //wd.get("http://localhost/mantisbt-2.25.7/login_password_page.php");
    type(By.name("password"), "root");
    click(By.xpath("//input[@type='submit']"));
  }

  public void initChangePassword(int id) {
    wd.get(app.getProperty("web.baseUrl") + "/account_page.php");
    click(By.xpath("//a[@href='/mantisbt-2.25.7/manage_overview_page.php']"));
    click(By.xpath("//a[@href='/mantisbt-2.25.7/manage_user_page.php']"));
    click(By.xpath("//a[@href='manage_user_edit_page.php?user_id=" + id + "']"));
    click(By.xpath("//input[@value='Сбросить пароль']"));
  }
}