package com.learning.elastic.controller;

import com.learning.elastic.model.PincodeEsMaster;
import com.learning.elastic.model.PincodeMaster;
import com.learning.elastic.service.ElasticOperationsService;
import com.learning.elastic.service.PincodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
public class PinCodeController {

    @Autowired
    private PincodeService pincodeService;

    @Autowired
    private ElasticOperationsService operationsService;

    @GetMapping("/get-pincode-from-db")
    public int getPincodeFromDb() {
        Page<PincodeMaster> totalPages = pincodeService.findAllPage(PageRequest.of(0, 5));

        log.info("---------------->total Page :: " + totalPages.getTotalPages());
        for (int i = 0; i < totalPages.getTotalPages(); i++) {
            log.info("---------------->Page No.::  " + i);

            totalPages = pincodeService.findAllPage(PageRequest.of(i, 5));
            List<PincodeMaster> pincodeMasters = totalPages.getContent();
            pincodeMasters.parallelStream().forEach(e -> {
                PincodeEsMaster pin = new PincodeEsMaster();
                pin.setId(String.valueOf(e.getId()));
                pin.setZipcode(e.getZipcode());
                pincodeService.createDataInEs(pin);
            });
        }
        return totalPages.getTotalPages();
    }

    @GetMapping("get-location-by-city/{zipcode}")
    public Set<String> getLocationByCity(@PathVariable("zipcode") String zipcode) {
        return operationsService.findByCity(zipcode);
    }
}
