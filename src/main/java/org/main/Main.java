package org.main;

import org.main.engine.Game;
import org.main.engine.util.NamePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting render");

        String[] names = {
                "ModEratorTA",
                "WavyCat",
                "vivilka21",
                "Jagorrim"
        };

        NamePrinter.namePrinter(names);

        Game game = new Game();
        game.run();
    }
}