package com.example.demo.model;

import java.util.Arrays;

public class FangXingBaoInfo {
  // 姓名
  private String name;
  // 公司
  private String company;
  // 所在地
  private String localhost;
  // 从业年限
  private String workingYears;
  // 上一职业
  private String previousPost;
  // 星座
  private String constellation;
  // 擅长领域
  private String[] areasExpertise;
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

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getLocalhost() {
    return localhost;
  }

  public void setLocalhost(String localhost) {
    this.localhost = localhost;
  }

  public String getWorkingYears() {
    return workingYears;
  }

  public void setWorkingYears(String workingYears) {
    this.workingYears = workingYears;
  }

  public String getPreviousPost() {
    return previousPost;
  }

  public void setPreviousPost(String previousPost) {
    this.previousPost = previousPost;
  }

  public String getConstellation() {
    return constellation;
  }

  public void setConstellation(String constellation) {
    this.constellation = constellation;
  }

  public String[] getAreasExpertise() {
    return areasExpertise;
  }

  public void setAreasExpertise(String[] areasExpertise) {
    this.areasExpertise = areasExpertise;
  }

  @Override
  public String toString() {
    return "FangXingBaoInfo{" +
            "name='" + name + '\'' +
            ", company='" + company + '\'' +
            ", localhost='" + localhost + '\'' +
            ", workingYears='" + workingYears + '\'' +
            ", previousPost='" + previousPost + '\'' +
            ", constellation='" + constellation + '\'' +
            ", areasExpertise=" + Arrays.toString(areasExpertise) +
            ", url='" + url + '\'' +
            '}';
  }
}
