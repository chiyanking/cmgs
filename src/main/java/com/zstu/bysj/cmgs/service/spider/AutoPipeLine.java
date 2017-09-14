package com.zstu.bysj.cmgs.service.spider;


import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Service
public class AutoPipeLine implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {

        Object autoModel = resultItems.get("autoModel");
        System.out.println(autoModel);

    }
}
