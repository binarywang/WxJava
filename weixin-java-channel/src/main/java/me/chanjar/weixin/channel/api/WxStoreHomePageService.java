package me.chanjar.weixin.channel.api;

import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.home.background.BackgroundApplyResponse;
import me.chanjar.weixin.channel.bean.home.background.BackgroundGetResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerApplyResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerGetResponse;
import me.chanjar.weixin.channel.bean.home.banner.BannerInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductEditInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductListInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeProductListResponse;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowGetResponse;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowInfo;
import me.chanjar.weixin.channel.bean.home.tree.TreeShowSetResponse;
import me.chanjar.weixin.channel.bean.home.window.WindowProductSettingResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 微信小店 主页管理相关接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxStoreHomePageService {

  /**
   * 添加分类关联的商品
   *
   * @param info 商品分类以及商品id
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse addTreeProduct(TreeProductEditInfo info) throws WxErrorException;

  /**
   * 删除分类关联的商品
   *
   * @param info 商品分类以及商品id
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse delTreeProduct(TreeProductEditInfo info) throws WxErrorException;

  /**
   * 获取分类关联的商品ID列表
   *
   * @param info 分类id、分页大小、分页上下文
   * @return 商品id、分页上下文
   *
   * @throws WxErrorException 异常
   */
  TreeProductListResponse getTreeProductList(TreeProductListInfo info) throws WxErrorException;

  /**
   * 设置展示在店铺主页的商品分类
   *
   * @param info 分类id
   * @return 商品分类审核结果
   *
   * @throws WxErrorException 异常
   */
  TreeShowSetResponse setShowTree(TreeShowInfo info) throws WxErrorException;

  /**
   * 获取展示在店铺主页的商品分类
   *
   * @return 商品分类信息
   *
   * @throws WxErrorException 异常
   */
  TreeShowGetResponse getShowTree() throws WxErrorException;

  /**
   * 获取主页展示商品列表
   *
   * @param pageSize 分页大小
   * @param nextKey  分页上下文
   * @return WindowProductSettingResponse
   *
   * @throws WxErrorException 异常
   */
  WindowProductSettingResponse listWindowProduct(Integer pageSize, String nextKey) throws WxErrorException;

  /**
   * 删除主页展示商品
   *
   * @param productId 商品id
   * @param indexNum  商品重新排序后的新序号，最大移动步长为500（即新序号与当前序号的距离小于500）
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse reorderWindowProduct(String productId, Integer indexNum) throws WxErrorException;

  /**
   * 隐藏小店主页商品
   *
   * @param productId 商品id
   * @param setHide   是否隐藏。1-隐藏，0-取消隐藏
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse hideWindowProduct(String productId, Integer setHide) throws WxErrorException;

  /**
   * 置顶小店主页商品
   *
   * @param productId 商品id
   * @param setTop    是否顶置。1-置顶，0-取消置顶
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse topWindowProduct(String productId, Integer setTop) throws WxErrorException;

  /**
   * 提交背景图申请
   *
   * @param imgUrl 图片链接。请务必使用接口上传图片（参数resp_type=1），并将返回的img_url填入此处，不接受其他任何格式的图片url。
   *               若url曾经做过转换（url前缀为mmecimage.cn/p/），则可以直接提交。
   * @return 申请编号
   *
   * @throws WxErrorException 异常
   * @see WxChannelBasicService#uploadImg(int, String)
   */
  BackgroundApplyResponse applyBackground(String imgUrl) throws WxErrorException;

  /**
   * 查询背景图
   *
   * @return 背景图信息
   * @throws WxErrorException 异常
   */
  BackgroundGetResponse getBackground() throws WxErrorException;

  /**
   * 撤销主页背景图申请
   *
   * @param applyId 申请编号
   * @return BaseResponse
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse cancelBackground(Integer applyId) throws WxErrorException;

  /**
   * 清空主页背景图并撤销流程中的申请
   *
   * @return BaseResponse
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse removeBackground() throws WxErrorException;

  /**
   * 提交精选展示位申请
   *
   * @param info 展示位信息
   * @return 申请编号
   * @throws WxErrorException 异常
   */
  BannerApplyResponse applyBanner(BannerInfo info) throws WxErrorException;

  /**
   * 查询精选展示位
   *
   * @return 展示位信息
   * @throws WxErrorException 异常
   */
  BannerGetResponse getBanner() throws WxErrorException;

  /**
   * 撤销精选展示位申请
   *
   * @param applyId 申请编号
   * @return BaseResponse
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse cancelBanner(Integer applyId) throws WxErrorException;

  /**
   * 清空精选展示位并撤销流程中的申请
   *
   * @return BaseResponse
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse removeBanner() throws WxErrorException;
}
