package ru.stqa.pft.mantis.model;

import java.math.BigInteger;

public class Project {

  private BigInteger id;
  private String name;

  public BigInteger getId() {
    return id;
  }

  public Project setId(BigInteger id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Project setName(String name) {
    this.name = name;
    return this;
  }
}
