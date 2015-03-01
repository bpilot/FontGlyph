package com.radicalninja.fontmaker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    public static String readFile(String filePath) throws IOException {
        StringBuffer dataFromFile = new StringBuffer(1000);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        char[] cbuf = new char[1024];
        int numRead;
        while ((numRead = reader.read(cbuf)) != -1) {
            String readData = String.valueOf(cbuf, 0, numRead);
            dataFromFile.append(readData);
            cbuf = new char[1024];
        }
        reader.close();
        return dataFromFile.toString();
    }

    public static void writeFile(String filePath, String content) throws IOException {
        File outFile = new File(filePath);
        outFile.createNewFile();
        FileWriter writer = new FileWriter(outFile);
        writer.write(content);
        writer.flush();
        writer.close();
    }

    public static void copyFile(String sourcePath, String targetPath) throws IOException {

        InputStream in = new FileInputStream(sourcePath);
        File outFile = new File(targetPath);
        outFile.createNewFile();
        OutputStream out = new FileOutputStream(outFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
    }

}