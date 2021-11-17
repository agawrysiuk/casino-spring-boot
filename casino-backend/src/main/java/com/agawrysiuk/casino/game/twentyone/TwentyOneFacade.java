package com.agawrysiuk.casino.game.twentyone;

import com.agawrysiuk.casino.game.exception.NotEnoughMoneyException;
import com.agawrysiuk.casino.game.exception.ServiceUnavailableException;
import com.agawrysiuk.casino.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TwentyOneFacade {

    private final TwentyOneService twentyOneService;
    private final UserService userService;

    public TwentyOneResponseDto twentyOne(String name) {
        BigDecimal userBalance = userService.findCasinoUserByUsername(name).getBalance();
        checkUserBalance(userBalance);

        twentyOneService.resetGame();

        String resultMessage = "Your balance is " + String.format("%1$,.2f", userBalance) + " $. Bet is 10 $.";

        return TwentyOneResponseDto.builder()
                .mainMessage(twentyOneService.getMainMessage())
                .resultMessage(resultMessage)
                .yourCards(twentyOneService.getYourCards())
                .dealersCards(twentyOneService.getDealersCards())
                .gameFinished(twentyOneService.isGameFinished())
                .balance(userBalance)
                .build();
    }

    public TwentyOneResponseDto nextMove(String name, TwentyOneRequestDto requestDto) {
        switch (requestDto.getCode()) {
            case HIT_ME:
                return newCard(name);
            case HOLD:
                return waitForDealer(name);
            case AGAIN:
                return playAgain(name);
            default:
                throw new ServiceUnavailableException();
        }
    }

    public TwentyOneResponseDto newCard(String name) {
        twentyOneService.hitMe();
        return getGameString(name);
    }

    public TwentyOneResponseDto waitForDealer(String name) {
        twentyOneService.dealersTurn();
        return getGameString(name);
    }

    public TwentyOneResponseDto playAgain(String name) {
        return twentyOne(name);
    }

    private TwentyOneResponseDto getGameString(String name) {
        boolean gameFinished = twentyOneService.isGameFinished();
        BigDecimal userBalance = userService.findCasinoUserByUsername(name).getBalance();
        if (gameFinished) {
            if (twentyOneService.getGameResult()) {
                userBalance = userBalance.add(new BigDecimal("10"));
            } else {
                userBalance = userBalance.add(new BigDecimal("-10"));
            }
        }
        userService.updateCasinoUserBalance(userBalance, name);

        return TwentyOneResponseDto.builder()
                .mainMessage(twentyOneService.getMainMessage())
                .resultMessage(twentyOneService.getResultMessage())
                .yourCards(twentyOneService.getYourCards())
                .dealersCards(twentyOneService.getDealersCards())
                .gameFinished(gameFinished)
                .balance(userBalance)
                .build();
    }

    private void checkUserBalance(BigDecimal userBalance) {
        if (!userService.isEnoughMoney(userBalance, new BigDecimal("1"))) {
            throw new NotEnoughMoneyException();
        }
    }
}
