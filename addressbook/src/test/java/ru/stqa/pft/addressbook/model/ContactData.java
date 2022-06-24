package ru.stqa.pft.addressbook.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.lang.Integer.MAX_VALUE;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  private int id = MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstname;

  @Expose
  @Column(name = "lastname")
  private String lastname;

  @Column(name = "home")
  @Type(type = "text")
  private String home;

  @Column(name = "mobile")
  @Type(type = "text")
  @Expose private String mobile;

  @Column(name = "work")
  @Type(type = "text")
  private String work;

  transient private String allPhones;

  @Expose
  @Type(type = "text")
  private String email;
  @Type(type = "text")
  private String email2;
  @Type(type = "text")
  private String email3;
  transient private String allEmail;
  @Expose
  @Type(type = "text")
  private String address;

  @Transient
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"),
          inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();

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
    return email;
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
  public File getPhoto() {
    return new File(photo);
  }
  public Groups getGroups() {
    return new Groups(groups);
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
    this.email = email1;
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
  public ContactData withPhoto(File file) {
    this.photo = file.getPath();
    return this;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", home='" + home + '\'' +
            ", mobile='" + mobile + '\'' +
            ", work='" + work + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", email1='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id && Objects.equals(firstname, that.firstname)
        && Objects.equals(lastname, that.lastname)
        && Objects.equals(home, that.home)
        && Objects.equals(mobile, that.mobile)
        && Objects.equals(work, that.work)
        && Objects.equals(allPhones, that.allPhones)
        && Objects.equals(email, that.email)
        && Objects.equals(email2, that.email2)
        && Objects.equals(email3, that.email3)
        && Objects.equals(photo, that.photo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, home, mobile,
        work, allPhones, email, email2, email3, photo);
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

}
