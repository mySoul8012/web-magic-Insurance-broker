package com.example.demo.model;

public class LingyunbxInfo {
  private String name;
  private String gender;
  private String city;
  private String company;
  private String workingyears;
  private String goodAtInsurance;
  private String weiChat;
  private String facialPhoto;
  private String personalProfile;

  public String getFacialPhoto() {
    return facialPhoto;
  }

  public void setFacialPhoto(String facialPhoto) {
    this.facialPhoto = facialPhoto;
  }

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

  public String getWorkingyears() {
    return workingyears;
  }

  public void setWorkingyears(String workingyears) {
    this.workingyears = workingyears;
  }

  public String getGoodAtInsurance() {
    return goodAtInsurance;
  }

  public void setGoodAtInsurance(String goodAtInsurance) {
    this.goodAtInsurance = goodAtInsurance;
  }

  public String getWeiChat() {
    return weiChat;
  }

  public void setWeiChat(String weiChat) {
    this.weiChat = weiChat;
  }

  public String getPersonalProfile() {
    return personalProfile;
  }

  public void setPersonalProfile(String personalProfile) {
    this.personalProfile = personalProfile;
  }

  @Override
  public String toString() {
    return "LingyunbxInfo{" +
            "name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", city='" + city + '\'' +
            ", company='" + company + '\'' +
            ", workingyears='" + workingyears + '\'' +
            ", goodAtInsurance='" + goodAtInsurance + '\'' +
            ", weiChat='" + weiChat + '\'' +
            ", facialPhoto='" + facialPhoto + '\'' +
            ", personalProfile='" + personalProfile + '\'' +
            '}';
  }
}
