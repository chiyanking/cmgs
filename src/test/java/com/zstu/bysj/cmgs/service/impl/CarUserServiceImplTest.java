package com.zstu.bysj.cmgs.service.impl;

import com.zstu.bysj.cmgs.model.entity.AutoBrand;
import com.zstu.bysj.cmgs.service.AutoBrandService;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class CarUserServiceImplTest extends BaseTest {

    @Resource
    AutoBrandService autoBrandService;

    @Test
    public void save() throws Exception {
        List<AutoBrand> list = autoBrandService.findList();
        System.out.println(list);
    }
}