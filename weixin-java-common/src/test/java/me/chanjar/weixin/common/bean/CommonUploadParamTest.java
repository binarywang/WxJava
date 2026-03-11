package me.chanjar.weixin.common.bean;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * CommonUploadParam 单元测试
 *
 * @author Binary Wang
 */
@Test
public class CommonUploadParamTest {

  @Test
  public void testFromFile() {
    File file = new File("test.txt");
    CommonUploadParam param = CommonUploadParam.fromFile("media", file);

    Assert.assertNotNull(param);
    Assert.assertEquals(param.getName(), "media");
    Assert.assertNotNull(param.getData());
    Assert.assertNull(param.getFormFields());
  }

  @Test
  public void testFromBytes() {
    byte[] bytes = "test content".getBytes();
    CommonUploadParam param = CommonUploadParam.fromBytes("media", "test.txt", bytes);

    Assert.assertNotNull(param);
    Assert.assertEquals(param.getName(), "media");
    Assert.assertNotNull(param.getData());
    Assert.assertEquals(param.getData().getFileName(), "test.txt");
    Assert.assertNull(param.getFormFields());
  }

  @Test
  public void testAddFormField() {
    File file = new File("test.txt");
    CommonUploadParam param = CommonUploadParam.fromFile("media", file);

    // 添加单个表单字段
    param.addFormField("title", "测试标题");

    Assert.assertNotNull(param.getFormFields());
    Assert.assertEquals(param.getFormFields().size(), 1);
    Assert.assertEquals(param.getFormFields().get("title"), "测试标题");

    // 添加多个表单字段
    param.addFormField("introduction", "测试介绍");

    Assert.assertEquals(param.getFormFields().size(), 2);
    Assert.assertEquals(param.getFormFields().get("introduction"), "测试介绍");
  }

  @Test
  public void testAddFormFieldChaining() {
    File file = new File("test.txt");
    CommonUploadParam param = CommonUploadParam.fromFile("media", file)
      .addFormField("title", "测试标题")
      .addFormField("introduction", "测试介绍");

    Assert.assertNotNull(param.getFormFields());
    Assert.assertEquals(param.getFormFields().size(), 2);
    Assert.assertEquals(param.getFormFields().get("title"), "测试标题");
    Assert.assertEquals(param.getFormFields().get("introduction"), "测试介绍");
  }

  @Test
  public void testConstructorWithFormFields() {
    CommonUploadData data = new CommonUploadData("test.txt", null, 0);
    Map<String, String> formFields = new HashMap<>();
    formFields.put("title", "测试标题");
    formFields.put("introduction", "测试介绍");

    CommonUploadParam param = new CommonUploadParam("media", data, formFields);

    Assert.assertNotNull(param.getFormFields());
    Assert.assertEquals(param.getFormFields().size(), 2);
    Assert.assertEquals(param.getFormFields().get("title"), "测试标题");
    Assert.assertEquals(param.getFormFields().get("introduction"), "测试介绍");
  }

  @Test
  public void testToString() {
    File file = new File("test.txt");
    CommonUploadParam param = CommonUploadParam.fromFile("media", file)
      .addFormField("title", "测试标题");

    String str = param.toString();
    Assert.assertTrue(str.contains("name:media"));
    Assert.assertTrue(str.contains("formFields:"));
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAddFormFieldWithNullFieldName() {
    File file = new File("test.txt");
    CommonUploadParam param = CommonUploadParam.fromFile("media", file);
    param.addFormField(null, "value");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAddFormFieldWithEmptyFieldName() {
    File file = new File("test.txt");
    CommonUploadParam param = CommonUploadParam.fromFile("media", file);
    param.addFormField("", "value");
  }

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAddFormFieldWithNullFieldValue() {
    File file = new File("test.txt");
    CommonUploadParam param = CommonUploadParam.fromFile("media", file);
    param.addFormField("fieldName", null);
  }
}
