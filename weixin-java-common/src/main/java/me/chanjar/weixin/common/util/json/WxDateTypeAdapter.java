package me.chanjar.weixin.common.util.json;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Date;

/**
 * <pre>
 * Gson 日期类型转换器
 * Created by Binary Wang on 2017-7-8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxDateTypeAdapter extends TypeAdapter<Date> {
  @Override
  public void write(JsonWriter out, Date value) throws IOException {
    if (value == null) {
      out.nullValue();
    } else {
      out.value(value.getTime() / 1000);
    }
  }

  @Override
  public Date read(JsonReader in) throws IOException {
    JsonToken peek = in.peek();
    switch (peek) {
      case NULL:
        in.nextNull();
        return null;
      case NUMBER:
        long seconds = in.nextLong();
        try {
          return new Date(Math.multiplyExact(seconds, 1000L));
        } catch (ArithmeticException e) {
          throw new JsonParseException("Timestamp seconds out of range: " + seconds, e);
        }
      default:
        throw new JsonParseException("Expected NUMBER but was " + peek);
    }
  }
}
