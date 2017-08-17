package com.cooksdev.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HeartbeatDto {
    private String module;

    public HeartbeatDto(String name) {
        module = name;
    }
}
