package com.agawrysiuk.casino.game.roulette;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RouletteResponseDto {
    private String mainMessage;
    private String resultMessage;
    private BigDecimal balance;
}
