package com.agawrysiuk.casino.client.payment;

import com.agawrysiuk.casino.client.dto.PaymentStatus;
import com.agawrysiuk.casino.client.helper.TestHelper;
import com.agawrysiuk.casino.client.model.Payment;
import com.agawrysiuk.casino.client.model.PaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class PaymentServiceImplTest {

    @MockBean
    private PaymentRepository repository;
    @Autowired
    private PaymentService service;

    @Test
    public void should_return_payment_object() {
        // given
        var request = TestHelper.getPaymentRequest();
        var entity = TestHelper.getPayment(request);
        var expected = TestHelper.getPaymentResponse(entity);

        Mockito.when(repository.save(any())).thenReturn(entity);

        // when
        var result = service.pay(request);

        // then
        Assertions.assertEquals(expected, result);
    }

}
