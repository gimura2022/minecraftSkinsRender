package org.main.engine.util;

import org.main.engine.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

    public static String readFile(String file) {
        logger.debug("Reading file: " + file);

        boolean appendSlashes = false;
        boolean returnInOneLine = false;

        StringBuilder shaderSource = new StringBuilder();

        try {
            InputStream in = getFileFromResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;

            while((line = reader.readLine()) != null) {
                shaderSource.append(line);

                if(appendSlashes)    shaderSource.append("//");
                if(!returnInOneLine) shaderSource.append("\n");
            }

            reader.close();

            return shaderSource.toString();
        } catch(IOException e) {
            logger.error("Unable to read file!");
            System.exit(-1);
        }

        return null;
    }

    public static void writeFile(String path, String file) {
        logger.debug("Write file: " + path);

        try (FileWriter fileWriter = new FileWriter(path, false)) {
            fileWriter.write(file);
            fileWriter.flush();
        } catch (IOException e) {
            logger.error("Unable to write file!");
            System.exit(-1);
        }
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {
        logger.debug("Reading file as stream: " + fileName);

        ClassLoader classLoader = FileUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            logger.error("Unable to read file!");
            System.exit(-1);
        }
        return inputStream;
    }
}
