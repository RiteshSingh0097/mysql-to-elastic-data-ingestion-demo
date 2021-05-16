package com.learning.elastic.service.impl;

import com.learning.elastic.jpa.PincodeJpaRepository;
import com.learning.elastic.model.PincodeEsMaster;
import com.learning.elastic.model.PincodeMaster;
import com.learning.elastic.repository.PincodeEsRepository;
import com.learning.elastic.service.PincodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PincodeServiceImpl implements PincodeService {

    @Autowired
    private PincodeJpaRepository jpaRepository;

    @Autowired
    private PincodeEsRepository esRepository;

    @Override
    public PincodeMaster createDataInDb(PincodeMaster pincodeMaster) {
        return jpaRepository.save(pincodeMaster);
    }

    @Override
    public List<PincodeMaster> findByZipCodeFormDb(Pageable pageable) {
        return jpaRepository.findAll();
    }

    @Override
    public Page<PincodeMaster> findAllPage(Pageable pageable) {
        return jpaRepository.findAll(pageable);
    }

    @Override
    public void createDataInEs(PincodeEsMaster pincodeMaster) {
        esRepository.save(pincodeMaster);
    }

    @Override
    public PincodeMaster findByZipCodeFormEs(String pincode) {
        return null;
    }
}
