package com.example.demo.model;

public class AxbxwInfo {
  // 名字
  private String name;
  // 性别
  private String gender;
  // 资格证号
  private String certificate;
  // 证书状态
  private String ficateStates;
  // 有效截止日期
  private String endDate;
  // 业务范围
  private String businessScope;
  // 销售区域
  private String salesArea;
  // 公司
  private String company;
  // 头像
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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

  public String getCertificate() {
    return certificate;
  }

  public void setCertificate(String certificate) {
    this.certificate = certificate;
  }

  public String getFicateStates() {
    return ficateStates;
  }

  public void setFicateStates(String ficateStates) {
    this.ficateStates = ficateStates;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public String getBusinessScope() {
    return businessScope;
  }

  public void setBusinessScope(String businessScope) {
    this.businessScope = businessScope;
  }

  public String getSalesArea() {
    return salesArea;
  }

  public void setSalesArea(String salesArea) {
    this.salesArea = salesArea;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "AxbxwInfo{" +
            "name='" + name + '\'' +
            ", gender='" + gender + '\'' +
            ", certificate='" + certificate + '\'' +
            ", ficateStates='" + ficateStates + '\'' +
            ", endDate='" + endDate + '\'' +
            ", businessScope='" + businessScope + '\'' +
            ", salesArea='" + salesArea + '\'' +
            ", company='" + company + '\'' +
            ", url='" + url + '\'' +
            '}';
  }
}
