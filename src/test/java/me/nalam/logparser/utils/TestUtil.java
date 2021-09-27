package me.nalam.logparser.utils;

import java.io.File;

public class TestUtil {

  private TestUtil() {}

  public static String getAbsPath(String fileName) {
    ClassLoader classLoader = TestUtil.class.getClassLoader();
    File file = new File(classLoader.getResource(fileName).getFile());
    return file.getAbsolutePath();
  }

}
