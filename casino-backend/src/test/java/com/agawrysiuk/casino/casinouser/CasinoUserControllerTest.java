package com.agawrysiuk.casino.casinouser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CasinoUserControllerTest {

    private static final String API_ENDPOINT = "/v1/casino-user";

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    CasinoUserService service;

    @Test
    @WithMockUser(username = "Basic_User")
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(API_ENDPOINT))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "Basic_User")
    void update() throws Exception {
        String requestContent = objectMapper.writeValueAsString(CasinoUser.builder().build());
        mockMvc.perform(MockMvcRequestBuilders.patch(API_ENDPOINT).contentType(MediaType.APPLICATION_JSON).content(requestContent))
                .andExpect(status().isAccepted());
    }
}
