package com.eparking.informationPush;

import com.eparking.informationPush.service.system.CommonService;
import com.eparking.informationPush.until.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = 1)
public class ApplicationRunnerTest implements ApplicationRunner {
    @Autowired private CommonService commonService;
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("启动加载...");
        commonService.loadGlobal();
        //System.out.println("数据"+ Global.parkPermissionMap.size());
    }
}
