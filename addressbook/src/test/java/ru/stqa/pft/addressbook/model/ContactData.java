package ru.stqa.pft.addressbook.model;

import java.util.Objects;

import static java.lang.Integer.MAX_VALUE;

public class ContactData {
  private int id;
  private String firstname;
  private String lastname;
  private String mobile;
  private String group;


  public ContactData(String firstname, String lastname, String mobile, String group) {
    this.id = MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobile = mobile;
    this.group = group;
  }

  public ContactData(int id, String firstname, String lastname, String mobile, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobile = mobile;
    this.group = group;
  }
  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMobile() {
    return mobile;
  }

  public String getGroup() {
    return group;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", mobile='" + mobile + '\'' +
            ", group='" + group + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }
}
