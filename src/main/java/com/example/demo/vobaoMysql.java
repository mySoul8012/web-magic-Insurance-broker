package com.example.demo;

import com.example.demo.model.vobaoMysqlInfo;
import org.assertj.core.error.ShouldBeAfterYear;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

// 实现Mysql的批量读取
public class vobaoMysql implements PageProcessor {
  private Site site = Site.me().setRetryTimes(50000).setSleepTime(0)
          .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {
    vobaoMysqlInfo vobaoMysqlInfo = new vobaoMysqlInfo();
    // 姓名
    String name = page.getHtml().css("div.infor").xpath("/div/h3/a/text()").toString();
    vobaoMysqlInfo.setName(name);
    // 电话
    String phone = page.getHtml().css("div.infor").xpath("/div/span/text()").toString();
    vobaoMysqlInfo.setPhone(phone);
    // 职位
    String position = page.getHtml().css("span.fw").xpath("/span/text()").toString();
    vobaoMysqlInfo.setPosition(position);
    // 所属机构
    List<String> stringList = page.getHtml().css("span.fw").replace("<span class=\"fw\">", "").replace("</span>", "").all();
    // 所属机构
    String affiliation = stringList.get(1);
    vobaoMysqlInfo.setAffiliation(affiliation);
    // 职业证
    String OCN = stringList.get(2);
    vobaoMysqlInfo.setOCN(OCN);
    // 地区
    String localhost = stringList.get(3);
    vobaoMysqlInfo.setLocalhost(localhost);
    // 职业年限
    String proYears = stringList.get(4);
    vobaoMysqlInfo.setProYears(proYears);
    // 头像照片
    String photo = page.getHtml().css("div.photo").xpath("/div/img/@src").toString();
    vobaoMysqlInfo.setPhoto(photo);
    System.out.println(vobaoMysqlInfo);
   }

  /**
   * get the site settings
   *
   * @return site
   * @see Site
   */
  @Override
  public Site getSite() {
    return this.site;
  }

  public static void main(String[] args) {
    // 进行sql 查询
//    try {
//      Class.forName("com.mysql.cj.jdbc.Driver");
//      Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
//      Statement statement = connection.createStatement();
//      String sql = "select distinct url from url";
//      System.out.println(sql);
//      ResultSet rs = statement.executeQuery(sql);
//      while(rs.next()){
//        String url = rs.getString("url");
        Spider.create(new vobaoMysql())
                //从"https://github.com/code4craft"开始抓
                .addUrl("http://www.hzzgrb.cn")
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
//      }
//    }catch (Exception e){
//
//    }
//    /*
//    Spider.create(new GithubRepoPageProcessor())
//            //从"https://github.com/code4craft"开始抓
//            .addUrl("http://www.jq-bx.com/dailiren/index?page=1")
//            //开启5个线程抓取
//            .thread(5)
//            //启动爬虫
//            .run();
//
//
//     */
  }
}
