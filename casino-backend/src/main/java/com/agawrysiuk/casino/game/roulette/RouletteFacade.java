package com.agawrysiuk.casino.game.roulette;

import com.agawrysiuk.casino.game.exception.NotEnoughMoneyException;
import com.agawrysiuk.casino.game.exception.ServiceUnavailableException;
import com.agawrysiuk.casino.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

@Component
@RequiredArgsConstructor
public class RouletteFacade {

    private final RouletteService rouletteService;
    private final UserService userService;

    public RouletteResponseDto roll(String name, RouletteRequestDto requestDto) {
        switch (requestDto.getChoice()) {
            case GUESS_SINGLE:
                return rouletteSingle(name, Integer.parseInt(requestDto.getValue()));
            case GUESS_RED_OR_BLACK:
                return rouletteRedOrBlackBet(name, requestDto.getValue());
            case GUESS_EVEN_OR_ODD:
                return rouletteEvenOrOddBet(name, requestDto.getValue());
            default:
                throw new ServiceUnavailableException();
        }
    }

    public RouletteResponseDto roulette(String name) {
        BigDecimal userBalance = userService.findCasinoUserByUsername(name).getBalance();
        checkUserBalance(userBalance);

        return RouletteResponseDto.builder()
                .mainMessage(rouletteService.getMainMessage())
                .resultMessage("Your balance is " + userBalance.toString() + " $.")
                .build();
    }

    public RouletteResponseDto rouletteSingle(String name, int guessSingle) {
        BigDecimal userBalance = userService.findCasinoUserByUsername(name).getBalance();
        checkUserBalance(userBalance);

        return getResult(name, userBalance, new BigDecimal("34"), () -> rouletteService.getResultMessageSingle(guessSingle));
    }

    public RouletteResponseDto rouletteRedOrBlackBet(String name, String guessRedOrBlack) {
        BigDecimal userBalance = userService.findCasinoUserByUsername(name).getBalance();
        checkUserBalance(userBalance);

        return getResult(name, userBalance, new BigDecimal("2"), () -> rouletteService.getResultMessageRedOrBlack(guessRedOrBlack));
    }

    public RouletteResponseDto rouletteEvenOrOddBet(String name, String guessEvenOrOdd) {
        BigDecimal userBalance = userService.findCasinoUserByUsername(name).getBalance();
        checkUserBalance(userBalance);

        return getResult(name, userBalance, new BigDecimal("1"), () -> rouletteService.getResultMessageEvenOrOdd(guessEvenOrOdd));
    }

    private void checkUserBalance(BigDecimal userBalance) {
        if (!userService.isEnoughMoney(userBalance, new BigDecimal("1"))) {
            throw new NotEnoughMoneyException();
        }
    }

    private RouletteResponseDto getResult(String name,
                                          BigDecimal userBalance,
                                          BigDecimal possibleWin,
                                          Callable<String> callable) {
        rouletteService.roll();
        String message;

        try {
            message = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceUnavailableException();
        }

        return processResult(name, userBalance, possibleWin, message);
    }

    private RouletteResponseDto processResult(String name,
                                              BigDecimal userBalance,
                                              BigDecimal possibleWin,
                                              String message) {
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("You bet 1 $. ");
        BigDecimal moneyResult;
        if (message.equals("You lost")) {
            resultMessage.append(message);
            moneyResult = new BigDecimal("-1");
        } else if (message.equals("You won")) {
            resultMessage.append("You won ").append(possibleWin.toString()).append(" $");
            moneyResult = possibleWin;
        } else {
            throw new ServiceUnavailableException();
        }

        userBalance = userBalance.add(moneyResult);
        userService.updateCasinoUserBalance(userBalance, name);

        resultMessage.append(". ");
        resultMessage.append("Your balance is now ").append(userBalance.toString()).append(" $.");

        return getRouletteString(userBalance, resultMessage);
    }

    private RouletteResponseDto getRouletteString(BigDecimal userBalance, StringBuilder resultMessage) {
        return RouletteResponseDto.builder()
                .resultMessage(resultMessage.toString())
                .mainMessage(rouletteService.getMainMessage())
                .balance(userBalance)
                .build();
    }
}
