package com.agawrysiuk.casino.client.payment;

import com.agawrysiuk.casino.client.dto.PaymentRequest;
import com.agawrysiuk.casino.client.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PaymentClient {

    @Value("${casino.api.host.payment}")
    private String paymentHost;

    private final RestTemplate restTemplate;

    public PaymentResponse pay(PaymentRequest request) {
        return restTemplate.postForObject(paymentHost, request, PaymentResponse.class);
    }
}
