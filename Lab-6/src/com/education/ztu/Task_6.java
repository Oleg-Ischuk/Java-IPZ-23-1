package com.education.ztu;

import java.io.File;
import java.io.IOException;

public class Task_6 {

    public static void main(String[] args) {
        File parentDir = new File("directory_for_files");
        File innerDir = new File(parentDir, "inner_directory");

        if (!innerDir.exists()) {
            innerDir.mkdir();
        }

        System.out.println("Absolute path: " + innerDir.getAbsolutePath());
        System.out.println("Parent directory: " + innerDir.getParent());

        File file1 = new File(innerDir, "file1.txt");
        File file2 = new File(innerDir, "file2.txt");

        try {
            file1.createNewFile();
            file2.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating files: " + e.getMessage());
        }

        if (file2.exists()) {
            file2.delete();
        }

        File renamedDir = new File(parentDir, "renamed_inner_directory");
        innerDir.renameTo(renamedDir);

        File[] filesList = parentDir.listFiles();
        if (filesList != null) {
            for (File f : filesList) {
                String type = f.isDirectory() ? "Directory" : "File";
                System.out.println(f.getName() + " | Size: " + f.length() + " | Type: " + type);
            }
        }
    }
}
