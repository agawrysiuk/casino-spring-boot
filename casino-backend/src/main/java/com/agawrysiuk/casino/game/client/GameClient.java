package com.agawrysiuk.casino.game.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GameClient {

    @Value("${casino.api.host.game}")
    private String gameHost;

    private final RestTemplate restTemplate;

    public String startGame() {
        return restTemplate.getForObject(gameHost + "/api/game", String.class);
    }
}
