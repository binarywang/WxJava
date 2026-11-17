package me.chanjar.weixin.common.util.fs;

import me.chanjar.weixin.common.error.WxRuntimeException;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Base64;

import static org.apache.commons.io.FileUtils.openOutputStream;

/**
 * @author Daniel Qian
 */
public class FileUtils {
  private static volatile File tmpDir;

  /**
   * 创建临时文件.
   *
   * @param inputStream 输入文件流
   * @param name        文件名
   * @param ext         扩展名
   * @param tmpDirFile  临时文件夹目录
   */
  public static File createTmpFile(InputStream inputStream, String name, String ext, File tmpDirFile)
    throws IOException {
    File resultFile = File.createTempFile(name, '.' + ext, tmpDirFile);

    resultFile.deleteOnExit();
    copyToFile(inputStream, resultFile);
    return resultFile;
  }

  private static void copyToFile(final InputStream source, final File destination) throws IOException {
    try (InputStream in = source; OutputStream out = openOutputStream(destination)) {
      IOUtils.copy(in, out);
    }
  }

  /**
   * 创建临时文件，文件位于 {@link #getTmpDir()} 返回的目录中.
   *
   * @param inputStream 输入文件流
   * @param name        文件名
   * @param ext         扩展名
   */
  public static File createTmpFile(InputStream inputStream, String name, String ext) throws IOException {
    return createTmpFile(inputStream, name, ext, getTmpDir());
  }

  /**
   * 获取 SDK 共用的临时目录.
   * <p>
   * 目录在首次调用时创建，之后的调用都复用它，不会每次调用都新建一个永远不会被删除的临时目录；
   * 目录被外部清理掉后，下次调用会重新创建。
   * </p>
   *
   * @return 临时目录
   * @throws IOException 无法创建临时目录时抛出
   */
  public static File getTmpDir() throws IOException {
    File dir = tmpDir;
    if (dir == null || !dir.isDirectory()) {
      synchronized (FileUtils.class) {
        dir = tmpDir;
        if (dir == null || !dir.isDirectory()) {
          dir = Files.createTempDirectory("wxjava-temp").toFile();
          dir.deleteOnExit();
          tmpDir = dir;
        }
      }
    }
    return dir;
  }

  /**
   * 文件流生成base64
   *
   * @param in 文件流
   * @return base64编码
   * @throws WxRuntimeException 读取文件流失败时抛出
   */
  public static String imageToBase64ByStream(InputStream in) {
    // 读取图片字节数组
    try (InputStream input = in) {
      // 返回Base64编码过的字节数组字符串
      return Base64.getEncoder().encodeToString(IOUtils.toByteArray(input));
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

}
