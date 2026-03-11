package me.chanjar.weixin.open.bean.auth;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 小程序认证 查询个人认证身份选项列表 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
@Getter
@Setter
@NoArgsConstructor
public class MaAuthQueryIdentityTreeResult extends WxOpenResult {

  /**
   * 子节点信息 非叶子节点必有
   */
  @Nullable
  @SerializedName("identity_tree_list")
  private List<MaAuthQueryIdentityTreeResultIdentityNode> nodeList;
}
