package com.example.demo.db;

import com.example.demo.model.Bx58Bean;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

public class PipBx58 implements Pipeline {
  /**
   * Process extracted results.
   *
   * @param resultItems resultItems
   * @param task        task
   */
  @Override
  public void process(ResultItems resultItems, Task task) {
    List<Bx58Bean> bx58Beans = resultItems.get("bx");
    System.out.println(bx58Beans.size());
    for(int i = 0; i < bx58Beans.size(); i++){
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
        Statement statement = connection.createStatement();
        String sql = "insert into Bx58Bean" + " values(" +
                "\"" + bx58Beans.get(i).getOpenID()  +  "\"" + "," +
                "\"" + bx58Beans.get(i).getGradeImg() + "\"" + "," +
                "\"" + bx58Beans.get(i).getCityName() + "\"" + "," +
                "\"" + bx58Beans.get(i).getcName() + "\"" + "," +
                "\"" + bx58Beans.get(i).getgName() + "\"" + "," +
                "\"" + bx58Beans.get(i).getCpinyin() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuId() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuName() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuPwd() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuRealname() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuSex() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuProvice() + "\"" + "," +
                 "\"" + bx58Beans.get(i).getuCity() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuCounty() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuAddress() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuGsid() + "\"" + "," +
                "\"" + bx58Beans.get(i).getuIntegral() + "\"" + "," +
               "\"" + bx58Beans.get(i).getuGrade() + "\"" + "," +
              "\"" + bx58Beans.get(i).getuAvator() + "\"" + "," +
              "\"" + bx58Beans.get(i).getuTel() + "\"" + "," +
            "\"" + bx58Beans.get(i).getuEmail() + "\"" + "," +
            "\"" + bx58Beans.get(i).getuQQ() + "\"" + "," +
            "\"" + bx58Beans.get(i).getuIdcard() + "\"" + "," +
            "\"" + bx58Beans.get(i).getuQualification() + "\"" + "," +
            "\"" + bx58Beans.get(i).getuZyzh() + "\"" + "," +
            "\"" + bx58Beans.get(i).getIsApproved() + "\"" + "," +
           "\"" + bx58Beans.get(i).getuLoginCount() + "\"" + "," +
           "\"" + bx58Beans.get(i).getuLoginDate() + "\"" + "," +
            "\"" + bx58Beans.get(i).getuLoginIp() + "\"" + "," +
            "\"" + bx58Beans.get(i).getuPcount() + "\"" + "," +
             "\"" + bx58Beans.get(i).getuWebSite() + "\"" + "," +
             "\"" + bx58Beans.get(i).getuLevel() + "\"" + "," +
          "\"" + bx58Beans.get(i).getuGoal() + "\"" + "," +
           "\"" + bx58Beans.get(i).getuIntroduce() + "\"" + "," +
           "\"" + bx58Beans.get(i).getuHobby() + "\"" + "," +
          "\"" + bx58Beans.get(i).getuQuotation() + "\"" + "," +
          "\"" + bx58Beans.get(i).getIsTop() + "\"" + "," +
          "\"" + bx58Beans.get(i).getIsVip() + "\"" + "," +
          "\"" + bx58Beans.get(i).getVipStartDate() + "\"" + "," +
          "\"" + bx58Beans.get(i).getVipEndDate() + "\"" + "," +
          "\"" + bx58Beans.get(i).getTopStartDate() + "\"" + "," +
          "\"" + bx58Beans.get(i).getTopEndDate() + "\"" + "," +
          "\"" + bx58Beans.get(i).getuFocusDate() + "\"" + "," +
          "\"" + bx58Beans.get(i).getuEndFocusDate() + "\"" + "," +
          "\"" + bx58Beans.get(i).getuRegDate() + "\"" + ","+
          "\"" + bx58Beans.get(i).getIsFocus() + "\"" + "," +
          "\"" + bx58Beans.get(i).getuType() + "\"" +","+
          "\"" + bx58Beans.get(i).getuBirth() + "\""+ "," +
          "\"" + bx58Beans.get(i).getuJoinDate() + "\""+ "," +
          "\"" + bx58Beans.get(i).getuClick() + "\"" + "," +
           "\"" + bx58Beans.get(i).getuSpecialty() + "\"" + "," +
          "\"" + bx58Beans.get(i).getuActive() + "\"" + "," +
        "\"" + bx58Beans.get(i).getIsState() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuNotice() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuFeature() + "\"" + "," +
       "\"" + bx58Beans.get(i).getuForte() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuAgencyIds() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuTemplate() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuDitch() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuAntecedents() + "\"" + "," +
        "\"" + bx58Beans.get(i).getUfollow() + "\"" + "," +
        "\"" + bx58Beans.get(i).getUrmark() + "\"" + "," +
        "\"" + bx58Beans.get(i).getUagent() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuMurl() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuMusciId() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuAgentPid() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuKeyword1() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuKeyword2() + "\"" + "," +
        "\"" + bx58Beans.get(i).getUvotedate() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuCloseDate() + "\"" + "," +
        "\"" + bx58Beans.get(i).getUvotecount() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuMyFollow() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuWithdrawals() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuPercentage() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuPayment() + "\"" + "," +
        "\"" + bx58Beans.get(i).getuCodeUrl() + "\"" + "," +
        "\"" + bx58Beans.get(i).getIsIdCard() + "\"" + "," +
        "\"" + bx58Beans.get(i).getIsCode() + "\"" + "," +
       "\"" + bx58Beans.get(i).getuGradePrice() + "\"" + "," +
         "\"" + bx58Beans.get(i).getContractNotes() + "\"" + "," +
       "\"" + bx58Beans.get(i).getUserType() + "\"" + "," +
        "\"" + bx58Beans.get(i).getWeiXin() + "\"" + "," +
      "\"" + bx58Beans.get(i).getUmyqq() + "\"" + "," +
      "\"" + bx58Beans.get(i).getUphone() + "\"" + "," +
       "\"" + bx58Beans.get(i).getuKeywords() + "\"" + "," +
      "\"" + bx58Beans.get(i).getuDescription() + "\"" + "," +
      "\"" + bx58Beans.get(i).getuCloseRemarks() + "\"" + "," +
      "\"" + bx58Beans.get(i).getUcompany() + "\"" + "," +
      "\"" + bx58Beans.get(i).getIsInterview() + "\"" + "," +
    "\"" + bx58Beans.get(i).getuIdCardNum() + "\"" + "," +
       "\"" + bx58Beans.get(i).getuQualificationNum() + "\"" + "," +
      "\"" + bx58Beans.get(i).getuRestartTime() + "\"" + "," +
     "\"" + bx58Beans.get(i).getuTone() + "\"" + "," +
     "\"" + bx58Beans.get(i).getUserHead() + "\"" + "," +
    "\"" + bx58Beans.get(i).getuAvatorPass() + "\"" + "," +
    "\"" + bx58Beans.get(i).getUCodeSrc() + "\"" + "," +
     "\"" + bx58Beans.get(i).getIsCust() + "\""  + "," +
      "\"" + bx58Beans.get(i).getMarriage() + "\"" + "," +
    "\"" + bx58Beans.get(i).getPersonId() + "\"" + "," +
     "\"" + bx58Beans.get(i).getIsDemand() + "\"" + "," +
     "\"" + bx58Beans.get(i).getuRegDateStr() + "\"" + "," +
     "\"" + bx58Beans.get(i).getUforteText() + "\"" +
        ")";
        System.out.println(sql);
        int res = statement.executeUpdate(sql);
        System.out.println(res);
      }catch (Exception e){

      }
    }
  }
}
