package com.zstu.bysj.cmgs.service.spider;

import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

@Service
public class AutoBrandPageProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        List<String> all = page.getHtml().xpath("div[@class=\"tree_nav\"]/ul/li[@class=\"close_child\"]/h4[@class=\"brand_tit\"]/a/text()").all();
        System.out.println(all.size());
        System.out.println(all);
    }

    @Override
    public Site getSite() {
        return SiteUtils.getInstants();
    }
}
