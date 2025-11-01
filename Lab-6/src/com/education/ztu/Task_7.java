package com.education.ztu;

import java.io.*;
import java.util.zip.*;

public class Task_7 {

    public static void main(String[] args) {
        String sourceDir = "directory_for_files";
        String zipFile = "directory_for_files/archive.zip";

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            File dir = new File(sourceDir);
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && !file.getName().equals("archive.zip")) {
                        try (FileInputStream fis = new FileInputStream(file)) {
                            ZipEntry entry = new ZipEntry(file.getName());
                            zos.putNextEntry(entry);

                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = fis.read(buffer)) != -1) {
                                zos.write(buffer, 0, len);
                            }

                            zos.closeEntry();
                        }
                    }
                }
            }

            System.out.println("Archive created successfully.");

        } catch (IOException e) {
            System.out.println("Error creating archive: " + e.getMessage());
        }

        try (FileInputStream fis = new FileInputStream(zipFile);
             ZipInputStream zis = new ZipInputStream(fis)) {

            System.out.println("Files in archive:");
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }

        } catch (IOException e) {
            System.out.println("Error reading archive: " + e.getMessage());
        }
    }
}
