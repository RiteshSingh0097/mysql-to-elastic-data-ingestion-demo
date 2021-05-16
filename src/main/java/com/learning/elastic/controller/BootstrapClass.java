package com.learning.elastic.controller;

import com.learning.elastic.model.PincodeMaster;
import com.learning.elastic.service.PincodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BootstrapClass implements ApplicationListener<ApplicationReadyEvent> {


    @Autowired
    private PincodeService pincodeService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        log.info("Adding some value in PINCODE_MASTER table");
        for (int i = 0; i < 10; i++) {
            PincodeMaster pincodeMaster = new PincodeMaster();
            pincodeMaster.setZipcode("22541" + i);
            pincodeService.createDataInDb(pincodeMaster);
            log.info("data in db :: " + pincodeMaster);
        }
    }
}
