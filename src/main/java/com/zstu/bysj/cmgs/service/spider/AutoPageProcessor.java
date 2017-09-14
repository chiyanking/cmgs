package com.zstu.bysj.cmgs.service.spider;

import com.zstu.bysj.cmgs.model.dto.AutoModel;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutoPageProcessor implements PageProcessor {

    @Override
    public void process(Page page) {
        page.getHtml().$(".tree_nav .close_child").select(new Selector() {
            @Override
            public String select(String s) {
                Html brandNameHtml = new Html(s);
                String brandName = brandNameHtml.$(".close_child h4.brand_tit ").xpath("//a/text()").toString();
                List<String> all1 = brandNameHtml.xpath("//a[@class='model-a']").all();
                for (String s1 : all1) {
                    Html html = new Html(s1);
                    String url = html.$("a", "href").toString();
                    page.addTargetRequest(url);
                }
                return s;
            }

            @Override
            public List<String> selectList(String s) {
                return new ArrayList<>();
            }
        });
        //处理车型
        List<String> titles = page.getHtml().$(".top_con .top_tit").$("a[uigs=xccx]", "title").all();
        if (!CollectionUtils.isEmpty(titles)) {
            for (String title : titles) {
                List<String> all = page.getHtml().xpath("//dl[@class='info']/dd/a[@uigs='jdcszdj']/text()").all();
                if (all.size() == 1) {
                    String price = all.get(0);
                    String[] lowHight = price.replace("万", "").split("-");
                    AutoModel autoModel = new AutoModel();
                    autoModel.setName(title.trim());
                    autoModel.setLowPrice(new BigDecimal(Float.valueOf(lowHight[0]) * 10000));
                    autoModel.setHighPrice(new BigDecimal(Float.valueOf(lowHight[1]) * 10000));
                    page.putField("autoModel", autoModel);
                }
            }
        }
    }


    @Override
    public Site getSite() {
        return SiteUtils.getInstants();
    }
}
