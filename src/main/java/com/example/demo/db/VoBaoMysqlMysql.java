package com.example.demo.db;

import com.example.demo.model.vobaoMysqlInfo;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class VoBaoMysqlMysql implements Pipeline {
  /**
   * Process extracted results.
   *
   * @param resultItems resultItems
   * @param task        task
   */
  @Override
  public void process(ResultItems resultItems, Task task) {
    vobaoMysqlInfo vobaoMysqlInfo = resultItems.get("vobao");
    System.out.println(vobaoMysqlInfo);
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
      Statement statement = connection.createStatement();
      String sql = "insert into vobaoMysqlInfo" + " values(" + "\"" +  vobaoMysqlInfo.getName() + "\""  + "," + "\"" +  vobaoMysqlInfo.getPhone() + "\"" +"," +  "\"" +  vobaoMysqlInfo.getPosition() + "\"" + "," + "\"" +  vobaoMysqlInfo.getAffiliation() + "\"" +"," +  "\"" +  vobaoMysqlInfo.getOCN() + "\"" + "," + "\""  +  vobaoMysqlInfo.getLocalhost()  + "\"" + "," +   "\""  +  vobaoMysqlInfo.getProYears()  + "\""+ ","  + "\""  +  vobaoMysqlInfo.getPhoto()  + "\"" + ","  + "\""  +  vobaoMysqlInfo.getSelf()  + "\"" + ")";
      System.out.println(sql);
      int res = statement.executeUpdate(sql);
      System.out.println(res);
    }catch (Exception e){

    }
  }
}
