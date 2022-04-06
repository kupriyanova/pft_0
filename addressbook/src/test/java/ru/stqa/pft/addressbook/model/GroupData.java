package ru.stqa.pft.addressbook.model;

import java.util.Objects;

import static java.lang.Integer.MAX_VALUE;

public class GroupData {

  private int id = MAX_VALUE;
  private String name;
  private String header;
  private String footer;

  public GroupData() {
  }

  public int getId() {
    return id;
  }

  public GroupData withId(int id) {
    this.id = id;
    return this;
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
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", header='" + header + '\'' +
            ", footer='" + footer + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id && Objects.equals(name, groupData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
