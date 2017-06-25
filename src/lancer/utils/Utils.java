package lancer.utils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project name - Tanks
 * Package name - lancer.utils
 * Created by Lancer on 025 25.06.17.
 */
public class Utils {

    public static BufferedImage resize(BufferedImage image, int width, int height) {
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image, 0, 0, width, height, null);

        return newImage;
    }

    public static Integer[][] levelParser(String filePath) {

        Integer[][] result = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)))) {

            String line = null;
            List<Integer[]> levelsLine = new ArrayList<Integer[]>();
            // Читаем файл построчно
            while ((line = reader.readLine()) != null) {
                // Разбиваем первую строчку файла на отдельные токены
                levelsLine.add(str2intArrays(line.split(" ")));
            }

            result = new Integer[levelsLine.size()][levelsLine.get(0).length];

            for (int i = 0; i < levelsLine.size(); i++) {
                result[i] = levelsLine.get(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static final Integer[] str2intArrays(String[] sArr) {
        Integer[] result = new Integer[sArr.length];

        for (int i = 0; i < sArr.length; i++) {
            result[i] = Integer.parseInt(sArr[i]);
        }

        return result;
    }
}
