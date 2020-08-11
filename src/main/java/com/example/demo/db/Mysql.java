package com.example.demo.db;

import com.example.demo.Jqbx;
import com.example.demo.model.JqBxInfo;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.stream.Stream;

public class Mysql implements Pipeline {
  /**
   * Process extracted results.
   *
   * @param resultItems resultItems
   * @param task        task
   */
  @Override
  public void process(ResultItems resultItems, Task task) {
    JqBxInfo jqBxInfo = resultItems.get("jqBxInfo");
    // 获取数据进行插入

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
      Statement statement = connection.createStatement();
      String sql = "insert into JqBxInfo" + " values(" +  "\""+jqBxInfo.getName() + "\"" + "," + "\"" +jqBxInfo.getGender() + "\"" + "," +  "\"" +jqBxInfo.getCity() + "\"" +"," +  "\""  +jqBxInfo.getCompany() +  "\"" + "," + "\"" + jqBxInfo.getVipLevel()  + "\"" + "," +  "\"" + jqBxInfo.getWorkingYears() +  "\"" +"," +  "\"" +jqBxInfo.getGoodAtInsurance() +  "\""  + "," +  "\""  +  jqBxInfo.getQualificationNumber() +  "\"" + "," + "\""  +  jqBxInfo.getBusinessExperience()  +  "\"" + "," +  "\""  + jqBxInfo.getUrl() +  "\"" + ")";
      System.out.println(sql);
      int res = statement.executeUpdate(sql);
      System.out.println(res);
    }catch (Exception e){

    }
  }
}
