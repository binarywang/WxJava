package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpTag;
import me.chanjar.weixin.cp.bean.WxCpTpTagAddOrRemoveUsersResult;
import me.chanjar.weixin.cp.bean.WxCpTpTagGetResult;

import java.util.List;

/**
 * <pre>
 *   企业微信第三方开发-标签相关接口
 * </pre>
 *
 * @author zhangq <zhangq002@gmail.com>
 * @since 2021-02-14 16:02
 */
public interface WxCpTpTagService {
  /**
   * 创建标签.
   * <pre>
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=ACCESS_TOKEN
   * 文档地址：https://work.weixin.qq.com/api/doc/90001/90143/90346
   * </pre>
   *
   * @param name 标签名称，长度限制为32个字以内（汉字或英文字母），标签名不可与其他标签重名。
   * @param id   标签id，非负整型，指定此参数时新增的标签会生成对应的标签id，不指定时则以目前最大的id自增。
   * @return 标签id string
   * @throws WxErrorException the wx error exception
   */
  String create(String name, Integer id) throws WxErrorException;

  /**
   * 更新标签.
   *
   * @param tagId   标签id
   * @param tagName 标签名
   * @throws WxErrorException .
   */
  void update(String tagId, String tagName) throws WxErrorException;

  /**
   * 删除标签.
   *
   * @param tagId 标签id
   * @throws WxErrorException .
   */
  void delete(String tagId) throws WxErrorException;

  /**
   * 获取标签成员
   *
   * @param tagId the tag id
   * @return wx cp tp tag get result
   * @throws WxErrorException the wx error exception
   */
  WxCpTpTagGetResult get(String tagId) throws WxErrorException;

  /**
   * 增加标签成员.
   *
   * @param tagId    标签id
   * @param userIds  用户ID 列表
   * @param partyIds 企业部门ID列表
   * @return . wx cp tp tag add or remove users result
   * @throws WxErrorException .
   */
  WxCpTpTagAddOrRemoveUsersResult addUsers2Tag(String tagId, List<String> userIds, List<String> partyIds)
    throws WxErrorException;

  /**
   * 移除标签成员.
   *
   * @param tagId    标签id
   * @param userIds  用户id列表
   * @param partyIds 企业部门ID列表
   * @return . wx cp tp tag add or remove users result
   * @throws WxErrorException .
   */
  WxCpTpTagAddOrRemoveUsersResult removeUsersFromTag(String tagId, List<String> userIds, List<String> partyIds)
    throws WxErrorException;

  /**
   * 获得标签列表.
   *
   * @return 标签列表 list
   * @throws WxErrorException .
   */
  List<WxCpTpTag> listAll() throws WxErrorException;

}
