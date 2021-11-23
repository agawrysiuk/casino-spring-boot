package com.agawrysiuk.casino.client.helper;

import com.agawrysiuk.casino.client.dto.PaymentRequest;
import com.agawrysiuk.casino.client.dto.PaymentResponse;
import com.agawrysiuk.casino.client.dto.PaymentStatus;
import com.agawrysiuk.casino.client.model.Payment;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@UtilityClass
public class TestHelper {

    public PaymentRequest getPaymentRequest() {
        return PaymentRequest.builder()
                .amount(BigDecimal.valueOf(50))
                .email("usermail@mail.com")
                .userId(UUID.randomUUID())
                .build();
    }

    public Payment getPayment(PaymentRequest request) {
        return Payment.builder()
                .id(UUID.randomUUID())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .mail(request.getEmail())
                .requestedOn(LocalDateTime.now())
                .status(PaymentStatus.STARTED)
                .build();
    }

    public PaymentResponse getPaymentResponse(Payment entity) {
        return PaymentResponse.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .amount(entity.getAmount())
                .build();
    }
}
