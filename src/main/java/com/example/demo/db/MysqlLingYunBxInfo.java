package com.example.demo.db;

import com.example.demo.model.LingyunbxInfo;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MysqlLingYunBxInfo implements Pipeline {
  /**
   * Process extracted results.
   *
   * @param resultItems resultItems
   * @param task        task
   */
  @Override
  public void process(ResultItems resultItems, Task task) {
    LingyunbxInfo lingyunbxInfo = resultItems.get("lingyunbxInfo");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
      Statement statement = connection.createStatement();
      String sql = "insert into LingyunbxInfo" + " values(" +  "\""+ lingyunbxInfo.getName() + "\"" + "," + "\"" +lingyunbxInfo.getGender() + "\"" + "," +  "\"" +lingyunbxInfo.getCity() + "\"" +"," +  "\""  +lingyunbxInfo.getCompany() +  "\"" + "," + "\"" + lingyunbxInfo.getWorkingyears() + "\"" + "," +  "\"" + lingyunbxInfo.getGoodAtInsurance() +  "\"" + "," +  "\""  +  lingyunbxInfo.getWeiChat() +  "\"" + "," + "\""  +  lingyunbxInfo.getFacialPhoto()  +  "\"" + "," +  "\""  + lingyunbxInfo.getPersonalProfile() +  "\"" + ")";
      System.out.println(sql);
      int res = statement.executeUpdate(sql);
      System.out.println(res);
    }catch (Exception e){

    }
    System.out.println(lingyunbxInfo);
  }
}
