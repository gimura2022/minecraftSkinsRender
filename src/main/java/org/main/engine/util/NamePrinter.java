package org.main.engine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NamePrinter {
    private static final Logger logger = LoggerFactory.getLogger(NamePrinter.class);

    public static void namePrinter(String[] names) {
        for (String name :names) logger.trace("Hello " + name);
    }
}
