package com.agawrysiuk.casino.client.payment;

import com.agawrysiuk.casino.client.dto.PaymentRequest;
import com.agawrysiuk.casino.client.dto.PaymentResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
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

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
class PaymentClientTest {

    private MockRestServiceServer mockServer;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentClient paymentClient;
    @Autowired
    private ObjectMapper mapper;
    @Value("${casino.api.host.payment}")
    private String paymentHost;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void should_return_pay_reponse_with_correct_id() throws URISyntaxException, JsonProcessingException {
        var userId = UUID.randomUUID();

        PaymentRequest request = PaymentRequest.builder()
                .userId(userId)
                .amount(BigDecimal.valueOf(50))
                .email("mail@mail.com")
                .build();

        PaymentResponse expected = PaymentResponse.builder()
                .amount(BigDecimal.valueOf(50))
                .id(UUID.randomUUID())
                .userId(userId)
                .build();
        mockServer.expect(ExpectedCount.once(),
                requestTo(new URI(paymentHost)))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(expected))
                );

        PaymentResponse result = paymentClient.pay(request);
        mockServer.verify();
        Assertions.assertEquals(expected, result);
    }
}
