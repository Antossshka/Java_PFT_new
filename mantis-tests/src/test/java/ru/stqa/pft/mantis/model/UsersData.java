package ru.stqa.pft.mantis.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UsersData {
  @Id
  @Column(name = "id")
  private int id;
  @Column(name = "username")
  private String name;
  @Column(name = "email")
  private String email;

  public UsersData(int id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public UsersData() {
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public UsersData withId(int id) {
    this.id = id;
    return this;
  }

  public UsersData withName(String name) {
    this.name = name;
    return this;
  }

  public UsersData withEmail(String email) {
    this.email = email;
    return this;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UsersData usersData = (UsersData) o;
    return id == usersData.id && Objects.equals(name, usersData.name) && Objects.equals(email, usersData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email);
  }
}