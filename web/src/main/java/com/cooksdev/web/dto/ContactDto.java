package com.cooksdev.web.dto;

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
    private String mobilePhone;
    private String homePhone;
    private String address;
    private String mail;
}
