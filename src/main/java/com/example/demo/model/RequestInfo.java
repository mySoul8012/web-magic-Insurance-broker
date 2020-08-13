package com.example.demo.model;

import com.google.gson.annotations.SerializedName;

public class RequestInfo{
  @SerializedName("Search.Pagesize")
  private String pagesize;
  @SerializedName("Search.Pageindex")
  private String pageindex;
  @SerializedName("Search.CompSymbol")
  private String compSymbol;
  @SerializedName("Search.CitySymbol")
  private String citySymbol;
  @SerializedName("Search.ProvinceSymbol")
  private String provinceSymbol;

  @Override
  public String toString() {
    return "RequestInfo{" +
            "pagesize='" + pagesize + '\'' +
            ", pageindex='" + pageindex + '\'' +
            ", compSymbol='" + compSymbol + '\'' +
            ", citySymbol='" + citySymbol + '\'' +
            ", provinceSymbol='" + provinceSymbol + '\'' +
            '}';
  }

  public String getPagesize() {
    return pagesize;
  }

  public void setPagesize(String pagesize) {
    this.pagesize = pagesize;
  }

  public String getPageindex() {
    return pageindex;
  }

  public void setPageindex(String pageindex) {
    this.pageindex = pageindex;
  }

  public String getCompSymbol() {
    return compSymbol;
  }

  public void setCompSymbol(String compSymbol) {
    this.compSymbol = compSymbol;
  }

  public String getCitySymbol() {
    return citySymbol;
  }

  public void setCitySymbol(String citySymbol) {
    this.citySymbol = citySymbol;
  }

  public String getProvinceSymbol() {
    return provinceSymbol;
  }

  public void setProvinceSymbol(String provinceSymbol) {
    this.provinceSymbol = provinceSymbol;
  }
}
