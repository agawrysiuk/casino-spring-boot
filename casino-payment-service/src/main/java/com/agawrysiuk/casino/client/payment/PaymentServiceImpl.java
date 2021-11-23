package com.agawrysiuk.casino.client.payment;

import com.agawrysiuk.casino.client.dto.PaymentRequest;
import com.agawrysiuk.casino.client.dto.PaymentResponse;
import com.agawrysiuk.casino.client.dto.PaymentStatus;
import com.agawrysiuk.casino.client.model.Payment;
import com.agawrysiuk.casino.client.model.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    @Override
    public PaymentResponse pay(PaymentRequest request) {
        var payment = Payment.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .mail(request.getEmail())
                .requestedOn(LocalDateTime.now())
                .status(PaymentStatus.STARTED)
                .build();

        var entity = repository.save(payment);

        return PaymentResponse.builder()
                .id(entity.getId())
                .amount(request.getAmount())
                .userId(request.getUserId())
                .build();
    }
}
