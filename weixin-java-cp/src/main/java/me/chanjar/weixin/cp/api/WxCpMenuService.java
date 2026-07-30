package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * <pre>
 *  菜单管理相关接口
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpMenuService {

  /**
   * <pre>
   * 自定义菜单创建接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=自定义菜单创建接口
   *
   * 注意: 这个方法使用WxCpConfigStorage里的agentId
   * </pre>
   *
   * @param menu 菜单对象
   * @throws WxErrorException the wx error exception
   * @see #create(Integer, WxMenu) #create(Integer, WxMenu)
   */
  void create(WxMenu menu) throws WxErrorException;

  /**
   * <pre>
   * 自定义菜单创建接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=自定义菜单创建接口
   *
   * 注意: 这个方法不使用WxCpConfigStorage里的agentId，需要开发人员自己给出
   * </pre>
   *
   * @param agentId 企业号应用的id
   * @param menu    菜单对象
   * @throws WxErrorException the wx error exception
   * @see #create(me.chanjar.weixin.common.bean.menu.WxMenu) #create(me.chanjar.weixin.common.bean.menu.WxMenu)
   */
  void create(Long agentId, WxMenu menu) throws WxErrorException;

  /**
   * <pre>
   * 自定义菜单删除接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=自定义菜单删除接口
   *
   * 注意: 这个方法使用WxCpConfigStorage里的agentId
   * </pre>
   *
   * @throws WxErrorException the wx error exception
   * @see #delete(Integer) #delete(Integer)
   */
  void delete() throws WxErrorException;

  /**
   * <pre>
   * 自定义菜单删除接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=自定义菜单删除接口
   *
   * 注意: 这个方法不使用WxCpConfigStorage里的agentId，需要开发人员自己给出
   * </pre>
   *
   * @param agentId 企业号应用的id
   * @throws WxErrorException the wx error exception
   * @see #delete() #delete()
   */
  void delete(Long agentId) throws WxErrorException;

  /**
   * <pre>
   * 自定义菜单查询接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=自定义菜单查询接口
   *
   * 注意: 这个方法使用WxCpConfigStorage里的agentId
   * </pre>
   *
   * @return the wx menu
   * @throws WxErrorException the wx error exception
   * @see #get(Integer) #get(Integer)
   */
  WxMenu get() throws WxErrorException;

  /**
   * <pre>
   * 自定义菜单查询接口
   * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=自定义菜单查询接口
   *
   * 注意: 这个方法不使用WxCpConfigStorage里的agentId，需要开发人员自己给出
   * </pre>
   *
   * @param agentId 企业号应用的id
   * @return the wx menu
   * @throws WxErrorException the wx error exception
   * @see #get() #get()
   */
  WxMenu get(Long agentId) throws WxErrorException;
}
