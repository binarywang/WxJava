package me.chanjar.weixin.common.util.fs;

import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FileUtilsTest {

  @Test
  public void testCreateTmpFile() throws IOException {
    String strings = "abc";
    File tmpFile = FileUtils.createTmpFile(new ByteArrayInputStream(strings.getBytes()), "name", "txt");
    System.out.println(tmpFile);
    List<String> lines = IOUtils.readLines(Files.newInputStream(tmpFile.toPath()), Charset.defaultCharset());
    assertThat(lines).hasSize(1);
    assertThat(lines.get(0)).isEqualTo(strings);
  }

  @Test
  public void testTestCreateTmpFile() {
  }

  @Test
  public void testImageToBase64ByStream() {
    byte[] original = new byte[5000];
    for (int i = 0; i < original.length; i++) {
      original[i] = (byte) (i % 256);
    }
    // A stream that returns at most 16 bytes per read() call (like a network stream),
    // so a single in.read(buffer) does not fill the buffer.
    InputStream chunked = new InputStream() {
      private final ByteArrayInputStream delegate = new ByteArrayInputStream(original);

      @Override
      public int read() {
        return delegate.read();
      }

      @Override
      public int read(byte[] b, int off, int len) {
        return delegate.read(b, off, Math.min(len, 16));
      }

      @Override
      public int available() {
        return delegate.available();
      }
    };
    String result = FileUtils.imageToBase64ByStream(chunked);
    assertThat(result).isEqualTo(Base64.getEncoder().encodeToString(original));
  }
}
