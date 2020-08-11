package com.example.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

// 读取相关内容
public class axbxwReader implements PageProcessor {
  private Site site = Site.me().setRetryTimes(5000).setSleepTime(0);


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
    return null;
  }
}
