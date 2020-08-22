package com.example.demo.model;

public class VoBaoMysqlInfoNew {
  // 姓名
  private String name;
  // 职位
  private String position;
  // 从业年限
  private String workYears;
  // 擅长险种
  private String[] goodInsurance;
  // 所在地区
  private String localhost;
  // 所属机构
  private String affiliation;
  // 执业证件号
  private String PCN;
  // 联系电话
  private String phoneNumber;
  // 头像
  private String avatarLink;
  // 个人介绍
  private String self;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getWorkYears() {
    return workYears;
  }

  public void setWorkYears(String workYears) {
    this.workYears = workYears;
  }

  public String[] getGoodInsurance() {
    return goodInsurance;
  }

  public void setGoodInsurance(String[] goodInsurance) {
    this.goodInsurance = goodInsurance;
  }

  public String getLocalhost() {
    return localhost;
  }

  public void setLocalhost(String localhost) {
    this.localhost = localhost;
  }

  public String getAffiliation() {
    return affiliation;
  }

  public void setAffiliation(String affiliation) {
    this.affiliation = affiliation;
  }

  public String getPCN() {
    return PCN;
  }

  public void setPCN(String PCN) {
    this.PCN = PCN;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAvatarLink() {
    return avatarLink;
  }

  public void setAvatarLink(String avatarLink) {
    this.avatarLink = avatarLink;
  }

  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }
}
