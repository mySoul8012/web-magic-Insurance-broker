package com.example.demo.model;

public class JqBxInfo {
  // 姓名
  private String name;
  // 性别
  private String gender;
  // 城市
  private String city;
  // 公司
  private String company;
  // VIP
  private String vipLevel;
  // 从业年限
  private String workingYears;
  // 擅长险种
  private String goodAtInsurance;
  // 资格证号
  private String qualificationNumber;
  // 从业经历
  private String businessExperience;
  // 头像照片
  private String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getVipLevel() {
    return vipLevel;
  }

  public void setVipLevel(String vipLevel) {
    this.vipLevel = vipLevel;
  }

  public String getWorkingYears() {
    return workingYears;
  }

  public void setWorkingYears(String workingYears) {
    this.workingYears = workingYears;
  }

  public String getGoodAtInsurance() {
    return goodAtInsurance;
  }

  public void setGoodAtInsurance(String goodAtInsurance) {
    this.goodAtInsurance = goodAtInsurance;
  }

  public String getQualificationNumber() {
    return qualificationNumber;
  }

  public void setQualificationNumber(String qualificationNumber) {
    this.qualificationNumber = qualificationNumber;
  }

  public String getBusinessExperience() {
    return businessExperience;
  }

  public void setBusinessExperience(String businessExperience) {
    this.businessExperience = businessExperience;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "JqBxInfo{" +
            "name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", city='" + city + '\'' +
            ", company='" + company + '\'' +
            ", vipLevel='" + vipLevel + '\'' +
            ", workingYears='" + workingYears + '\'' +
            ", goodAtInsurance='" + goodAtInsurance + '\'' +
            ", qualificationNumber='" + qualificationNumber + '\'' +
            ", businessExperience='" + businessExperience + '\'' +
            ", url='" + url + '\'' +
            '}';
  }
}
