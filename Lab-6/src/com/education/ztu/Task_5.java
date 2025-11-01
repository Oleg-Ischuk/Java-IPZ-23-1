package com.education.ztu;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Task_5 {

    public static void main(String[] args) {
        String filePath = "directory_for_files/products.txt";

        try {
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");

            byte[] oldContent = new byte[(int) raf.length()];
            raf.readFully(oldContent);
            String content = new String(oldContent);

            raf.setLength(0);

            if (!content.startsWith("=== Product List ===")) {
                raf.writeBytes("=== Product List ===\n");
            }

            raf.writeBytes(content.trim());
            raf.writeBytes("\nOrange - 3 pcs");
            raf.writeBytes("\nCheese - 1 pack");

            raf.close();
            System.out.println("Text successfully updated.");
        } catch (IOException e) {
            System.out.println("Error working with file: " + e.getMessage());
        }
    }
}
