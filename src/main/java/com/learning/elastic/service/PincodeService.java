package com.learning.elastic.service;

import com.learning.elastic.model.PincodeEsMaster;
import com.learning.elastic.model.PincodeMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PincodeService {

    PincodeMaster createDataInDb(PincodeMaster pincodeMaster);

    List<PincodeMaster> findByZipCodeFormDb(Pageable pageable);

    Page<PincodeMaster> findAllPage(Pageable pageable);

    void createDataInEs(PincodeEsMaster pincodeMaster);

    PincodeMaster findByZipCodeFormEs(String pincode);
}
