package com.cooksdev.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

    private Integer id;
    private String name;
    private String surname;
    private String mobile_phone;
    private String home_phone;
    private String address;
    private String email;
    private Integer userId;
}
