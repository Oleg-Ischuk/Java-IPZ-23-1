package com.education.ztu;

import java.io.*;

public class Task_4 {

    public static void copyTextFile(String sourcePath, String destPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(sourcePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(destPath))) {

            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }

            System.out.println("Text file copied successfully.");

        } catch (IOException e) {
            System.out.println("Error copying text file: " + e.getMessage());
        }
    }
    public static void copyBinaryFile(String sourcePath, String destPath) {
        try (FileInputStream fis = new FileInputStream(sourcePath);
             FileOutputStream fos = new FileOutputStream(destPath)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Binary file copied successfully.");

        } catch (IOException e) {
            System.out.println("Error copying binary file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        copyTextFile("directory_for_files/sample.txt", "directory_for_files/copy_sample.txt");
        copyBinaryFile("directory_for_files/image.jpg", "directory_for_files/copy_image.jpg");
    }
}
