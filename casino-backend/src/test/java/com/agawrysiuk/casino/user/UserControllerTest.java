package com.agawrysiuk.casino.user;

import com.agawrysiuk.casino.utils.UserDtoUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest("casino.bootstrap.dev=false")
@AutoConfigureMockMvc
class UserControllerTest {

    private static final String API_ENDPOINT = "/v1/user";
    private static final String REGISTER_ENDPOINT = API_ENDPOINT + "/register";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    UserFacade userFacade;

    @Test
    void should_register_with_correct_data() throws Exception {
        String requestContent = objectMapper.writeValueAsString(UserDtoUtils.correctUser());
        mockMvc.perform(MockMvcRequestBuilders.put(REGISTER_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(requestContent))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_400_when_username_is_null() throws Exception {
        String requestContent = objectMapper.writeValueAsString(UserDtoUtils.withUsername(null));
        mockMvc.perform(MockMvcRequestBuilders.put(REGISTER_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList("username can't be null"))));
    }

    @Test
    void should_return_400_when_username_is_empty() throws Exception {
        String requestContent = objectMapper.writeValueAsString(UserDtoUtils.withUsername(""));
        mockMvc.perform(MockMvcRequestBuilders.put(REGISTER_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList("username can't be "))));
    }

    @Test
    void should_return_400_when_username_is_whitespace() throws Exception {
        String requestContent = objectMapper.writeValueAsString(UserDtoUtils.withUsername(" "));
        mockMvc.perform(MockMvcRequestBuilders.put(REGISTER_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(requestContent))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList("username can't be  "))));
    }

}
