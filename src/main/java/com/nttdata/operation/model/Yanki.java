package com.nttdata.operation.model;
import lombok.Data;

@Data
public class Yanki {
    private String id;
    private String phoneNumber;
    private String typeDocument;
    private String imei;
    private String email;
    private Double balance;
    private String accountId;
}
