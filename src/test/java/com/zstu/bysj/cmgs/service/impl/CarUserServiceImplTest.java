package com.zstu.bysj.cmgs.service.impl;

import com.zstu.bysj.cmgs.service.AutoBrandService;
import com.zstu.bysj.cmgs.service.spider.AutoBrandPageProcessor;
import com.zstu.bysj.cmgs.service.spider.AutoBrandPipeLine;
import com.zstu.bysj.cmgs.service.spider.URLConfig;
import org.junit.Test;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

public class CarUserServiceImplTest extends BaseTest {

    @Resource
    AutoBrandService autoBrandService;

    @Resource
    AutoBrandPageProcessor brandPageProcessor;

    @Resource
    AutoBrandPipeLine brandPipeLine;

    @Test
    public void save() throws Exception {
        Spider spider = Spider.create(brandPageProcessor).addPipeline(brandPipeLine)
                .addUrl(URLConfig.BASE_URL).thread(1);
        spider.run();
    }
}