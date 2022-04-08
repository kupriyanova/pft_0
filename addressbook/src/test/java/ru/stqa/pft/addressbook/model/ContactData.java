package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

import static java.lang.Integer.MAX_VALUE;

public class ContactData {
  private int id = MAX_VALUE;
  @Expose private String firstname;
  @Expose private String lastname;
  private String group;
  private String home;
  @Expose private String mobile;
  private String work;
  private String allPhones;
  @Expose private String email1;
  private String email2;
  private String email3;
  private String allEmail;
  @Expose private String address;
  private File photo;

  public ContactData() {
  }

  public int getId() {
    return id;
  }
  public String getFirstname() {
    return firstname;
  }
  public String getLastname() {
    return lastname;
  }
  public String getGroup() {
    return group;
  }
  public String getAllPhones() {
    return allPhones;
  }
  public String getMobilePhone() {
    return mobile;
  }
  public String getHomePhone() {
    return home;
  }
  public String getWorkPhone() {
    return work;
  }
  public String getEmail() {
    return email1;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }
  public String getAllEmail() {
    return allEmail;
  }
  public String getAddress() {
    return address;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }
  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
}
  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }
  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }
  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }
  public ContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }
  public ContactData withHome(String home) {
    this.home = home;
    return this;
  }
  public ContactData withWork(String work) {
    this.work = work;
    return this;
  }
  public ContactData withEmail(String email1) {
    this.email1 = email1;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactData withAllEmail(String allEmail) {
    this.allEmail = allEmail;
    return this;
  }
  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", group='" + group + '\'' +
            ", home='" + home + '\'' +
            ", mobile='" + mobile + '\'' +
            ", work='" + work + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", email1='" + email1 + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allEmail='" + allEmail + '\'' +
            ", address='" + address + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }

  public File getPhoto() {
    return photo;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }
}
