package com.zstu.bysj.cmgs.service.spider;

import us.codecraft.webmagic.Site;

import java.util.HashSet;
import java.util.Set;

public class SiteUtils {

    public static Site getInstants() {
        Set<Integer> acceptStatCode = new HashSet<Integer>();
        acceptStatCode.add(200);
        acceptStatCode.add(302);
        return Site.me().setRetryTimes(5).setCycleRetryTimes(10).setTimeOut(30000)
                .setSleepTime(200).setAcceptStatCode(acceptStatCode);
    }
}
