package com.brettyin.cardshelper;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;
import de.greenrobot.daogenerator.ToMany;

public class MyDaoGenerator {
    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(4, "com.brettyin.cardshelper.model");
        Entity player = schema.addEntity("Player");
        player.addIdProperty();
        player.addStringProperty("name");
        player.addStringProperty("pic");

        Entity game = schema.addEntity("Game");
        game.addIdProperty();
        game.addIntProperty("Score");
        game.addDateProperty("Date");

        Entity gameToPlayer = schema.addEntity("GameToPlayer");
        Property gameID = gameToPlayer.addLongProperty("gameId").notNull().getProperty();
        ToMany gameToPlayers = game.addToMany(gameToPlayer, gameID);
        Property playerIdProperty = gameToPlayer.addLongProperty("playerId").getProperty();
        gameToPlayer.addToOne(player, playerIdProperty);
        gameToPlayer.addIntProperty("Status");
        gameToPlayer.addIntProperty("Level");

        new DaoGenerator().generateAll(schema, "../app/src/main/java");
    }
}
