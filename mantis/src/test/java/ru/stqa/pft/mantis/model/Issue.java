package ru.stqa.pft.mantis.model;

import java.math.BigInteger;

public class Issue {

  private BigInteger id;
  private String summary;
  private String description;
  private Project project;

  public BigInteger getId() {
    return id;
  }

  public Issue setId(BigInteger id) {
    this.id = id;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public Issue setSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue setDescription(String description) {
    this.description = description;
    return this;
  }

  public Project getProject() {
    return project;
  }

  public Issue setProject(Project project) {
    this.project = project;
    return this;
  }
}
