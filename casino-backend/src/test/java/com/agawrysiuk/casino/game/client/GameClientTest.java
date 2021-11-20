package com.agawrysiuk.casino.game.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
class GameClientTest {

    private MockRestServiceServer mockServer;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private GameClient gameClient;
    @Autowired
    private ObjectMapper mapper;
    @Value("${api.host.game}")
    private String gameHost;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void startGame() throws URISyntaxException, JsonProcessingException {
        String expected = "Started";
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(gameHost + "/api/game")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(expected)
//                        .body(mapper.writeValueAsString(expected))
                );

        String result = gameClient.startGame();
        mockServer.verify();
        assertEquals(expected, result);
    }
}
