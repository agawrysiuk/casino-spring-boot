package com.agawrysiuk.casino.game.roulette;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RouletteRequestDto {
    @NotNull
    private RouletteChoice choice;
    @NotNull
    private String value;
}
