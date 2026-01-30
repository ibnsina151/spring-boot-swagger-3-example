package com.aikoder.spring.swagger.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class Student {

  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private long id = 0;

  private String name;

  private String description;

  private boolean isActive;

  public Student() {

  }

  public Student(String name, String description, boolean isActive) {
    this.name = name;
    this.description = description;
    this.isActive = isActive;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", desc=" + description + ", isActive=" + isActive + "]";
  }

}
