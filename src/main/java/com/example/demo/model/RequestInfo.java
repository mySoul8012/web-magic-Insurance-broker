package com.example.demo.model;

import com.google.gson.annotations.SerializedName;

public class RequestInfo{
  private String Area1List1;
  private String Area2List2;
  @SerializedName("Search.KeyWord")
  private String KeyWord;
  @SerializedName("Search.AreaID1")
  private String AreaID1;
  @SerializedName("Search.AreaID3")
  private String AreaID3;
  @SerializedName("Search.PyCode")
  private String PyCode;
  @SerializedName("Search.CompanySymbol")
  private String CompanySymbol;
  @SerializedName("Search.Year")
  private String year;
  @SerializedName("Search.OrderTp")
  private String OrderTp;
  @SerializedName("Search.SearchTp")
  private String searchTp;
  @SerializedName("Search.UserLevel")
  private String userLevel;
  @SerializedName("Search.SortRule")
  private String SortRule;
  @SerializedName("Search.PageIndex")
  private String PageIndex;
  @SerializedName("Search.PageSize")
  private String pageSize;
  @SerializedName("Search.LastHash")
  private String lastHash;
  @SerializedName("Search.TotalPages")
  private String totalPages;
  @SerializedName("Search.TotalCount")
  private String totalCount;
  @SerializedName("sex")
  private String sex;
  @SerializedName("X-Requested-With")
  private String xRequestedWith;

  @Override
  public String toString() {
    return "RequestInfo{" +
            "Area1List1='" + Area1List1 + '\'' +
            ", Area2List2='" + Area2List2 + '\'' +
            ", KeyWord='" + KeyWord + '\'' +
            ", AreaID1='" + AreaID1 + '\'' +
            ", AreaID3='" + AreaID3 + '\'' +
            ", PyCode='" + PyCode + '\'' +
            ", CompanySymbol='" + CompanySymbol + '\'' +
            ", year='" + year + '\'' +
            ", OrderTp='" + OrderTp + '\'' +
            ", searchTp='" + searchTp + '\'' +
            ", userLevel='" + userLevel + '\'' +
            ", SortRule='" + SortRule + '\'' +
            ", PageIndex='" + PageIndex + '\'' +
            ", pageSize='" + pageSize + '\'' +
            ", lastHash='" + lastHash + '\'' +
            ", totalPages='" + totalPages + '\'' +
            ", totalCount='" + totalCount + '\'' +
            ", sex='" + sex + '\'' +
            ", xRequestedWith='" + xRequestedWith + '\'' +
            '}';
  }

  public String getArea1List1() {
    return Area1List1;
  }

  public void setArea1List1(String area1List1) {
    Area1List1 = area1List1;
  }

  public String getArea2List2() {
    return Area2List2;
  }

  public void setArea2List2(String area2List2) {
    Area2List2 = area2List2;
  }

  public String getKeyWord() {
    return KeyWord;
  }

  public void setKeyWord(String keyWord) {
    KeyWord = keyWord;
  }

  public String getAreaID1() {
    return AreaID1;
  }

  public void setAreaID1(String areaID1) {
    AreaID1 = areaID1;
  }

  public String getAreaID3() {
    return AreaID3;
  }

  public void setAreaID3(String areaID3) {
    AreaID3 = areaID3;
  }

  public String getPyCode() {
    return PyCode;
  }

  public void setPyCode(String pyCode) {
    PyCode = pyCode;
  }

  public String getCompanySymbol() {
    return CompanySymbol;
  }

  public void setCompanySymbol(String companySymbol) {
    CompanySymbol = companySymbol;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getOrderTp() {
    return OrderTp;
  }

  public void setOrderTp(String orderTp) {
    OrderTp = orderTp;
  }

  public String getSearchTp() {
    return searchTp;
  }

  public void setSearchTp(String searchTp) {
    this.searchTp = searchTp;
  }

  public String getUserLevel() {
    return userLevel;
  }

  public void setUserLevel(String userLevel) {
    this.userLevel = userLevel;
  }

  public String getSortRule() {
    return SortRule;
  }

  public void setSortRule(String sortRule) {
    SortRule = sortRule;
  }

  public String getPageIndex() {
    return PageIndex;
  }

  public void setPageIndex(String pageIndex) {
    PageIndex = pageIndex;
  }

  public String getPageSize() {
    return pageSize;
  }

  public void setPageSize(String pageSize) {
    this.pageSize = pageSize;
  }

  public String getLastHash() {
    return lastHash;
  }

  public void setLastHash(String lastHash) {
    this.lastHash = lastHash;
  }

  public String getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(String totalPages) {
    this.totalPages = totalPages;
  }

  public String getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(String totalCount) {
    this.totalCount = totalCount;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getxRequestedWith() {
    return xRequestedWith;
  }

  public void setxRequestedWith(String xRequestedWith) {
    this.xRequestedWith = xRequestedWith;
  }
}
