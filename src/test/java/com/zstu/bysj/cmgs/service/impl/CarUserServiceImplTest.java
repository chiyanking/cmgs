package com.zstu.bysj.cmgs.service.impl;

import com.zstu.bysj.cmgs.service.AutoBrandService;
import com.zstu.bysj.cmgs.service.spider.AutoPageProcessor;
import com.zstu.bysj.cmgs.service.spider.AutoPipeLine;
import com.zstu.bysj.cmgs.service.spider.URLConfig;
import org.junit.Test;
import us.codecraft.webmagic.Spider;

import javax.annotation.Resource;

public class CarUserServiceImplTest extends BaseTest {

    @Resource
    AutoBrandService autoBrandService;

    @Resource
    AutoPageProcessor brandPageProcessor;

    @Resource
    AutoPipeLine brandPipeLine;

    @Test
    public void save() throws Exception {
        Spider spider = Spider.create(brandPageProcessor).addPipeline(brandPipeLine)
                .addUrl(URLConfig.BASE_URL).thread(1);
        spider.run();
    }
}