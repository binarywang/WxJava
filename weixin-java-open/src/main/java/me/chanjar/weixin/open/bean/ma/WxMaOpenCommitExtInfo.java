package me.chanjar.weixin.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信小程序三方平台代上传代码提交额外信息对象
 * <p>
 * 如果代码中已经有配置，则配置的合并规则为：除了pages和tabBar.list直接覆盖原配置，其他都为插入或同级覆盖。
 * extjson 详细说明
 * https://developers.weixin.qq.com/miniprogram/dev/devtools/ext.html#%E5%B0%8F%E7%A8%8B%E5%BA%8F%E6%A8%A1%E6%9D%BF%E5%BC%80%E5%8F%91
 * </p>
 *
 * <p>
 * ext_json补充说明
 * 文档地址:https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/code/commit.html
 * 为了便于第三方平台使用同一个小程序模板为不同的小程序提供服务，第三方可以将自定义信息放置在 ext_json 中，在模板小程序中，可以使用 wx.getExtConfigSync 接口获取自定义信息，从而区分不同的小程序。详见：小程序模板开发
 * ext_json 中的参数可选，参数详见小程序配置;但是，如果是模板id为标准模板库的模板id，则ext_json可支持的参数为：{"extAppid":'', "ext": {}, "window": {}}
 * ext_json 中有限支持 pages，支持配置模板页面的子集（ext_json 中不可新增页面）。
 * ext_json 中有限支持 subPackages，支持配置模板分包及其页面的子集（ext_json 中配置的分包必须已声明于模板中，且不可新增分包页面）。
 * ext_json支持plugins配置，该配置会覆盖模板中的app.json中的plugins配置。关于plugin的使用详情请参考使用插件。
 * 如果代码中已经有配置，则配置的合并规则为：
 * ext整体替换
 * pages整体替换
 * extPages中找到对应页面，同级覆盖page.json
 * window同级覆盖
 * extAppid直接加到app.json
 * networkTimeout同级覆盖
 * customOpen整体替换
 * tabbar同级覆盖
 * functionPages整体替换
 * subPackages整体替换
 * navigateToMiniProgaramAppIdList：整体替换
 * plugins整体替换
 * </p>
 *
 * @author yqx
 * created on  2018/9/13
 */
@Data
public class WxMaOpenCommitExtInfo implements Serializable {

  WxMaOpenCommitExtInfo() {

  }

  /**
   * 授权小程序Appid，可填入商户小程序AppID，以区分不同商户
   */
  private String extAppid;

  /**
   * 配置 ext.json 是否生效
   */
  private Boolean extEnable = Boolean.TRUE;

  /**
   * 是否直接提交到待审核列表
   */
  private Boolean directCommit = Boolean.FALSE;

  @SerializedName("ext")
  private Map<String, Object> extMap;

  @SerializedName("extPages")
  private Map<String, WxMaOpenPage> extPages;

  /**
   * 页面路径列表(和app.json结构一致)
   */
  @SerializedName("pages")
  private List<String> pageList;

  /**
   * 分包结构配置
   */
  @SerializedName("subpackages")
  private List<WxMaOpenSubpackage> subpackageList;

  @SerializedName("window")
  private WxMaOpenWindow window;

  @SerializedName("networkTimeout")
  private WxMaOpenNetworkTimeout networkTimeout;

  @SerializedName("tabBar")
  private WxMaOpenTabBar tabBar;

  /**
   * 关于新增 requiredPrivateInfos 说明
   * 关于地理位置接口新增与相关流程调整可以查看社区公告：
   * <a href="https://developers.weixin.qq.com/community/develop/doc/000a02f2c5026891650e7f40351c01">点击查看</a>
   * 7.14后，在代码中使用的地理位置相关接口（共计 8 个，见表1），第三方开发者均需要在 ext_json 参数中 requiredPrivateInfos 配置项中声明
   * 在ext_json参数中配置requiredPrivateInfos，其规则为「整体替换」。即如果在 app.json 里也配置了，那么最终会是ext_json的配置会覆盖 app.json
   * 配置的requiredPrivateInfos。其余规则可查看下方的「ext_json补充说明」
   * 在ext_json参数中配置 requiredPrivateInfos 示例如下
   * {
   * "template_id": "95",
   * "ext_json": "{\"requiredPrivateInfos\":[\"onLocationChange\",\"startLocationUpdate\"]}",
   * "user_version": "V1.0",
   * "user_desc": "test"
   * }
   * requiredPrivateInfos主要会检查格式是否正确，填入的 api 名称是否正确，填入的 api 名称是否有权限，填入的 api 名称是否互斥。对应的错误码可查看文档末尾的错误码文档。
   * requiredPrivateInfos在2022.7.14后才会生效，文档提前更新是为了方便开发者可以提前了解接口的参数变更规则，提前进行调整。
   */
  @SerializedName("requiredPrivateInfos")
  private String[] requiredPrivateInfos;

  /**
   * 添加扩展项
   *
   * @param key
   * @param value
   */
  public void addExt(String key, String value) {
    if (extMap == null) {
      extMap = new HashMap<>();
    }
    if (StringUtils.isNoneBlank(key, value)) {
      extMap.put(key, value);
    }
  }

  /**
   * 添加扩展页面
   *
   * @param pagePath
   * @param page
   */
  public void addExtPage(String pagePath, WxMaOpenPage page) {
    if (extPages == null) {
      extPages = new HashMap<>();
    }
    if (StringUtils.isNotBlank(pagePath) && page != null) {
      extPages.put(pagePath, page);
    }
  }

  /**
   * 添加页面
   *
   * @param pagePath
   */
  public void addPage(String pagePath) {
    if (pageList == null) {
      pageList = new ArrayList<>();
    }
    if (StringUtils.isNotBlank(pagePath)) {
      pageList.add(pagePath);
    }
  }

  public static WxMaOpenCommitExtInfo INSTANCE() {
    return new WxMaOpenCommitExtInfo();
  }

  public String toJson() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
