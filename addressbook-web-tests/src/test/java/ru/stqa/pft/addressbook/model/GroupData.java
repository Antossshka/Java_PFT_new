package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@XStreamAlias("group")
@Entity
@Table(name="group_list")
public class GroupData {
  @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
  private final Set<ContactData> contacts = new HashSet<>();
  @Expose
  @Column(name="group_name")
  public String name;
  @Expose
  @Column(name="group_header")
  @Type(type="text")
  public String header;
  @Expose
  @Column(name="group_footer")
  @Type(type="text")
  public String footer;

  @XStreamOmitField
  @Id
  @Column(name="group_id")
  private int id = Integer.MAX_VALUE;

  public Set<ContactData> getContacts() {
    return new Contacts(contacts);
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }

  public int getId() {
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public GroupData withName(String name) {
    this.name = name;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    if (id != groupData.id) return false;
    if (!Objects.equals(name, groupData.name)) return false;
    if (!Objects.equals(header, groupData.header)) return false;
    return Objects.equals(footer, groupData.footer);
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "name='" + name + '\'' +
            ", header='" + header + '\'' +
            ", footer='" + footer + '\'' +
            ", id=" + id +
            '}';
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (header != null ? header.hashCode() : 0);
    result = 31 * result + (footer != null ? footer.hashCode() : 0);
    result = 31 * result + id;
    return result;
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }
}