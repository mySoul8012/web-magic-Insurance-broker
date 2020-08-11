package com.example.demo.model;

public class PingAnInfo {
  // 姓名
  private String name;
  // 所在地区
  private String localhost;
  // 所属公司
  private String company;
  // 企业地址
  private String companyLocalhost;
  // 电话
  private String phone;
  // 微信号
  private String weiChat;
  // qq
  private String qqNumber;
  // 自我介绍
  private String self;
  // 头像url
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public String toString() {
    return "PingAnInfo{" +
            "name='" + name + '\'' +
            ", localhost='" + localhost + '\'' +
            ", company='" + company + '\'' +
            ", companyLocalhost='" + companyLocalhost + '\'' +
            ", phone='" + phone + '\'' +
            ", weiChat='" + weiChat + '\'' +
            ", qqNumber='" + qqNumber + '\'' +
            ", self='" + self + '\'' +
            ", url='" + url + '\'' +
            '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocalhost() {
    return localhost;
  }

  public void setLocalhost(String localhost) {
    this.localhost = localhost;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getCompanyLocalhost() {
    return companyLocalhost;
  }

  public void setCompanyLocalhost(String companyLocalhost) {
    this.companyLocalhost = companyLocalhost;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getWeiChat() {
    return weiChat;
  }

  public void setWeiChat(String weiChat) {
    this.weiChat = weiChat;
  }

  public String getQqNumber() {
    return qqNumber;
  }

  public void setQqNumber(String qqNumber) {
    this.qqNumber = qqNumber;
  }

  public String getSelf() {
    return self;
  }

  public void setSelf(String self) {
    this.self = self;
  }
}
