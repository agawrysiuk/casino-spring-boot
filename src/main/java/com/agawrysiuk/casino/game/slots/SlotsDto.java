package com.agawrysiuk.casino.game.slots;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SlotsDto {
    private BigDecimal balance;
    private BigDecimal moneyResult;
    private String mainMessage;
    private int[] results;
}
