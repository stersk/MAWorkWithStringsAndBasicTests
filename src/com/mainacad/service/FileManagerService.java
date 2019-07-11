package com.mainacad.service;

import com.mainacad.model.ConnectionInfo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileManagerService {
  private static final String MAIN_DIR = System.getProperty("user.dir");
  private static final String SEPARATOR = System.getProperty("file.separator");
  private static final String FILE_DIR = getFilesDir();

  private static String getFilesDir() {
    File filesPath = new File(MAIN_DIR + SEPARATOR + "files");
    if (!filesPath.isDirectory()) {
      filesPath.mkdir();
    }

    return MAIN_DIR + SEPARATOR + "files" + SEPARATOR;
  }

  public static byte[] readBytes(String fileName) {
    File file = new File(FILE_DIR + fileName);
    byte[] bytes = null;
    try {
      bytes = Files.readAllBytes(file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return bytes;
  }

  public static void writeBytesToFileNio(byte[] bytes, String fileName) {
    File file = new File(FILE_DIR + fileName);

    try {
      Files.write(file.toPath(), bytes, StandardOpenOption.CREATE);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void copyFile(String fromFile, String toFile) {
    writeBytesToFileNio(readBytes(fromFile), fromFile);
  }

  public static void writeObject(Object object, String fileName) {
    try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_DIR + fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Object readObject(String fileName) {
    try (FileInputStream fileInputStream = new FileInputStream(FILE_DIR + fileName);
    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
      return objectInputStream.readObject();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static void writeText(String fileName, String text, boolean append){
    try (FileWriter fileWriter = new FileWriter(FILE_DIR + fileName, append))
    {
      fileWriter.write(text + "\n");
      fileWriter.flush();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String readText(String fileName){
    String outText = "";

    String line;
    try (FileReader fileReader = new FileReader(FILE_DIR + fileName);
         BufferedReader bufferedReader = new BufferedReader(fileReader)){
      while ((line = bufferedReader.readLine()) != null){
        outText += line + "\n";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return outText;
  }
}
