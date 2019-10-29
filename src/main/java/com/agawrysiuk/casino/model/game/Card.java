package com.agawrysiuk.casino.model.game;

import com.agawrysiuk.casino.util.game.CardColor;
import com.agawrysiuk.casino.util.game.CardStrength;
import lombok.Getter;

@Getter
public class Card {
    private CardColor cardColor;
    private CardStrength cardStrength;

    public Card(CardColor cardColor, CardStrength cardStrength) {
        this.cardColor = cardColor;
        this.cardStrength = cardStrength;
    }

    @Override
    public String toString() {
        return cardStrength.toString() + " of " + cardColor.toString();
    }
}
