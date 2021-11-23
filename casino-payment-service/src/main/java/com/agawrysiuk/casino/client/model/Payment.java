package com.agawrysiuk.casino.client.model;

import com.agawrysiuk.casino.client.dto.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PAYMENT")
public class Payment {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "USER_ID")
    @Type(type = "uuid-char")
    private UUID userId;

    @Column(name = "USER_MAIL")
    private String mail;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "REQUESTED_ON")
    private LocalDateTime requestedOn;

    @Column(name = "FINISHED_ON")
    private LocalDateTime finishedOn;
}
