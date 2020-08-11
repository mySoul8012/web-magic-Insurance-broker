package com.example.demo.db;

import com.example.demo.model.RequestInfo;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
  }
}
