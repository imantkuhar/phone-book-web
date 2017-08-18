package com.cooksdev.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("mobile_phone")
    private String mobilePhone;
    @JsonProperty("home_phone")
    private String homePhone;
    private String address;
    private String email;
    private Integer userId;
}
