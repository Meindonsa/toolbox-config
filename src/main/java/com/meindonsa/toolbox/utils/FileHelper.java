package com.meindonsa.toolbox.utils;

import com.meindonsa.toolbox.exception.FunctionalException;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileHelper {

    public static File convert(MultipartFile file, String path) throws IOException {
        File convFile = new File(path + file.getOriginalFilename());
        if (convFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convFile)) {
                fos.write(file.getBytes());
                return convFile;
            }
        }
        return null;
    }

    public static File multipartToFile(MultipartFile multipart, String path) throws IOException {
        File convFile = new File(path + multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

    public static byte[] filesAsBytes(String image) throws IOException {
        InputStream in = ClassLoader.getSystemResourceAsStream(image);
        return StreamUtils.copyToByteArray(in);
    }

    public static Path fileStorageLocation(String filePath) throws FunctionalException {
        Path fileStorageLocation = Paths.get(filePath).toAbsolutePath().normalize();
        try {
            return Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FunctionalException(
                    "Could not create the directory where the uploaded files will be stored.");
        }
    }

    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    public static void resizeImage(File input, String resizedImage, int height, int width)
            throws IOException {
        BufferedImage image = ImageIO.read(input);
        BufferedImage resized = resize(image, height, width);
        File output = new File(resizedImage);
        ImageIO.write(resized, "gif", output);
    }

    public static byte[] getFileContent(String path) {
        if (path != null) {
            File file = new File(path);
            try {
                byte[] fileContent = Files.readAllBytes(file.toPath());
                return fileContent;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void base64ToFile(String base64StringFile, final String pathString)
            throws IOException {
        bytesToFile(Base64.getDecoder().decode(base64StringFile), pathString);
    }

    public static void bytesToFile(byte[] bytes, final String pathString) throws IOException {
        Path path = Paths.get(pathString);
        Files.write(path, bytes);
    }

    public static File bytesArrayToFile(byte[] bytes, final String pathString) throws IOException {
        bytesToFile(bytes, pathString);
        return new File(pathString);
    }

    public static void mergePDF(String mergedFileName, String... files) throws IOException {
//        PDFMergerUtility mergeUtility = new PDFMergerUtility();
//        mergeUtility.setDestinationFileName(mergedFileName);
//        for (String file : files) {
//            mergeUtility.addSource(new File(file));
//        }
//        mergeUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
    }

    public static void mergePDF(String mergedFileName, File... files) throws IOException {
//        PDFMergerUtility mergeUtility = new PDFMergerUtility();
//        mergeUtility.setDestinationFileName(mergedFileName);
//        for (File file : files) {
//            mergeUtility.addSource(file);
//        }
//        mergeUtility.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
    }

    public static void deletePhysicalDocument(String path) {
        try {
            Files.deleteIfExists(Paths.get(path));
        } catch (IOException e) {
            System.out.println(String.format(ErrorMessages.ERR_FILE_NOT_FOUND, path));
        }
    }
}
