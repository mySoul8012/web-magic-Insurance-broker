package com.example.demo.db;

import com.example.demo.model.FangXingBaoInfo;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Arrays;

public class FangxingMysql implements Pipeline {
  /**
   * Process extracted results.
   *
   * @param resultItems resultItems
   * @param task        task
   */
  @Override
  public void process(ResultItems resultItems, Task task) {
    FangXingBaoInfo fangXingBaoInfo = resultItems.get("fangXingBaoInfo");
    System.out.println(fangXingBaoInfo);

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
      Statement statement = connection.createStatement();
      String sql = "insert into FangXingBaoNew" + " values(" +  "\""+ fangXingBaoInfo.getName() + "\"" + "," + "\"" +fangXingBaoInfo.getCompany() + "\"" + "," +  "\"" +fangXingBaoInfo.getLocalhost() + "\"" +"," +  "\""  +fangXingBaoInfo.getWorkingYears() +  "\"" + "," + "\"" + fangXingBaoInfo.getPreviousPost()  + "\"" + "," +  "\"" + fangXingBaoInfo.getConstellation() +  "\"" +"," +  "\"" + Arrays.toString(fangXingBaoInfo.getAreasExpertise()) +  "\""  + "," +  "\""  +  fangXingBaoInfo.getUrl() + "\"" +  "," +  "\""  +  fangXingBaoInfo.getPersonalIntroduction() + "\""  + "," +  "\""  +  fangXingBaoInfo.getPersonalHobby() + "\""  +   "," +  "\""  +  fangXingBaoInfo.getHonor() + "\"" +")";
      System.out.println(sql);
      int res = statement.executeUpdate(sql);
      System.out.println(res);
    }catch (Exception e){

    }


  }
}
