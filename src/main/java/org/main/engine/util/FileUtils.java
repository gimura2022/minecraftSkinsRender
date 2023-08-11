package org.main.engine.util;

import java.io.*;

public class FileUtils {
    public static String readFile(String file) {
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
            System.err.println("Unable to read file!");
            System.exit(-1);
        }

        return null;
    }

    public static void writeFile(String path, String file) {
        try (FileWriter fileWriter = new FileWriter(path, false)) {
            fileWriter.write(file);
            fileWriter.flush();
        } catch (IOException e) {
            System.err.println("Unable to write file!");
            System.exit(-1);
        }
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) { throw new IllegalArgumentException("file not found! " + fileName); } else { return inputStream; }
    }
}
