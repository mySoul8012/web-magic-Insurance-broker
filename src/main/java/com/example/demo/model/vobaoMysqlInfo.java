package com.example.demo.model;

public class vobaoMysqlInfo {
  private String name;
  private String phone;
  private String position;
  private String affiliation;
  private String OCN;
  private String localhost;
  private String proYears;
  private String photo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getAffiliation() {
    return affiliation;
  }

  public void setAffiliation(String affiliation) {
    this.affiliation = affiliation;
  }

  public String getOCN() {
    return OCN;
  }

  public void setOCN(String OCN) {
    this.OCN = OCN;
  }

  public String getLocalhost() {
    return localhost;
  }

  public void setLocalhost(String localhost) {
    this.localhost = localhost;
  }

  public String getProYears() {
    return proYears;
  }

  public void setProYears(String proYears) {
    this.proYears = proYears;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  @Override
  public String toString() {
    return "vobaoMysqlInfo{" +
            "name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", position='" + position + '\'' +
            ", affiliation='" + affiliation + '\'' +
            ", OCN='" + OCN + '\'' +
            ", localhost='" + localhost + '\'' +
            ", proYears='" + proYears + '\'' +
            ", photo='" + photo + '\'' +
            '}';
  }
}
