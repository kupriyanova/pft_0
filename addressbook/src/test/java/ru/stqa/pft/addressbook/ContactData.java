package ru.stqa.pft.addressbook;

public class ContactData {
  private String firstname;
  private String lastname;
  private String mobile;


  public ContactData(String firstname, String lastname, String mobile) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobile = mobile;
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
}
