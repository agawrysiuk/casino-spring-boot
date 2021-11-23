package com.agawrysiuk.casino.client.payment;

import com.agawrysiuk.casino.client.dto.PaymentRequest;
import com.agawrysiuk.casino.client.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse pay(PaymentRequest request);
}
