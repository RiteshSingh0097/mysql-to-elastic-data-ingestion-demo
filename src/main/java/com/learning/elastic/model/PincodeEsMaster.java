package com.learning.elastic.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Document(indexName = "pincode_master")
public class PincodeEsMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String zipcode;
    private String city;
    private String state;
    private String district;
    private String location;
}


