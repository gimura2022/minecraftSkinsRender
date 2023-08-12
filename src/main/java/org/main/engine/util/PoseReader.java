package org.main.engine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoseReader {
    private static final Logger logger = LoggerFactory.getLogger(PoseReader.class);

    public static float[] readPoseInFile(String path) {
        String[] poseFile = FileUtils.readFile(path).split("\n");

        int vertex = 0;
        for (String line : poseFile) { if (line.toCharArray()[0] == 'v' && line.toCharArray()[1] == ' ') { vertex++; } }

        float[] out = new float[vertex + ((vertex / 3) * 4 + (vertex / 3) * 3)];

        vertex = 0;

        for (int i = 0; i < poseFile.length - 1; i++) {
            if (poseFile[i].toCharArray()[0] == 'v' && poseFile[i].toCharArray()[1] == ' ') {
                int offset = vertex * 10;

                try {
                    char[] charsFloats = new char[poseFile[i].toCharArray().length - 2];
                    poseFile[i].getChars(2, poseFile[i].toCharArray().length, charsFloats, 0);
                    String stringFloats = String.valueOf(charsFloats);

                    out[offset] = Float.valueOf(stringFloats.split(" ")[0]);
                    out[offset + 1] = Float.valueOf(stringFloats.split(" ")[1]);
                    out[offset + 2] = Float.valueOf(stringFloats.split(" ")[2]);
                } catch (NumberFormatException e) {
                    logger.error("Invalid format pose file!");
                    System.exit(-1);
                }

                out[offset + 3] = 0.7f;
                out[offset + 4] = 0.7f;
                out[offset + 5] = 0.7f;
                out[offset + 6] = 1f;

                out[offset + 7] = 0f;
                out[offset + 8] = 0f;
                out[offset + 9] = 0f;

                vertex++;
            }
        }

        return out;
    }
}
