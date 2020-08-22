package com.example.demo;

import com.example.demo.db.VoBaoMysqlMysql;
import com.example.demo.model.vobaoMysqlInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.assertj.core.error.ShouldBeAfterYear;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 实现Mysql的批量读取
public class vobaoMysql implements PageProcessor {
  private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
          .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {
    vobaoMysqlInfo vobaoMysqlInfo = new vobaoMysqlInfo();

    // 根据页面模板进行分类
    if(page.getHtml().css("div.qj").toString() != null){
      // 进入www.hzzgrb.cn 页面模板
      // 获取姓名
      String name = page.getHtml().css("div.infor").xpath("/div/h3/a/text()").toString();
      vobaoMysqlInfo.setName(name);

      // 获取电话
      String phone = page.getHtml().css("div.infor").xpath("/div/span/text()").toString();
      vobaoMysqlInfo.setPhone(phone);

      // 职位
      String position = page.getHtml().css("div.infor").xpath("/div/p[2]/span/text()").toString();
      vobaoMysqlInfo.setPosition(position);

      // 所属机构
      String affiliation = page.getHtml().css("div.infor").xpath("/div/p[3]/span/text()").toString();
      vobaoMysqlInfo.setAffiliation(affiliation);

      // 执业证号
      String OCN = page.getHtml().css("div.infor").xpath("/div/p[4]/span/text()").toString();
      vobaoMysqlInfo.setOCN(OCN);

      // 所在地区
      String localhost = page.getHtml().css("div.infor").xpath("/div/p[5]/span/text()").toString();
      vobaoMysqlInfo.setLocalhost(localhost);

      // 执业年限
      String proYears = page.getHtml().css("div.infor").xpath("/div/p[5]/span[2]/text()").toString();
      vobaoMysqlInfo.setProYears(proYears);

      // 照片
      String photo = page.getHtml().css("div.photo").xpath("/div/img/@src").toString();
      vobaoMysqlInfo.setPhoto(photo);

      // 关于我
      String self = page.getHtml().css("div.self").replace("\"", "").replace("”", "").replace("“", "").replace("'", "").toString();
      vobaoMysqlInfo.setSelf(cleanHtmlTags(self));

    }

    if(vobaoMysqlInfo.getOCN() == null && page.getHtml().css("div.g-doc").toString() != null){
      //  www.rsbxmj.cn  获取
      // 姓名
      String name = cleanHtmlTags(page.getHtml().css("a.infoname").toString());
      vobaoMysqlInfo.setName(name);
      // 执业年限
      String proYears = cleanHtmlTags(page.getHtml().css("p.info-txt").replace("执业年限：", "").toString());
      vobaoMysqlInfo.setProYears(proYears);
      // 地区
      String localhost = page.getHtml().css("p.info-txt").replace("地区：", "").all().get(1);
      vobaoMysqlInfo.setLocalhost(cleanHtmlTags(localhost));
      // 所属机构
      String affiliation = page.getHtml().css("p.info-txt").replace("所属机构：", "").all().get(2);
      vobaoMysqlInfo.setAffiliation(cleanHtmlTags(affiliation));
      // 证件号
      String OCN = page.getHtml().css("p.info-txt").replace("执业证号：", "").replace("资格证号：", "").all().get(3);
      vobaoMysqlInfo.setOCN(cleanHtmlTags(OCN));
      // 照片
      String photo = page.getHtml().css("dl.info-con").xpath("/dl/dt/a/img/@src").toString();
      vobaoMysqlInfo.setPhoto(photo);

      Document document = Jsoup.parse(sendGet(page.getUrl() + "/about"));
      Elements elements = document.getElementsByClass("a-con");
      vobaoMysqlInfo.setSelf(elements.text());
    }

    // 按照模板进行分类，第三个
    if(vobaoMysqlInfo.getOCN() == null && page.getHtml().css("div.per-right__bottom").toString() != null ){
      // http://www.sbrsx.cn/
      // 姓名
      String name = page.getHtml().css("div.per-name").xpath("/div/span/text()").toString();
      vobaoMysqlInfo.setName(name);
      // 职位
      String position = page.getHtml().css("div.per-right__top").xpath("/div/div[2]/span/text()").replace("职位：", "").toString();
      vobaoMysqlInfo.setPosition(position);
      // 所属机构
      String affiliation = page.getHtml().css("div.per-right__top").xpath("/div/div[3]/text()").replace("所属机构：", "").toString();
      vobaoMysqlInfo.setAffiliation(affiliation);
      // 执业证号
      String OCN = page.getHtml().css("div.per-right__top").xpath("/div/div[4]/text()").replace("执业证号：", "").toString();
      vobaoMysqlInfo.setOCN(OCN);
      // 所在地
      String localhost = page.getHtml().css("div.per-right__top").xpath("/div/div[6]/text()").replace("所在地区：", "").toString();
      vobaoMysqlInfo.setLocalhost(localhost);
      // 执业年限
      String proYears = page.getHtml().css("div.per-right__top").xpath("/div/div[7]/text()").replace("执业年限：", "").toString();
      vobaoMysqlInfo.setProYears(proYears);
      String self = page.getHtml().css("div.per-right__bottom").xpath("/div/p/text()").toString();
      vobaoMysqlInfo.setSelf(self);
    }


    // 第四个页面模板
    if(vobaoMysqlInfo.getOCN() == null && page.getHtml().css("div.info_rz").toString() != null){
      // 获取第四个页面的模板   http://www.hfpabx.cn/
      String name = page.getHtml().css("div.info_text").xpath("/div/ul/li/strong/a/text()").toString();
      vobaoMysqlInfo.setName(name);
      String OCN = page.getHtml().css("div.info_rz").xpath("/div/ul/li[2]/text()").replace("执业证号：", "").toString();
      vobaoMysqlInfo.setOCN(OCN);
      // 所在地区
      String localhost = page.getHtml().css("div.info_rz").all().get(1);
      String[] localhosts = cleanHtmlTags(localhost).split("\n");
      vobaoMysqlInfo.setLocalhost(localhosts[3].replace("所在地区：", ""));

      // 所属机构
      String affiliation = page.getHtml().css("div.info_rz").all().get(1);
      String[] localhosts1 = cleanHtmlTags(localhost).split("\n");
      vobaoMysqlInfo.setAffiliation(localhosts[2].replace("所属机构：", ""));

      // 头像
      String photo = page.getHtml().css("div.info_img").xpath("/div/img/@src").toString();
      vobaoMysqlInfo.setPhoto(photo);
    }



    // 第五个页面模板
    if(vobaoMysqlInfo.getOCN() == null && page.getHtml().css("div.g-mn").toString() != null){
      // 获取姓名
      String name = page.getHtml().css("a.name").toString();
      vobaoMysqlInfo.setName(cleanHtmlTags(name));

      //
      String tmp = page.getHtml().css("p.company").xpath("/p/text()").toString();
      String[] tmps = tmp.split(" ");
      vobaoMysqlInfo.setLocalhost(tmps[1]);
      vobaoMysqlInfo.setAffiliation(tmps[2]);

      // OCN
      String OCN = page.getHtml().css("div.num").xpath("/div/span/text").replace("保险销售执业证号：", "").replace("保险代理资格证号：", "").toString();
      vobaoMysqlInfo.setOCN(OCN);

      // 照片
      String photo = page.getHtml().css("div.g-mn").xpath("/div/div/div/dl/dt/a/img/@src").toString();
      vobaoMysqlInfo.setPhoto(photo);
    }

    // 第六个页面模板
    // http://www.wxpazx.cn/
    if(vobaoMysqlInfo.getOCN() == null && page.getHtml().css("div.person-wrapper").toString() != null) {
      // 获取姓名
      String name = page.getHtml().css("div.infor-name").xpath("/div/text()").toString();
      vobaoMysqlInfo.setName(name);
      // 获取职称
      String position = page.getHtml().css("div.infor-name").xpath("/div/span/text()").toString();
      vobaoMysqlInfo.setPosition(position);
      // 执业证号
      String OCN = page.getHtml().css("span.person-zh").xpath("/span/text()").toString();
      vobaoMysqlInfo.setOCN(OCN);
      // 所属机构
      String affiliation = page.getHtml().css("span.infor-item__text").xpath("/span/text()").toString();
      vobaoMysqlInfo.setAffiliation(affiliation);
      // 所在地区
      String localhost = page.getHtml().css("div.person-wrapper").xpath("/div/div[6]/span[2]/text()").toString();
      vobaoMysqlInfo.setLocalhost(localhost);
      String photo = page.getHtml().css("div.person-infor").xpath("/div/img/@src").toString();
      vobaoMysqlInfo.setPhoto(photo);


    }



      page.putField("vobao", vobaoMysqlInfo);


   }


  private static String sendGet(String url) {
    //1.获得一个httpclient对象
    CloseableHttpClient httpclient = HttpClients.createDefault();
    //2.生成一个get请求
    HttpGet httpget = new HttpGet(url);
    httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.0.3) Gecko/2008092417 Firefox/3.0.3");
    CloseableHttpResponse response = null;
    try {
      //3.执行get请求并返回结果
      response = httpclient.execute(httpget);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    String result = null;
    try {
      //4.处理结果，这里将结果返回为字符串
      HttpEntity entity = response.getEntity();
      if (entity != null) {
        result = EntityUtils.toString(entity);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        response.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  private String cleanHtmlTags(String htmlText)
  {
    if (StringUtils.isEmpty(htmlText))
    {
      return "";
    }
    htmlText = htmlText.replaceAll("&nbsp;", " "); // 过滤html标签
    String regEx_html = "<[^>]+>";
    String regEx_td = "<[td]+>";
    String regEx_tr = "<[tr]+>";
    Pattern p_html = Pattern.compile(regEx_td, Pattern.CASE_INSENSITIVE);
    Matcher m_html = p_html.matcher(htmlText);
    htmlText = m_html.replaceAll(" "); // td替换成空格

    p_html = Pattern.compile(regEx_tr, Pattern.CASE_INSENSITIVE);
    m_html = p_html.matcher(htmlText);
    htmlText = m_html.replaceAll("\n"); // tr替换成换行

    p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
    m_html = p_html.matcher(htmlText);
    htmlText = m_html.replaceAll(""); // 过滤html标签

    return htmlText;
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
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://cdb-1yfd1mlm.cd.tencentcdb.com:10056/JqBxInfo", "root", "ABCcba20170607");
      Statement statement = connection.createStatement();
      String sql = "select distinct url from url";
      System.out.println(sql);
      ResultSet rs = statement.executeQuery(sql);
      HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
      httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("49.86.183.54",766, "2503942144@qq.com", "010_Ss")));

      while(rs.next()){
        String url = rs.getString("url");
        Spider.create(new vobaoMysql())
                //从"https://github.com/code4craft"开始抓
                .addUrl(url
                )
                // .setDownloader(httpClientDownloader)
                // 保存到mysql 这里保存到mysql
                .addPipeline(new VoBaoMysqlMysql())
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
//        System.out.println(url);
      }
    }catch (Exception e){

    }
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
