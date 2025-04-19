package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import me.chanjar.weixin.cp.bean.school.user.*;

import java.util.List;

/**
 * 企业微信家校沟通相关接口.
 * https://developer.work.weixin.qq.com/document/path/91638
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a> created on : 2022/6/18 9:10
 */
public interface WxCpSchoolUserService {

  /**
   * 获取访问用户身份
   * 该接口用于根据code获取成员信息
   * <p>
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE
   *
   * @param code the code
   * @return user info
   * @throws WxErrorException the wx error exception
   */
  WxCpOauth2UserInfo getUserInfo(@NonNull String code) throws WxErrorException;

  /**
   * 获取家校访问用户身份
   * 该接口用于根据code获取家长或者学生信息
   * <p>
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/getuserinfo?access_token=ACCESS_TOKEN&code=CODE
   *
   * @param code the code
   * @return school user info
   * @throws WxErrorException the wx error exception
   */
  WxCpOauth2UserInfo getSchoolUserInfo(@NonNull String code) throws WxErrorException;

  /**
   * 创建学生
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/create_student?access_token=ACCESS_TOKEN
   *
   * @param studentUserId the student user id
   * @param name          the name
   * @param departments   the departments
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp createStudent(@NonNull String studentUserId, @NonNull String name, @NonNull List<Integer> departments) throws WxErrorException;

  /**
   * 批量创建学生
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/batch_create_student?access_token=ACCESS_TOKEN
   *
   * @param request the request
   * @return wx cp batch result list
   * @throws WxErrorException the wx error exception
   */
  WxCpBatchResultList batchCreateStudent(@NonNull WxCpBatchCreateStudentRequest request) throws WxErrorException;

  /**
   * 批量删除学生
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/batch_delete_student?access_token=ACCESS_TOKEN
   *
   * @param request the request
   * @return wx cp batch result list
   * @throws WxErrorException the wx error exception
   */
  WxCpBatchResultList batchDeleteStudent(@NonNull WxCpBatchDeleteStudentRequest request) throws WxErrorException;

  /**
   * 批量更新学生
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/batch_update_student?access_token=ACCESS_TOKEN
   *
   * @param request the request
   * @return wx cp batch result list
   * @throws WxErrorException the wx error exception
   */
  WxCpBatchResultList batchUpdateStudent(@NonNull WxCpBatchUpdateStudentRequest request) throws WxErrorException;

  /**
   * 删除学生
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/delete_student?access_token=ACCESS_TOKEN&userid=USERID
   *
   * @param studentUserId the student user id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp deleteStudent(@NonNull String studentUserId) throws WxErrorException;

  /**
   * 更新学生
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/update_student?access_token=ACCESS_TOKEN
   *
   * @param studentUserId    the student user id
   * @param newStudentUserId the new student user id
   * @param name             the name
   * @param departments      the departments
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp updateStudent(@NonNull String studentUserId, String newStudentUserId, String name,
                             List<Integer> departments) throws WxErrorException;

  /**
   * 创建家长
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/create_parent?access_token=ACCESS_TOKEN
   *
   * @param request the request
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp createParent(@NonNull WxCpCreateParentRequest request) throws WxErrorException;

  /**
   * 批量创建家长
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/batch_create_parent?access_token=ACCESS_TOKEN
   *
   * @param request the request
   * @return wx cp batch result list
   * @throws WxErrorException the wx error exception
   */
  WxCpBatchResultList batchCreateParent(@NonNull WxCpBatchCreateParentRequest request) throws WxErrorException;

  /**
   * 批量删除家长
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/batch_delete_parent?access_token=ACCESS_TOKEN
   *
   * @param userIdList the user id list
   * @return wx cp batch result list
   * @throws WxErrorException the wx error exception
   */
  WxCpBatchResultList batchDeleteParent(@NonNull String... userIdList) throws WxErrorException;

  /**
   * 批量更新家长
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/batch_update_parent?access_token=ACCESS_TOKEN
   *
   * @param request the request
   * @return wx cp batch result list
   * @throws WxErrorException the wx error exception
   */
  WxCpBatchResultList batchUpdateParent(@NonNull WxCpBatchUpdateParentRequest request) throws WxErrorException;

  /**
   * 读取学生或家长
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/get?access_token=ACCESS_TOKEN&userid=USERID
   *
   * @param userId the user id
   * @return user
   * @throws WxErrorException the wx error exception
   */
  WxCpUserResult getUser(@NonNull String userId) throws WxErrorException;

  /**
   * 获取部门成员详情
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID
   * &fetch_child=FETCH_CHILD
   *
   * @param departmentId 获取的部门id
   * @param fetchChild   1/0：是否递归获取子部门下面的成员
   * @return user list
   * @throws WxErrorException the wx error exception
   */
  WxCpUserListResult getUserList(@NonNull Integer departmentId, Integer fetchChild) throws WxErrorException;

  /**
   * 获取部门家长详情
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/list_parent?access_token=ACCESS_TOKEN&department_id
   * =DEPARTMENT_ID
   *
   * @param departmentId 获取的部门id
   * @return user list parent
   * @throws WxErrorException the wx error exception
   */
  WxCpListParentResult getUserListParent(@NonNull Integer departmentId) throws WxErrorException;

  /**
   * 更新家长
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/update_parent?access_token=ACCESS_TOKEN
   *
   * @param request the request
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp updateParent(@NonNull WxCpUpdateParentRequest request) throws WxErrorException;

  /**
   * 删除家长
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/user/delete_parent?access_token=ACCESS_TOKEN&userid=USERID
   *
   * @param userId the user id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp deleteParent(@NonNull String userId) throws WxErrorException;

  /**
   * 设置家校通讯录自动同步模式
   * 企业和第三方可通过此接口修改家校通讯录与班级标签之间的自动同步模式，注意，一旦设置禁止自动同步，将无法再次开启。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/set_arch_sync_mode?access_token=ACCESS_TOKEN
   *
   * @param archSyncMode 家校通讯录同步模式：1-禁止将标签同步至家校通讯录，2-禁止将家校通讯录同步至标签，3-禁止家校通讯录和标签相互同步
   * @return arch sync mode
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp setArchSyncMode(@NonNull Integer archSyncMode) throws WxErrorException;

  /**
   * 创建部门
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/department/create?access_token=ACCESS_TOKEN
   *
   * @param request 请求参数对象
   * @return wx cp create department
   * @throws WxErrorException the wx error exception
   */
  WxCpCreateDepartment createDepartment(@NonNull WxCpCreateDepartmentRequest request) throws WxErrorException;

  /**
   * 更新部门
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/department/update?access_token=ACCESS_TOKEN
   *
   * @param request the request
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp updateDepartment(@NonNull WxCpUpdateDepartmentRequest request) throws WxErrorException;

  /**
   * 删除部门
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/department/delete?access_token=ACCESS_TOKEN&id=ID
   *
   * @param id the id
   * @return wx cp base resp
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp deleteDepartment(Integer id) throws WxErrorException;

  /**
   * 设置关注「学校通知」的模式
   * 可通过此接口修改家长关注「学校通知」的模式：“可扫码填写资料加入”或“禁止扫码填写资料加入”
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/set_subscribe_mode?access_token=ACCESS_TOKEN
   *
   * @param subscribeMode 关注模式, 1:可扫码填写资料加入, 2:禁止扫码填写资料加入
   * @return subscribe mode
   * @throws WxErrorException the wx error exception
   */
  WxCpBaseResp setSubscribeMode(@NonNull Integer subscribeMode) throws WxErrorException;

  /**
   * 获取关注「学校通知」的模式
   * 可通过此接口获取家长关注「学校通知」的模式：“可扫码填写资料加入”或“禁止扫码填写资料加入”
   * <p>
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_subscribe_mode?access_token=ACCESS_TOKEN
   *
   * @return subscribe mode
   * @throws WxErrorException the wx error exception
   */
  Integer getSubscribeMode() throws WxErrorException;

  /**
   * 获取外部联系人详情
   * 学校可通过此接口，根据外部联系人的userid（如何获取?），拉取外部联系人详情。
   * <p>
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get?access_token=ACCESS_TOKEN&external_userid
   * =EXTERNAL_USERID
   *
   * @param externalUserId 外部联系人的userid，注意不是学校成员的帐号
   * @return external contact
   * @throws WxErrorException the wx error exception
   */
  WxCpExternalContact getExternalContact(@NonNull String externalUserId) throws WxErrorException;

  /**
   * 获取可使用的家长范围
   * 获取可在微信「学校通知-学校应用」使用该应用的家长范围，以学生或部门列表的形式返回。应用只能给该列表下的家长发送「学校通知」。注意该范围只能由学校的系统管理员在「管理端-家校沟通-配置」配置。
   * <p>
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/agent/get_allow_scope?access_token=ACCESS_TOKEN&agentid=AGENTID
   *
   * @param agentId the agent id
   * @return allow scope
   * @throws WxErrorException the wx error exception
   */
  WxCpAllowScope getAllowScope(@NonNull Integer agentId) throws WxErrorException;

  /**
   * 外部联系人openid转换
   * 企业和服务商可通过此接口，将微信外部联系人的userid（如何获取?）转为微信openid，用于调用支付相关接口。暂不支持企业微信外部联系人（ExternalUserid为wo开头）的userid转openid。
   * <p>
   * 请求方式：POST（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/convert_to_openid?access_token=ACCESS_TOKEN
   *
   * @param externalUserId the external user id
   * @return string
   * @throws WxErrorException the wx error exception
   */
  String convertToOpenId(@NonNull String externalUserId) throws WxErrorException;

  /**
   * 获取部门列表
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/school/department/list?access_token=ACCESS_TOKEN&id=ID
   *
   * @param id 部门id。获取指定部门及其下的子部门。 如果不填，默认获取全量组织架构
   * @return wx cp department list
   * @throws WxErrorException the wx error exception
   */
  WxCpDepartmentList listDepartment(Integer id) throws WxErrorException;

  /**
   * 获取「学校通知」二维码
   * 学校可通过此接口获取「学校通知」二维码，家长可通过扫描此二维码关注「学校通知」并接收学校推送的消息。
   * 请求方式：GET（HTTPS）
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_subscribe_qr_code?access_token=ACCESS_TOKEN
   *
   * @return subscribe qr code
   * @throws WxErrorException the wx error exception
   */
  WxCpSubscribeQrCode getSubscribeQrCode() throws WxErrorException;

  /**
   * 修改自动升年级的配置
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/school/set_upgrade_info?access_token=ACCESS_TOKEN
   *
   * @param upgradeTime   the upgrade time
   * @param upgradeSwitch the upgrade switch
   * @return upgrade info
   * @throws WxErrorException the wx error exception
   */
  WxCpSetUpgradeInfo setUpgradeInfo(Long upgradeTime, Integer upgradeSwitch) throws WxErrorException;

}
