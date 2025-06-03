package com.github.binarywang.wxpay.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

/**
 * @author Binary Wang
 */
@UtilityClass
public class ZipUtils {

  /**
   * 解压gzip文件
   */
  public static File unGzip(final File file) throws IOException {
    File resultFile = new File(FilenameUtils.removeExtension(file.getAbsolutePath()));
    resultFile.createNewFile();

    try (FileOutputStream fos = new FileOutputStream(resultFile);
         GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(file))) {
      IOUtils.copy(gzis, fos);
    }

    return resultFile;
  }
}
