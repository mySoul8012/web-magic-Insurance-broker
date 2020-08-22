package com.example.demo.model;

public class vobaoMysqlInfo {
  // 姓名
  private String name;
  // 电话
  private String phone;
  // 职位
  private String position;
  // 所属机构
  private String affiliation;
  // 执业证号
  private String OCN;
  // 所在地区
  private String localhost;
  // 从业年限
  private String proYears;
  // 头像
  private String photo;
  // 个人简介
  private String self;

  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }

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
            ", self='" + self + '\'' +
            '}';
  }
}
