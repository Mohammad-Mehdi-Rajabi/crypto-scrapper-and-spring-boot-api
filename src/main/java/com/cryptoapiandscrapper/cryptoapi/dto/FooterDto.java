package com.cryptoapiandscrapper.cryptoapi.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FooterDto {
    private String rank;
    private String name;
    private String exchange;
    private String quantity;
    private String total;
    private String side;
    private String dateTime;

}
