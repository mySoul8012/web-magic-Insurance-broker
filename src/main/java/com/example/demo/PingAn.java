package com.example.demo;

import com.example.demo.db.AxbxwMySql;
import com.example.demo.db.PingAnMysql;
import com.example.demo.model.JqBxInfo;
import com.example.demo.model.PingAnInfo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;

public class PingAn implements PageProcessor {
  private Site site = Site.me().setRetryTimes(500000000).setSleepTime(1000);


  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {
    PingAnInfo pingAnInfo = new PingAnInfo();
    // 获取姓名
    String name = page.getHtml().xpath("/html/body/div[5]/div/div[2]/div[2]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[4]/td[2]/text()").toString();
    pingAnInfo.setName(name);
    // 获取地区
    String localhost = page.getHtml().xpath("/html/body/div[5]/div/div[2]/div[2]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/text()").toString();
    pingAnInfo.setLocalhost(localhost);
    System.out.println(localhost);
    // 获取所属公司
    String company = page.getHtml().xpath("/html/body/div[5]/div/div[2]/div[2]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/text()").toString();
    pingAnInfo.setCompany(company);
    // 获取所属公司位置
    String companyLocalhost = page.getHtml().xpath("/html/body/div[5]/div/div[2]/div[2]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/text()").toString();
    pingAnInfo.setCompanyLocalhost(companyLocalhost);
    // 获取联系人
    String phone =  page.getHtml().xpath("/html/body/div[5]/div/div[2]/div[2]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[5]/td[2]/text()").toString();
    pingAnInfo.setPhone(phone);
    // 微信号
    String weiChat = page.getHtml().xpath("/html/body/div[5]/div/div[2]/div[2]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[6]/td[2]/text()").toString();
    pingAnInfo.setWeiChat(weiChat);
    // qqNumber
    String qqNumber = page.getHtml().xpath("/html/body/div[5]/div/div[2]/div[2]/div/table[2]/tbody/tr/td[1]/table/tbody/tr[7]/td[2]/text()").toString();
    pingAnInfo.setQqNumber(qqNumber);
    // 个人介绍
    String self = page.getHtml().css("div.com_intro").xpath("/div/text()").toString();
    pingAnInfo.setSelf(self);
    // 获取头像url
    String url = page.getHtml().css("div.space_left_body").xpath("/div/li/img/@src").toString();
    pingAnInfo.setUrl(url);
    // 获取数字
    String[] integer = page.getUrl().toString().split("-");
    Integer integer1 = Integer.parseInt(integer[2]);
    String tmpUrl = "http://www.ping-an.net/pa-" + ++integer1;
    if(pingAnInfo.getName() == null  || pingAnInfo.getName().equals(" ")){
      page.setSkip(true);
    }
    // 添加数据
    page.putField("pingAnInfo", pingAnInfo);

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
    Spider.create(new PingAn())
            .addUrl("http://www.ping-an.net/pa-5786")
            .addPipeline(new PingAnMysql())
            .thread(5000)
            .run();
  }
}
