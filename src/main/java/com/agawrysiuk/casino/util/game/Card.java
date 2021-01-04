package com.agawrysiuk.casino.util.game;

import com.agawrysiuk.casino.util.game.CardColor;
import com.agawrysiuk.casino.util.game.CardStrength;
import lombok.Getter;

@Getter
public class Card {
    private CardColor cardColor;
    private CardStrength cardStrength;
    private String cardSrc;

    public Card(CardColor cardColor, CardStrength cardStrength) {
        this.cardColor = cardColor;
        this.cardStrength = cardStrength;
        this.cardSrc = "/images/cards/"
                + cardStrength.name().split("")[0]
                + cardColor.getColor().split("")[0]
                + ".png";
    }

    @Override
    public String toString() {
        return cardStrength.toString() + " of " + cardColor.toString();
    }
}
