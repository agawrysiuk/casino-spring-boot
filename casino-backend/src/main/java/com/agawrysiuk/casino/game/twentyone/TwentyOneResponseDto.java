package com.agawrysiuk.casino.game.twentyone;

import com.agawrysiuk.casino.game.utils.Card;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TwentyOneResponseDto {
    private String mainMessage;
    private List<Card> yourCards;
    private List<Card> dealersCards;
    private boolean gameFinished;
    private String resultMessage;
    private BigDecimal balance;
}
