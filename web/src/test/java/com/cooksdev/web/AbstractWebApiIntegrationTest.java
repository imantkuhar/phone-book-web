package com.cooksdev.web;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = WebApiTestConfig.class)
@AutoConfigureMockMvc
@TestExecutionListeners(listeners = {DbUnitTestExecutionListener.class }, mergeMode = MERGE_WITH_DEFAULTS )
@DbUnitConfiguration(dataSetLoader = CustomDataSetLoader.class)
public class AbstractWebApiIntegrationTest {
    protected @Autowired MockMvc mockMvc;
    protected @PersistenceContext
    EntityManager em;
    protected @Autowired
    TestRestTemplate restTemplate;
}
