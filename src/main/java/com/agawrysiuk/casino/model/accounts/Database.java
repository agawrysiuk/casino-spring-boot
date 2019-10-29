package com.agawrysiuk.casino.model.accounts;

public final class Database {

    private static Database instance = new Database();

    private Database() {
    }

    public Database getInstance(){
        return instance;
    }
}
