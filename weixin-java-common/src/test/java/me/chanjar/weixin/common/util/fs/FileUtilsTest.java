package me.chanjar.weixin.common.util.fs;

import me.chanjar.weixin.common.error.WxRuntimeException;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
  public void testCreateTmpFileReusesOneTmpDir() throws IOException {
    File first = FileUtils.createTmpFile(new ByteArrayInputStream("a".getBytes()), "first", "txt");
    File second = FileUtils.createTmpFile(new ByteArrayInputStream("b".getBytes()), "second", "txt");
    try {
      // 每次调用都新建一个临时目录的话，调用方删除文件后目录仍会一直残留
      assertThat(second.getParentFile()).isEqualTo(first.getParentFile());
    } finally {
      assertThat(first.delete()).isTrue();
      assertThat(second.delete()).isTrue();
    }
  }

  @Test
  public void testGetTmpDirIsRecreatedAfterDeletion() throws IOException {
    File tmpDir = FileUtils.getTmpDir();
    assertThat(FileUtils.getTmpDir()).isEqualTo(tmpDir);

    org.apache.commons.io.FileUtils.deleteDirectory(tmpDir);
    assertThat(tmpDir).doesNotExist();

    File recreated = FileUtils.getTmpDir();
    assertThat(recreated).isDirectory();
    File tmpFile = FileUtils.createTmpFile(new ByteArrayInputStream("c".getBytes()), "recreated", "txt");
    assertThat(tmpFile.getParentFile()).isEqualTo(recreated);
    assertThat(tmpFile.delete()).isTrue();
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

  @Test
  public void testImageToBase64ByStreamThrowsOnIoError() {
    InputStream failing = new InputStream() {
      @Override
      public int read() throws IOException {
        throw new IOException("read failed");
      }
    };
    assertThatThrownBy(() -> FileUtils.imageToBase64ByStream(failing))
      .isInstanceOf(WxRuntimeException.class)
      .hasCauseInstanceOf(IOException.class);
  }
}
