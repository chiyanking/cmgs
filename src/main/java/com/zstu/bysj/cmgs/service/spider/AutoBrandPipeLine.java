package com.zstu.bysj.cmgs.service.spider;


import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Service
public class AutoBrandPipeLine implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println(resultItems.toString());
    }
}
