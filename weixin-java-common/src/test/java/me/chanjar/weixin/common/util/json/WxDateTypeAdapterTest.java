package me.chanjar.weixin.common.util.json;

import com.google.gson.JsonParseException;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link WxDateTypeAdapter}.
 */
public class WxDateTypeAdapterTest {
  private final WxDateTypeAdapter adapter = new WxDateTypeAdapter();

  @Test
  public void testReadTimestampAfter2038() throws IOException {
    Date date = this.adapter.fromJson("4102444800");

    assertThat(date).isEqualTo(new Date(4102444800000L));
  }

  @Test
  public void testReadRejectsPositiveMillisecondOverflow() {
    long overflowingSeconds = Long.MAX_VALUE / 1000 + 1;

    assertThatThrownBy(() -> this.adapter.fromJson(Long.toString(overflowingSeconds)))
        .isInstanceOf(JsonParseException.class)
        .hasMessageContaining("out of range");
  }

  @Test
  public void testReadRejectsNegativeMillisecondOverflow() {
    long overflowingSeconds = Long.MIN_VALUE / 1000 - 1;

    assertThatThrownBy(() -> this.adapter.fromJson(Long.toString(overflowingSeconds)))
        .isInstanceOf(JsonParseException.class)
        .hasMessageContaining("out of range");
  }

  @Test
  public void testWriteUsesSeconds() throws IOException {
    assertThat(this.adapter.toJson(new Date(4102444800123L))).isEqualTo("4102444800");
  }
}
