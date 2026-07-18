package me.chanjar.weixin.common.util.json;

import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * GsonHelper 的单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-09-04
 */
public class GsonHelperTest {

  @Test
  public void testIsNull() {
  }

  @Test
  public void testIsNotNull() {
  }

  @Test
  public void testGetLong() {
  }

  @Test
  public void testGetPrimitiveLong() {
  }

  @Test
  public void testGetInteger() {
  }

  @Test
  public void testGetPrimitiveInteger() {
  }

  @Test
  public void testGetDouble() {
  }

  @Test
  public void testGetPrimitiveDouble() {
  }

  @Test
  public void testGetFloat() {
  }

  @Test
  public void testGetPrimitiveFloat() {
  }

  @Test
  public void testGetBoolean() {
  }

  @Test
  public void testGetString() {
  }

  @Test
  public void testGetAsString() {
  }

  @Test
  public void testGetAsLong() {
  }

  @Test
  public void testGetAsPrimitiveLong() {
  }

  @Test
  public void testGetAsInteger() {
  }

  @Test
  public void testGetAsPrimitiveInt() {
  }

  @Test
  public void testGetAsBoolean() {
  }

  @Test
  public void testGetAsPrimitiveBool() {
  }

  @Test
  public void testGetAsDouble() {
  }

  @Test
  public void testGetAsPrimitiveDouble() {
  }

  @Test
  public void testGetAsFloat() {
  }

  @Test
  public void testGetAsPrimitiveFloat() {
  }

  @Test
  public void testGetIntArray() {
  }

  @Test
  public void testGetStringArray() {
  }

  @Test
  public void testGetLongArray() {
  }

  @Test
  public void testGetAsJsonArray() {
  }

  @Test
  public void testBuildSimpleJsonObject() {
    try {
      GsonHelper.buildJsonObject(1, 2, 3);
    } catch (RuntimeException e) {
      assertThat(e.getMessage()).isEqualTo("参数个数必须为偶数");
    }

    System.out.println(GsonHelper.buildJsonObject(1, 2));
    System.out.println(GsonHelper.buildJsonObject(1, null));
    System.out.println(GsonHelper.buildJsonObject("int", 1, "float", 2.1f, "double", 2.5));
    System.out.println(GsonHelper.buildJsonObject("boolean", true, "string", "1av"));
    System.out.println(GsonHelper.buildJsonObject(1, true, "jsonElement", new JsonObject()));
    System.out.println(GsonHelper.buildJsonObject("num", 2, "string", "cde", "char", 'a', "bool", true));
  }

  /**
   * If the JSON property exists but is NOT a JSON array (e.g. a plain string),
   * getIntArray should return null gracefully rather than throwing ClassCastException.
   */
  @Test
  public void testGetIntArrayNonArrayProperty() {
    JsonObject json = new JsonObject();
    json.addProperty("items", "not-an-array");
    assertThat(GsonHelper.getIntArray(json, "items")).isNull();
  }

  @Test
  public void testGetStringArrayNonArrayProperty() {
    JsonObject json = new JsonObject();
    json.addProperty("items", 123);
    assertThat(GsonHelper.getStringArray(json, "items")).isNull();
  }

  @Test
  public void testGetLongArrayNonArrayProperty() {
    JsonObject json = new JsonObject();
    json.addProperty("items", "not-an-array");
    assertThat(GsonHelper.getLongArray(json, "items")).isNull();
  }
}
