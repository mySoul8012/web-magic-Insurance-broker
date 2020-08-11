package com.example.demo;

import com.example.demo.db.Mysql;
import com.example.demo.model.JqBxInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashMap;
import java.util.Map;

public class bxlcs implements PageProcessor {
  private Site site = Site.me().setRetryTimes(500000000).setSleepTime(0);


  /**
   * process the page, extract urls to fetch, extract the data and store
   *
   * @param page page
   */
  @Override
  public void process(Page page) {

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
    Spider.create(new bxlcs())
            .addUrl("http://www.jq-bx.com/dailiren/11500")
           // .addPipeline(new Mysql())
            .thread(5000)
            .run();
  }
}
