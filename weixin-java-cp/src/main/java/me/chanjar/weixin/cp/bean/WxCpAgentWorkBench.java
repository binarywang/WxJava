package me.chanjar.weixin.cp.bean;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.bean.workbench.WorkBenchKeyData;
import me.chanjar.weixin.cp.bean.workbench.WorkBenchList;
import me.chanjar.weixin.cp.constant.WxCpConsts;

import java.io.Serializable;
import java.util.List;

/**
 * The type Wx cp agent work bench.
 *
 * @author songshiyu  created on  : create in 16:09 2020/9/27 工作台自定义展示
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpAgentWorkBench implements Serializable {
  private static final long serialVersionUID = -4136604790232843229L;

  /**
   * 展示类型，目前支持 “keydata”、 “image”、 “list” 、”webview”
   */
  private String type;
  /**
   * 用户的userid
   */
  private String userId;
  /**
   * 用户的userIds
   */
  private List<String> useridList;
  /**
   * 应用id
   */
  private Long agentId;
  /**
   * 点击跳转url，若不填且应用设置了主页url，则跳转到主页url，否则跳到应用会话窗口
   */
  private String jumpUrl;
  /**
   * 若应用为小程序类型，该字段填小程序pagepath，若未设置，跳到小程序主页
   */
  private String pagePath;
  /**
   * 图片url:图片的最佳比例为3.35:1;webview:渲染展示的url
   */
  private String url;
  /**
   * 是否覆盖用户工作台的数据。设置为true的时候，会覆盖企业所有用户当前设置的数据。若设置为false,则不会覆盖用户当前设置的所有数据
   */
  private Boolean replaceUserData;
  /**
   * 是否开启webview内的链接跳转能力，默认值为false。注意：开启之后，会使jump_url失效。 链接跳转仅支持以下schema方式：wxwork://openurl?url=xxxx，注意url需要进行编码。
   * 参考示例：<a href="wxwork://openurl?url=https%3A%2F%2Fwork.weixin.qq.com%2F">今日要闻</a>
   */
  private Boolean enableWebviewClick;
  /**
   * 高度。可以有两种选择：single_row与double_row。当为single_row时，高度为106px（如果隐藏标题则为147px）。
   * 当为double_row时，高度固定为171px（如果隐藏标题则为212px）。默认值为double_row
   */
  private String height;
  /**
   * 是否要隐藏展示了应用名称的标题部分，默认值为false。
   */
  private Boolean hideTitle;

  private List<WorkBenchKeyData> keyDataList;

  private List<WorkBenchList> lists;

  /**
   * 生成模板Json字符串
   *
   * @return the string
   */
  public String toTemplateString() {
    JsonObject templateObject = new JsonObject();
    templateObject.addProperty("agentid", this.agentId);
    templateObject.addProperty("type", this.type);
    if (this.replaceUserData != null) {
      templateObject.addProperty("replace_user_data", this.replaceUserData);
    }
    this.handle(templateObject);
    return templateObject.toString();
  }

  /**
   * 生成用户数据Json字符串
   *
   * @return the string
   */
  public String toUserDataString() {
    JsonObject userDataObject = new JsonObject();
    userDataObject.addProperty("agentid", this.agentId);
    userDataObject.addProperty("userid", this.userId);
    userDataObject.addProperty("type", this.type);
    this.handle(userDataObject);
    return userDataObject.toString();
  }

  /**
   * 生成批量用户数据Json字符串
   *
   * @return the string
   */
  public String toBatchUserDataString() {
    JsonObject userDataObject = new JsonObject();
    userDataObject.addProperty("agentid", this.agentId);
    JsonArray useridList = WxGsonBuilder.create().toJsonTree(this.useridList).getAsJsonArray();
    userDataObject.add("userid_list", useridList);
    this.handleBatch(userDataObject);
    return userDataObject.toString();
  }

  /**
   * 处理不用类型的工作台数据
   */
  private void handle(JsonObject templateObject) {
    switch (this.getType()) {
      case WxCpConsts.WorkBenchType.KEYDATA: {
        JsonArray keyDataArray = new JsonArray();
        JsonObject itemsObject = new JsonObject();
        for (WorkBenchKeyData keyDataItem : this.keyDataList) {
          JsonObject keyDataObject = new JsonObject();
          keyDataObject.addProperty("key", keyDataItem.getKey());
          keyDataObject.addProperty("data", keyDataItem.getData());
          keyDataObject.addProperty("jump_url", keyDataItem.getJumpUrl());
          keyDataObject.addProperty("pagepath", keyDataItem.getPagePath());
          keyDataArray.add(keyDataObject);
        }
        itemsObject.add("items", keyDataArray);
        templateObject.add("keydata", itemsObject);
        break;
      }
      case WxCpConsts.WorkBenchType.IMAGE: {
        JsonObject image = new JsonObject();
        image.addProperty("url", this.url);
        image.addProperty("jump_url", this.jumpUrl);
        image.addProperty("pagepath", this.pagePath);
        templateObject.add("image", image);
        break;
      }
      case WxCpConsts.WorkBenchType.LIST: {
        JsonArray listArray = new JsonArray();
        JsonObject itemsObject = new JsonObject();
        for (WorkBenchList listItem : this.lists) {
          JsonObject listObject = new JsonObject();
          listObject.addProperty("title", listItem.getTitle());
          listObject.addProperty("jump_url", listItem.getJumpUrl());
          listObject.addProperty("pagepath", listItem.getPagePath());
          listArray.add(listObject);
        }
        itemsObject.add("items", listArray);
        templateObject.add("list", itemsObject);
        break;
      }
      case WxCpConsts.WorkBenchType.WEBVIEW: {
        JsonObject webview = new JsonObject();
        webview.addProperty("url", this.url);
        webview.addProperty("jump_url", this.jumpUrl);
        webview.addProperty("pagepath", this.pagePath);
        webview.addProperty("enable_webview_click", this.enableWebviewClick);
        webview.addProperty("height", this.height);
        webview.addProperty("hide_title", this.hideTitle);
        templateObject.add("webview", webview);
        break;
      }
      default: {
        //do nothing
      }
    }
  }

  /**
   * 处理不用类型的工作台数据
   */
  private void handleBatch(JsonObject templateObject) {
    switch (this.getType()) {
      case WxCpConsts.WorkBenchType.KEYDATA: {
        JsonArray keyDataArray = new JsonArray();
        JsonObject itemsObject = new JsonObject();
        for (WorkBenchKeyData keyDataItem : this.keyDataList) {
          JsonObject keyDataObject = new JsonObject();
          keyDataObject.addProperty("key", keyDataItem.getKey());
          keyDataObject.addProperty("data", keyDataItem.getData());
          keyDataObject.addProperty("jump_url", keyDataItem.getJumpUrl());
          keyDataObject.addProperty("pagepath", keyDataItem.getPagePath());
          keyDataArray.add(keyDataObject);
        }
        itemsObject.add("items", keyDataArray);
        JsonObject dataObject = new JsonObject();
        dataObject.addProperty("type", WxCpConsts.WorkBenchType.KEYDATA);
        dataObject.add("keydata", itemsObject);
        templateObject.add("data", dataObject);
        break;
      }
      case WxCpConsts.WorkBenchType.IMAGE: {
        JsonObject image = new JsonObject();
        image.addProperty("url", this.url);
        image.addProperty("jump_url", this.jumpUrl);
        image.addProperty("pagepath", this.pagePath);
        JsonObject dataObject = new JsonObject();
        dataObject.addProperty("type", WxCpConsts.WorkBenchType.IMAGE);
        dataObject.add("image", image);
        templateObject.add("data", dataObject);
        break;
      }
      case WxCpConsts.WorkBenchType.LIST: {
        JsonArray listArray = new JsonArray();
        JsonObject itemsObject = new JsonObject();
        for (WorkBenchList listItem : this.lists) {
          JsonObject listObject = new JsonObject();
          listObject.addProperty("title", listItem.getTitle());
          listObject.addProperty("jump_url", listItem.getJumpUrl());
          listObject.addProperty("pagepath", listItem.getPagePath());
          listArray.add(listObject);
        }
        itemsObject.add("items", listArray);
        JsonObject dataObject = new JsonObject();
        dataObject.addProperty("type", WxCpConsts.WorkBenchType.LIST);
        dataObject.add("list", itemsObject);
        templateObject.add("data", dataObject);
        break;
      }
      case WxCpConsts.WorkBenchType.WEBVIEW: {
        JsonObject webview = new JsonObject();
        webview.addProperty("url", this.url);
        webview.addProperty("jump_url", this.jumpUrl);
        webview.addProperty("pagepath", this.pagePath);
        webview.addProperty("enable_webview_click", this.enableWebviewClick);
        webview.addProperty("height", this.height);
        webview.addProperty("hide_title", this.hideTitle);
        JsonObject dataObject = new JsonObject();
        dataObject.addProperty("type", WxCpConsts.WorkBenchType.WEBVIEW);
        dataObject.add("webview", webview);
        templateObject.add("data", dataObject);
        break;
      }
      default: {
        //do nothing
      }
    }
  }

}
