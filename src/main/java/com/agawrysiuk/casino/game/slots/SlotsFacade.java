package com.agawrysiuk.casino.game.slots;

import com.agawrysiuk.casino.game.exception.NotEnoughMoneyException;
import com.agawrysiuk.casino.user.UserService;
import com.agawrysiuk.casino.util.AttributeNames;
import com.agawrysiuk.casino.util.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class SlotsFacade {

    private final SlotsService slotsService;
    private final UserService userService;

    SlotsDto slots(String name) {
        BigDecimal userBalance = userService.findCasinoUserByUsername(name).getBalance();
        checkUserBalance(userBalance);
        return slotsService.getInitial(userBalance);
    }

    SlotsDto roll(String name) {
        BigDecimal userBalance = userService.findCasinoUserByUsername(name).getBalance();
        checkUserBalance(userBalance);

        BigDecimal moneyResult = slotsService.roll();

        userBalance = userBalance.add(new BigDecimal("-1")).add(moneyResult);
        userService.updateCasinoUserBalance(userBalance, name);

        return SlotsDto.builder()
                .results(slotsService.getResults())
                .mainMessage(slotsService.getMessage())
                .balance(userBalance)
                .moneyResult(moneyResult)
                .results(slotsService.getResults())
                .build();
    }

    private void checkUserBalance(BigDecimal userBalance) {
        if (!userService.isEnoughMoney(userBalance, new BigDecimal("1"))) {
            throw new NotEnoughMoneyException();
        }
    }
}
