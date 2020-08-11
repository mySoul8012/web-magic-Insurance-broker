package com.example.demo.db;

import com.example.demo.model.AxbxwInfo;
import com.example.demo.model.LingyunbxInfo;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AxbxwMySql implements Pipeline {
  /**
   * Process extracted results.
   *
   * @param resultItems resultItems
   * @param task        task
   */
  @Override
  public void process(ResultItems resultItems, Task task) {
    AxbxwInfo axbxwInfo = resultItems.get("axbxInfo");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
      Statement statement = connection.createStatement();
      String sql = "insert into AxbxwInfo" + " values(" +  "\""+ axbxwInfo.getName() + "\"" + "," + "\"" +axbxwInfo.getGender() + "\"" + "," +  "\"" +axbxwInfo.getCertificate() + "\"" +"," +  "\""  +axbxwInfo.getFicateStates() +  "\"" + "," + "\"" + axbxwInfo.getEndDate()  + "\"" + "," +  "\"" + axbxwInfo.getBusinessScope() +  "\"" +"," +  "\"" +axbxwInfo.getSalesArea() +  "\""  + "," +  "\""  +  axbxwInfo.getCompany() + "\"" +   ","  +  "\"" +  axbxwInfo.getUrl() +    "\""   +  ")";
      System.out.println(sql);
      int res = statement.executeUpdate(sql);
      System.out.println(res);
    }catch (Exception e){

    }
  }
}
