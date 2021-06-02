package com.junior.CRUD.utils;

import com.junior.CRUD.model.Games;

public class Verification {

    public static boolean verificationGames(Games games) {
        return !games.getName().trim().equals("") &&
                !games.getDescription().trim().equals("") &&
                !games.getCategory().trim().equals("") &&
                games.getPrice() > 0;
    }
}
