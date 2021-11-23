package com.agawrysiuk.casino.client.model;

import com.agawrysiuk.casino.client.dto.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
class PaymentRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PaymentRepository repository;

    @Test
    void should_save_the_data() {
        // given
        var payment = Payment.builder()
                .userId(UUID.randomUUID())
                .mail("some_mail@mail.com.pl")
                .amount(BigDecimal.valueOf(50))
                .status(PaymentStatus.STARTED)
                .requestedOn(LocalDateTime.now())
                .build();

        entityManager.persist(payment);
        entityManager.flush();

        // when
        List<Payment> result = repository.findAll();

        // then
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(result.get(0).getStatus(), PaymentStatus.STARTED);
        Assertions.assertNotNull(result.get(0).getId());
        Assertions.assertNotNull(result.get(0).getRequestedOn());
        Assertions.assertNull(result.get(0).getFinishedOn());
    }
}
