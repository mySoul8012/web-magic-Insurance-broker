package com.example.demo.db;

import com.example.demo.model.RequestInfo;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class vobaoMysql implements Pipeline {
  /**
   * Process extracted results.
   *
   * @param resultItems resultItems
   * @param task        task
   */
  @Override
  public void process(ResultItems resultItems, Task task) {
    List<String> list = resultItems.get("url");
    List<String> listRes = new ArrayList<>();
    for(int i = 0; i < list.size(); i++){
      String tmp ="http://www." +  list.get(i) + ".cn";
      System.out.println(tmp);
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
        Statement statement = connection.createStatement();
        String sql = "insert into url" + " values(" + "\"" +  tmp + "\"" +  ")";
        System.out.println(sql);
        int res = statement.executeUpdate(sql);
        System.out.println(res);
      }catch (Exception e){

      }
    }
  }
}
