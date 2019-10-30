package com.agawrysiuk.casino.service;

public interface SlotsService {
    void roll();
    int[] getResults();
    double getMultiplier();
    String getMessage();
}
