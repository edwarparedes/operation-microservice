package com.nttdata.operation.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Operation {
    @Id
    private String id;
    private String phoneSend;
    private String phoneReceive;
    private Double amount;
    
}
