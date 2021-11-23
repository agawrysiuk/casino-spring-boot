package com.agawrysiuk.casino.client.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest {

    private UUID userId;
    private String email;
    private BigDecimal amount;
}
