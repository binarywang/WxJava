# CommonUploadParam 额外表单字段功能使用示例

## 背景

微信公众号在上传永久视频素材时，需要在POST请求中同时提交文件和一个名为`description`的表单字段，该字段包含视频的描述信息（JSON格式）。

根据微信公众号文档：
> 在上传视频素材时需要POST另一个表单，id为description，包含素材的描述信息，内容格式为JSON，格式如下：
> ```json
> {
>     "title": "VIDEO_TITLE",
>     "introduction": "INTRODUCTION"
> }
> ```

## 解决方案

`CommonUploadParam` 类已经扩展支持额外的表单字段，可以在上传文件的同时提交其他表单数据。

## 使用示例

### 1. 基本用法 - 上传永久视频素材

```java
import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.WxMpService;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class VideoMaterialUploadExample {
  
  public void uploadVideoMaterial(WxMpService wxMpService) throws Exception {
    // 准备视频文件
    File videoFile = new File("/path/to/video.mp4");
    
    // 创建上传参数
    CommonUploadParam uploadParam = CommonUploadParam.fromFile("media", videoFile);
    
    // 准备视频描述信息（JSON格式）
    Map<String, String> description = new HashMap<>();
    description.put("title", "我的视频标题");
    description.put("introduction", "这是一个精彩的视频介绍");
    String descriptionJson = WxGsonBuilder.create().toJson(description);
    
    // 添加description表单字段
    uploadParam.addFormField("description", descriptionJson);
    
    // 调用微信API上传
    String url = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=video";
    String response = wxMpService.upload(url, uploadParam);
    
    System.out.println("上传成功：" + response);
  }
}
```

### 2. 链式调用风格

```java
import me.chanjar.weixin.common.bean.CommonUploadParam;
import com.google.gson.JsonObject;

public class ChainStyleExample {
  
  public void uploadWithChainStyle(WxMpService wxMpService) throws Exception {
    File videoFile = new File("/path/to/video.mp4");
    
    // 准备描述信息
    JsonObject description = new JsonObject();
    description.addProperty("title", "视频标题");
    description.addProperty("introduction", "视频介绍");
    
    // 使用链式调用
    String response = wxMpService.upload(
      "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=video",
      CommonUploadParam.fromFile("media", videoFile)
        .addFormField("description", description.toString())
    );
    
    System.out.println("上传成功：" + response);
  }
}
```

### 3. 多个额外表单字段

```java
import me.chanjar.weixin.common.bean.CommonUploadParam;

public class MultipleFormFieldsExample {
  
  public void uploadWithMultipleFields(WxMpService wxMpService) throws Exception {
    File file = new File("/path/to/file.jpg");
    
    // 可以添加多个表单字段
    CommonUploadParam uploadParam = CommonUploadParam.fromFile("media", file)
      .addFormField("field1", "value1")
      .addFormField("field2", "value2")
      .addFormField("field3", "value3");
    
    String response = wxMpService.upload("https://api.weixin.qq.com/some/upload/url", uploadParam);
    
    System.out.println("上传成功：" + response);
  }
}
```

### 4. 从字节数组上传并添加表单字段

```java
import me.chanjar.weixin.common.bean.CommonUploadParam;

public class ByteArrayUploadExample {
  
  public void uploadFromBytes(WxMpService wxMpService) throws Exception {
    // 从字节数组创建上传参数
    byte[] fileBytes = getFileBytes();
    
    CommonUploadParam uploadParam = CommonUploadParam
      .fromBytes("media", "video.mp4", fileBytes)
      .addFormField("description", "{\"title\":\"标题\",\"introduction\":\"介绍\"}");
    
    String response = wxMpService.upload(
      "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=video",
      uploadParam
    );
    
    System.out.println("上传成功：" + response);
  }
  
  private byte[] getFileBytes() {
    // 获取文件字节数组的逻辑
    return new byte[0];
  }
}
```

## API 说明

### CommonUploadParam 类

#### 构造方法
- `fromFile(String name, File file)` - 从文件创建上传参数
- `fromBytes(String name, String fileName, byte[] bytes)` - 从字节数组创建上传参数

#### 方法
- `addFormField(String fieldName, String fieldValue)` - 添加额外的表单字段，返回当前对象支持链式调用
- `getFormFields()` - 获取所有额外的表单字段（Map类型）
- `setFormFields(Map<String, String> formFields)` - 设置额外的表单字段

#### 属性
- `name` - 文件对应的接口参数名称（如：media）
- `data` - 上传数据（CommonUploadData对象）
- `formFields` - 额外的表单字段（可选，Map<String, String>类型）

## 注意事项

1. **表单字段是可选的**：如果不需要额外的表单字段，可以不调用`addFormField`方法
2. **JSON格式**：对于需要JSON格式的表单字段（如description），需要先将对象转换为JSON字符串
3. **编码**：表单字段值会使用UTF-8编码
4. **所有HTTP客户端支持**：该功能在所有HTTP客户端实现中都得到支持（OkHttp、Apache HttpClient、HttpComponents、JoddHttp）

## 兼容性

- 对于通过 `fromFile`、`fromBytes` 等工厂方法创建 `CommonUploadParam` 的代码，本功能在行为层面是向后兼容的，现有代码无需修改即可继续工作。
- 如果之前直接使用构造函数（例如 `new CommonUploadParam(name, data)`）创建对象，由于新增了 `formFields` 字段，构造函数签名可能发生变化，升级后需要改为使用上述工厂方法或根据新构造函数签名调整代码。
