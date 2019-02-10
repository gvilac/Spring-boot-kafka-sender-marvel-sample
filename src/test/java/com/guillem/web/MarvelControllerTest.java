package com.guillem.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guillem.Application;
import com.guillem.model.Creator;
import com.guillem.model.RequestCreators;
import com.guillem.service.KafkaSender;
import com.guillem.service.MarvelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


/**
 * Created by guillem on 10/02/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class MarvelControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext applicationContext;

    @MockBean
    private MarvelService marvelService;

    @MockBean
    private KafkaSender kafkaSender;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void getCreatorsShouldReturnOneCreator() throws Exception {
        given(marvelService.getCreators(any(RequestCreators.class)))
                .willReturn(Stream.of(basicCreator()).collect(Collectors.toList()));

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/creators")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Creator[] productlist = mapFromJson(content, Creator[].class);
        assertTrue(productlist.length > 0);
    }

    private Creator basicCreator() {
        Creator creator = new Creator();
        creator.setFirstName("Guillem");
        creator.setLastName("Test");
        return creator;
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
