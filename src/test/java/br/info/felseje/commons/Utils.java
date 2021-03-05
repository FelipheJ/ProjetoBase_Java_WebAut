package br.info.felseje.commons;

import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.Calendar;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Objects;

public class Utils {

    public static class DataUtils {

        public static final String HORA = "HHmmss";
        public static final String DATA = "ddMMyyyy";
        public static final String DATAHORA = "ddMMyyyyHHmmss";

        /**
         * This method takes the current date from the system.
         *
         * @return a Date object.
         */
        public static Date obterDataAtual() {
            return Calendar.getInstance().getTime();
        }

        /**
         * This method takes the current date from the system and returns it in the specified format.
         *
         * @return a Date object.
         */
        public static String obterDataAtual(String formato) {
            return formatarData(obterDataAtual(), formato);
        }

        /**
         * This method formats a specified date to the specified pattern date.
         * @param data the date.
         * @param formato the format.
         * @return a string containing formatted date.
         */
        public static String formatarData(Date data, String formato) {
            return new SimpleDateFormat(formato).format(data);
        }
    }

    public static class ImageUtils {

        /**
         * This method returns the byte vector of the received image.
         * @param image Image.
         * @param imageExtension The desired extent of the image.
         * @return a byte vector.
         */
        public static byte[] getBytes(BufferedImage image, String imageExtension) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            try {
                ImageIO.write(image, imageExtension, outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return outputStream.toByteArray();
        }

        /**
         * Writes the specified image at the specified directory.
         * @param image image to be written.
         * @param imageName containing the name of the image ("Image001").
         * @param formatName containing the informal name of the format ("jpg", "png").
         * @param pathToFolder the directory where the image will be written ("C:\Users\Public\Pictures").
         * @return true if and only if image was written successful.
         */
        public static boolean saveImage(BufferedImage image, String pathToFolder, String imageName, String formatName) {
            String path;
            boolean r = true;
            if (Objects.isNull(image) || Objects.isNull(pathToFolder) || Objects.isNull(imageName) || Objects.isNull(formatName)) {
                throw new IllegalArgumentException("The arguments cannot be null.");
            } else if (pathToFolder.isEmpty() || imageName.isEmpty() || formatName.isEmpty()) {
                throw new IllegalArgumentException("The arguments cannot be empty.");
            }
            path = pathToFolder + imageName + System.getProperty("file.separator") + formatName;
            try {
                ImageIO.write(image, formatName, new File(path));
            } catch (IOException ioException) {
                r = false;
                ioException.printStackTrace(System.err);
            }
            return r;
        }
    }

    public static class FileUtils {

        public static boolean createIfNotExists(String path) throws SecurityException {
            File file = new File(path);
            if (!file.exists()) {
               if (!file.mkdir()) {
                   return file.mkdirs();
               }
            }
            return true;
        }
    }
}
