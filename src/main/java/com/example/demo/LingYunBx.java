package com.example.demo;

import com.example.demo.db.Mysql;
import com.example.demo.db.MysqlLingYunBxInfo;
import com.example.demo.model.JqBxInfo;
import com.example.demo.model.LingyunbxInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;

public class LingYunBx implements PageProcessor {

  private Site site = Site.me().setRetryTimes(500000000).setSleepTime(0);

  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {
    // 获取url
    String url = page.getUrl().toString();
    int dot = url.split("-")[1].trim().lastIndexOf('.');
    String urlTmp = url.split("-")[1].trim().substring(0, dot);
    int urlInteger = Integer.parseInt(urlTmp);

    LingyunbxInfo lingyunbxInfo = new LingyunbxInfo();
    // 获取姓名
    String name = page.getHtml().xpath("/html/body/div[3]/table/tbody/tr[2]/td/text()").replace("姓名：", "").toString();
    lingyunbxInfo.setName(name);
    // 获取性别
    String gender = page.getHtml().xpath("/html/body/div[3]/table/tbody/tr[3]/td/text()").replace("性别：", "").toString();
    lingyunbxInfo.setGender(gender);
    // 获取城市
    String city = page.getHtml().xpath("/html/body/div[3]/table/tbody/tr[4]/td/text()").replace("城市：", "").toString();
    lingyunbxInfo.setCity(city);
    // 获取公司
    String company = page.getHtml().xpath("/html/body/div[3]/table/tbody/tr[5]/td/text()").replace("公司：", "").toString();
    lingyunbxInfo.setCompany(company);
    // 获取工作年限
    String workingyears = page.getHtml().xpath("/html/body/div[3]/table/tbody/tr[6]/td/text()").replace("从业年限：", "").toString();
    //System.out.println(workingyears);
    lingyunbxInfo.setWorkingyears(workingyears);
    // 获取擅长险种
    String goodAtInsurance = page.getHtml().xpath("/html/body/div[3]/table/tbody/tr[7]/td/text()").replace("擅长险种：", "").toString();
    lingyunbxInfo.setGoodAtInsurance(goodAtInsurance);
    // 获取微信手机
    String weiChat = page.getHtml().xpath("/html/body/div[3]/table/tbody/tr[8]/td/text()").replace("微信/手机：", "").toString();
    lingyunbxInfo.setWeiChat(weiChat);
    // 获取头像照片
    String facialPhoto = page.getHtml().xpath("/html/body/div[3]/table/tbody/tr[1]/td/span/").xpath("//img/@src").toString();
    facialPhoto = "http://www.lingyunbx.com" + facialPhoto;
   // System.out.println(facialPhoto);
    lingyunbxInfo.setFacialPhoto(facialPhoto);
    // 个人简介
    String personalProfile = page.getHtml().xpath("/html/body/div[4]/div/div/p/span/text()").toString();
    //System.out.println(personalProfile);
    lingyunbxInfo.setPersonalProfile(personalProfile);

    // 拼接url
    //urlInteger++;
    String tmpUrl = "http://www.lingyunbx.com/dailiren/show-" +  ++urlInteger +  ".html";

    //System.out.println(lingyunbxInfo.toString());
    try {
      for(;;) {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(tmpUrl);
       // urlInteger++;
        int statusCode = client.executeMethod(method);
        if (statusCode == 404) {
          // url 增加
          tmpUrl = "http://www.lingyunbx.com/dailiren/show-" + (++urlInteger) + ".html";
        }
        if(statusCode != 404){
          break;
        }
      }
    }catch (Exception e){

    }
    page.putField("lingyunbxInfo", lingyunbxInfo);
    page.addTargetRequest(tmpUrl);
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


  public static void main(String[] args){
    Map<String, JqBxInfo> map = new HashMap<String, JqBxInfo>();
    Spider.create(new LingYunBx())
            .addUrl("http://www.lingyunbx.com/dailiren/show-193.html")
            .addPipeline(new MysqlLingYunBxInfo())
            .thread(50000000)
            .run();
  }
}
