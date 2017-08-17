package com.cooksdev.web.controller;

import com.cooksdev.web.AbstractWebApiIntegrationTest;
import com.cooksdev.web.dto.HeartbeatDto;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeartbeatControllerTest extends AbstractWebApiIntegrationTest {
    @Test
    public void exampleTest() throws Exception {
        HeartbeatDto heartbeat = restTemplate.getForObject("/heartbeat", HeartbeatDto.class);
        assertThat(heartbeat.getModule()).isEqualTo("web");
    }

}