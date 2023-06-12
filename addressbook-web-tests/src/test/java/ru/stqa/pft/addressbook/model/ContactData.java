package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {
  @Expose
  @Column(name = "firstname")
  public String firstname;
  @Expose
  public String middlename;
  @Expose
  @Column(name = "lastname")
  public String lastname;
  @Expose
  @Type(type = "text")
  public String email;
  @Expose
  @Type(type = "text")
  public String address;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Column(name = "home")
  @Type(type = "text")
  public String homePhone;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  public String mobilePhone;
  @Column(name = "work")
  @Type(type = "text")
  public String workPhone;
  @Transient
  public String allPhones;
  @Transient
  private String allEmails;
  @Type(type = "text")
  private String email2;
  @Type(type = "text")
  private String email3;
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }



  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }
  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withAllEmail(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            ", email='" + email + '\'' +
            ", address='" + address + '\'' +
            ", id=" + id +
            ", mobilePhone='" + mobilePhone + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (!Objects.equals(firstname, that.firstname)) return false;
    if (!Objects.equals(middlename, that.middlename)) return false;
    if (!Objects.equals(lastname, that.lastname)) return false;
    if (!Objects.equals(email, that.email)) return false;
    if (!Objects.equals(address, that.address)) return false;
    return Objects.equals(mobilePhone, that.mobilePhone);
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + id;
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    return result;
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public void setID(int id) {
    this.id = id;
  }


  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}