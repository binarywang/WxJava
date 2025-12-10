package me.chanjar.weixin.cp.constant;


/**
 * <pre>
 *  企业微信api地址常量类
 *  Created by BinaryWang on 2019-06-02.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpApiPathConsts {
  /**
   * The constant DEFAULT_CP_BASE_URL.
   */
  String DEFAULT_CP_BASE_URL = "https://qyapi.weixin.qq.com";

  /**
   * The constant GET_JSAPI_TICKET.
   */
  String GET_JSAPI_TICKET = "/cgi-bin/get_jsapi_ticket";
  /**
   * The constant GET_AGENT_CONFIG_TICKET.
   */
  String GET_AGENT_CONFIG_TICKET = "/cgi-bin/ticket/get?&type=agent_config";
  /**
   * The constant GET_CALLBACK_IP.
   */
  String GET_CALLBACK_IP = "/cgi-bin/getcallbackip";
  /**
   * The constant GET_API_DOMAIN_IP.
   */
  String GET_API_DOMAIN_IP = "/cgi-bin/get_api_domain_ip";
  /**
   * The constant BATCH_REPLACE_PARTY.
   */
  String BATCH_REPLACE_PARTY = "/cgi-bin/batch/replaceparty";
  /**
   * The constant BATCH_SYNC_USER.
   */
  String BATCH_SYNC_USER = "/cgi-bin/batch/syncuser";
  /**
   * The constant BATCH_REPLACE_USER.
   */
  String BATCH_REPLACE_USER = "/cgi-bin/batch/replaceuser";
  /**
   * The constant BATCH_GET_RESULT.
   */
  String BATCH_GET_RESULT = "/cgi-bin/batch/getresult?jobid=";
  /**
   * The constant JSCODE_TO_SESSION.
   */
  String JSCODE_TO_SESSION = "/cgi-bin/miniprogram/jscode2session";
  /**
   * The constant GET_TOKEN.
   */
  String GET_TOKEN = "/cgi-bin/gettoken?corpid=%s&corpsecret=%s";
  /**
   * The constant WEBHOOK_SEND.
   */
  String WEBHOOK_SEND = "/cgi-bin/webhook/send?key=";

  /**
   * 消息推送相关接口
   * https://work.weixin.qq.com/api/doc/90000/90135/90235
   */
  interface Message {

    /**
     * 发送应用消息
     */
    String MESSAGE_SEND = "/cgi-bin/message/send";

    /**
     * 查询应用消息发送统计
     */
    String GET_STATISTICS = "/cgi-bin/message/get_statistics";

    /**
     * 发送「学校通知」
     * https://developer.work.weixin.qq.com/document/path/92321
     */
    String EXTERNAL_CONTACT_MESSAGE_SEND = "/cgi-bin/externalcontact/message/send";

    /**
     * 撤回应用消息
     * https://developer.work.weixin.qq.com/document/path/94867
     */
    String MESSAGE_RECALL = "/cgi-bin/message/recall";

    /**
     * 互联企业发送应用消息
     * https://developer.work.weixin.qq.com/document/path/90250
     */
    String LINKEDCORP_MESSAGE_SEND = "/cgi-bin/linkedcorp/message/send";

  }

  /**
   * The interface Agent.
   */
  interface Agent {
    /**
     * The constant AGENT_GET.
     */
    String AGENT_GET = "/cgi-bin/agent/get?agentid=%d";
    /**
     * The constant AGENT_SET.
     */
    String AGENT_SET = "/cgi-bin/agent/set";
    /**
     * The constant AGENT_LIST.
     */
    String AGENT_LIST = "/cgi-bin/agent/list";
  }

  /**
   * The interface Work bench.
   */
  interface WorkBench {
    /**
     * The constant WORKBENCH_TEMPLATE_SET.
     */
    String WORKBENCH_TEMPLATE_SET = "/cgi-bin/agent/set_workbench_template";
    /**
     * The constant WORKBENCH_TEMPLATE_GET.
     */
    String WORKBENCH_TEMPLATE_GET = "/cgi-bin/agent/get_workbench_template";
    /**
     * The constant WORKBENCH_DATA_SET.
     */
    String WORKBENCH_DATA_SET = "/cgi-bin/agent/set_workbench_data";
    /**
     * The constant WORKBENCH_BATCH_DATA_SET.
     */
    String WORKBENCH_BATCH_DATA_SET = "/cgi-bin/agent/batch_set_workbench_data";
  }

  /**
   * The interface O auth 2.
   */
  interface OAuth2 {
    /**
     * The constant GET_USER_INFO.
     */
    String GET_USER_INFO = "/cgi-bin/user/getuserinfo?code=%s&agentid=%d";
    /**
     * The constant GET_SCHOOL_USER_INFO.
     */
    String GET_SCHOOL_USER_INFO = "/cgi-bin/school/getuserinfo?code=%s";
    /**
     * The constant GET_USER_DETAIL.
     */
    String GET_USER_DETAIL = "/cgi-bin/auth/getuserdetail";
    /**
     * The constant URL_OAUTH2_AUTHORIZE.
     */
    String URL_OAUTH2_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";
    /**
     * The constant GET_USER_INFO without agentId.
     */
    String GET_USER_AUTH_INFO = "/cgi-bin/auth/getuserinfo?code=%s";
    /**
     * The constant GET_TFA_INFO.
     */
    String GET_TFA_INFO = "/cgi-bin/auth/get_tfa_info";
  }

  /**
   * The interface Chat.
   */
  interface Chat {
    /**
     * The constant APPCHAT_CREATE.
     */
    String APPCHAT_CREATE = "/cgi-bin/appchat/create";
    /**
     * The constant APPCHAT_UPDATE.
     */
    String APPCHAT_UPDATE = "/cgi-bin/appchat/update";
    /**
     * The constant APPCHAT_GET_CHATID.
     */
    String APPCHAT_GET_CHATID = "/cgi-bin/appchat/get?chatid=";
    /**
     * The constant APPCHAT_SEND.
     */
    String APPCHAT_SEND = "/cgi-bin/appchat/send";
  }

  /**
   * The interface Department.
   */
  interface Department {
    /**
     * The constant DEPARTMENT_CREATE.
     */
    String DEPARTMENT_CREATE = "/cgi-bin/department/create";
    /**
     * The constant DEPARTMENT_UPDATE.
     */
    String DEPARTMENT_UPDATE = "/cgi-bin/department/update";
    /**
     * The constant DEPARTMENT_GET.
     */
    String DEPARTMENT_GET = "/cgi-bin/department/get?id=%d";
    /**
     * The constant DEPARTMENT_DELETE.
     */
    String DEPARTMENT_DELETE = "/cgi-bin/department/delete?id=%d";
    /**
     * The constant DEPARTMENT_LIST.
     */
    String DEPARTMENT_LIST = "/cgi-bin/department/list";
    /**
     * The constant DEPARTMENT_SIMPLE_LIST.
     */
    String DEPARTMENT_SIMPLE_LIST = "/cgi-bin/department/simplelist";
  }

  /**
   * The interface Media.
   */
  interface Media {
    /**
     * The constant MEDIA_GET.
     */
    String MEDIA_GET = "/cgi-bin/media/get";
    /**
     * The constant MEDIA_UPLOAD.
     */
    String MEDIA_UPLOAD = "/cgi-bin/media/upload?type=";
    /**
     * The constant IMG_UPLOAD.
     */
    String IMG_UPLOAD = "/cgi-bin/media/uploadimg";
    /**
     * The constant JSSDK_MEDIA_GET.
     */
    String JSSDK_MEDIA_GET = "/cgi-bin/media/get/jssdk";

    /** The constant GET_UPLOAD_BY_URL_RESULT. */
    String GET_UPLOAD_BY_URL_RESULT = "/cgi-bin/media/get_upload_by_url_result";

    /** The constant UPLOAD_BY_URL. */
    String UPLOAD_BY_URL = "/cgi-bin/media/upload_by_url";
  }

  /**
   * The interface Menu.
   */
  interface Menu {
    /**
     * The constant MENU_CREATE.
     */
    String MENU_CREATE = "/cgi-bin/menu/create?agentid=%d";
    /**
     * The constant MENU_DELETE.
     */
    String MENU_DELETE = "/cgi-bin/menu/delete?agentid=%d";
    /**
     * The constant MENU_GET.
     */
    String MENU_GET = "/cgi-bin/menu/get?agentid=%d";
  }

  /**
   * The interface Oa.
   */
  interface Oa {
    /**
     * 打卡
     * https://developer.work.weixin.qq.com/document/path/94204
     */
    String GET_CORP_CHECKIN_OPTION = "/cgi-bin/checkin/getcorpcheckinoption";
    /**
     * The constant GET_CHECKIN_DATA.
     */
    String GET_CHECKIN_DATA = "/cgi-bin/checkin/getcheckindata";
    /**
     * The constant GET_CHECKIN_OPTION.
     */
    String GET_CHECKIN_OPTION = "/cgi-bin/checkin/getcheckinoption";
    /**
     * The constant GET_CHECKIN_DAY_DATA.
     */
    String GET_CHECKIN_DAY_DATA = "/cgi-bin/checkin/getcheckin_daydata";
    /**
     * The constant GET_CHECKIN_MONTH_DATA.
     */
    String GET_CHECKIN_MONTH_DATA = "/cgi-bin/checkin/getcheckin_monthdata";
    /**
     * The constant GET_CHECKIN_SCHEDULE_DATA.
     */
    String GET_CHECKIN_SCHEDULE_DATA = "/cgi-bin/checkin/getcheckinschedulist";
    /**
     * The constant SET_CHECKIN_SCHEDULE_DATA.
     */
    String SET_CHECKIN_SCHEDULE_DATA = "/cgi-bin/checkin/setcheckinschedulist";
    /**
     * The constant ADD_CHECK_IN_USER_FACE.
     */
    String ADD_CHECK_IN_USER_FACE = "/cgi-bin/checkin/addcheckinuserface";

    /**
     * 审批
     * https://developer.work.weixin.qq.com/document/path/91956
     */
    String COPY_TEMPLATE = "/cgi-bin/oa/approval/copytemplate";
    /**
     * The constant GET_TEMPLATE_DETAIL.
     */
    String GET_TEMPLATE_DETAIL = "/cgi-bin/oa/gettemplatedetail";
    /**
     * The constant CREATE_TEMPLATE.
     */
    String CREATE_TEMPLATE = "/cgi-bin/oa/approval/create_template";
    /**
     * The constant CREATE_TEMPLATE.
     */
    String UPDATE_TEMPLATE = "/cgi-bin/oa/approval/update_template";
    /**
     * The constant APPLY_EVENT.
     */
    String APPLY_EVENT = "/cgi-bin/oa/applyevent";
    /**
     * The constant GET_APPROVAL_INFO.
     */
    String GET_APPROVAL_INFO = "/cgi-bin/oa/getapprovalinfo";
    /**
     * The constant GET_APPROVAL_DETAIL.
     */
    String GET_APPROVAL_DETAIL = "/cgi-bin/oa/getapprovaldetail";
    /**
     * The constant GET_APPROVAL_DATA.
     */
    String GET_APPROVAL_DATA = "/cgi-bin/corp/getapprovaldata";

    /**
     * The constant GET_CORP_CONF.
     */
    String GET_CORP_CONF = "/cgi-bin/oa/vacation/getcorpconf";
    /**
     * The constant GET_USER_VACATION_QUOTA.
     */
    String GET_USER_VACATION_QUOTA = "/cgi-bin/oa/vacation/getuservacationquota";
    /**
     * The constant SET_ONE_USER_QUOTA.
     */
    String SET_ONE_USER_QUOTA = "/cgi-bin/oa/vacation/setoneuserquota";

    /**
     * 公费电话
     * https://developer.work.weixin.qq.com/document/path/93662
     */
    String GET_DIAL_RECORD = "/cgi-bin/dial/get_dial_record";

    /**
     * 日程
     * https://developer.work.weixin.qq.com/document/path/93624
     */
    String CALENDAR_ADD = "/cgi-bin/oa/calendar/add";
    /**
     * The constant CALENDAR_UPDATE.
     */
    String CALENDAR_UPDATE = "/cgi-bin/oa/calendar/update";
    /**
     * The constant CALENDAR_GET.
     */
    String CALENDAR_GET = "/cgi-bin/oa/calendar/get";
    /**
     * The constant CALENDAR_DEL.
     */
    String CALENDAR_DEL = "/cgi-bin/oa/calendar/del";

    /**
     * The constant SCHEDULE_ADD.
     */
    String SCHEDULE_ADD = "/cgi-bin/oa/schedule/add";
    /**
     * The constant SCHEDULE_UPDATE.
     */
    String SCHEDULE_UPDATE = "/cgi-bin/oa/schedule/update";
    /**
     * The constant SCHEDULE_GET.
     */
    String SCHEDULE_GET = "/cgi-bin/oa/schedule/get";
    /**
     * The constant SCHEDULE_DEL.
     */
    String SCHEDULE_DEL = "/cgi-bin/oa/schedule/del";
    /**
     * The constant SCHEDULE_LIST.
     */
    String SCHEDULE_LIST = "/cgi-bin/oa/schedule/get_by_calendar";

    /**
     * 会议
     * https://developer.work.weixin.qq.com/document/path/93626
     */
    String MEETING_ADD = "/cgi-bin/meeting/create";
    /**
     * The constant MEETING_UPDATE.
     */
    String MEETING_UPDATE = "/cgi-bin/meeting/update";
    /**
     * The constant MEETING_CANCEL.
     */
    String MEETING_CANCEL = "/cgi-bin/meeting/cancel";
    /**
     * The constant MEETING_DETAIL.
     */
    String MEETING_DETAIL = "/cgi-bin/meeting/get_info";
    /**
     * The constant GET_USER_MEETING_ID.
     */
    String GET_USER_MEETING_ID = "/cgi-bin/meeting/get_user_meetingid";

    /**
     * 会议室
     * https://developer.work.weixin.qq.com/document/path/93624
     */
    String MEETINGROOM_ADD = "/cgi-bin/oa/meetingroom/add";
    /**
     * The constant MEETINGROOM_LIST.
     */
    String MEETINGROOM_LIST = "/cgi-bin/oa/meetingroom/list";
    /**
     * The constant MEETINGROOM_EDIT.
     */
    String MEETINGROOM_EDIT = "/cgi-bin/oa/meetingroom/edit";
    /**
     * The constant MEETINGROOM_DEL.
     */
    String MEETINGROOM_DEL = "/cgi-bin/oa/meetingroom/del";
    /**
     * The constant MEETINGROOM_GET_BOOKING_INFO.
     */
    String MEETINGROOM_GET_BOOKING_INFO = "/cgi-bin/oa/meetingroom/get_booking_info";
    /**
     * The constant MEETINGROOM_BOOK.
     */
    String MEETINGROOM_BOOK = "/cgi-bin/oa/meetingroom/book";
    /**
     * The constant MEETINGROOM_BOOK_BY_SCHEDULE.
     */
    String MEETINGROOM_BOOK_BY_SCHEDULE = "/cgi-bin/oa/meetingroom/book_by_schedule";
    /**
     * The constant MEETINGROOM_BOOK_BY_MEETING.
     */
    String MEETINGROOM_BOOK_BY_MEETING = "/cgi-bin/oa/meetingroom//book_by_meeting";
    /**
     * The constant MEETINGROOM_CANCEL_BOOK.
     */
    String MEETINGROOM_CANCEL_BOOK = "/cgi-bin/oa/meetingroom/cancel_book";
    /**
     * The constant MEETINGROOM_BOOKINFO_GET.
     */
    String MEETINGROOM_BOOKINFO_GET = "/cgi-bin/oa/meetingroom/bookinfo/get";

    /**
     * 微盘
     * https://developer.work.weixin.qq.com/document/path/93654
     */
    String SPACE_CREATE = "/cgi-bin/wedrive/space_create";
    /**
     * The constant SPACE_RENAME.
     */
    String SPACE_RENAME = "/cgi-bin/wedrive/space_rename";
    /**
     * The constant SPACE_DISMISS.
     */
    String SPACE_DISMISS = "/cgi-bin/wedrive/space_dismiss";
    /**
     * The constant SPACE_INFO.
     */
    String SPACE_INFO = "/cgi-bin/wedrive/space_info";
    /**
     * The constant SPACE_ACL_ADD.
     */
    String SPACE_ACL_ADD = "/cgi-bin/wedrive/space_acl_add";
    /**
     * The constant SPACE_ACL_DEL.
     */
    String SPACE_ACL_DEL = "/cgi-bin/wedrive/space_acl_del";
    /**
     * The constant SPACE_SETTING.
     */
    String SPACE_SETTING = "/cgi-bin/wedrive/space_setting";
    /**
     * The constant SPACE_SHARE.
     */
    String SPACE_SHARE = "/cgi-bin/wedrive/space_share";
    /**
     * The constant FILE_LIST.
     */
    String FILE_LIST = "/cgi-bin/wedrive/file_list";
    /**
     * The constant FILE_UPLOAD.
     */
    String FILE_UPLOAD = "/cgi-bin/wedrive/file_upload";
    /**
     * The constant FILE_DOWNLOAD.
     */
    String FILE_DOWNLOAD = "/cgi-bin/wedrive/file_download";
    /**
     * The constant FILE_RENAME.
     */
    String FILE_RENAME = "/cgi-bin/wedrive/file_rename";
    /**
     * The constant FILE_CREATE.
     */
    String FILE_CREATE = "/cgi-bin/wedrive/file_create";
    /**
     * The constant FILE_MOVE.
     */
    String FILE_MOVE = "/cgi-bin/wedrive/file_move";
    /**
     * The constant FILE_DELETE.
     */
    String FILE_DELETE = "/cgi-bin/wedrive/file_delete";
    /**
     * The constant FILE_INFO.
     */
    String FILE_INFO = "/cgi-bin/wedrive/file_info";
    /**
     * The constant FILE_ACL_ADD.
     */
    String FILE_ACL_ADD = "/cgi-bin/wedrive/file_acl_add";
    /**
     * The constant FILE_ACL_DEL.
     */
    String FILE_ACL_DEL = "/cgi-bin/wedrive/file_acl_del";
    /**
     * The constant FILE_SETTING.
     */
    String FILE_SETTING = "/cgi-bin/wedrive/file_setting";
    /**
     * The constant FILE_SHARE.
     */
    String FILE_SHARE = "/cgi-bin/wedrive/file_share";

    /**
     * 审批流程引擎
     * https://developer.work.weixin.qq.com/document/path/90269
     */
    String GET_OPEN_APPROVAL_DATA = "/cgi-bin/corp/getopenapprovaldata";

    /**
     * 文档
     * https://developer.work.weixin.qq.com/document/path/97392
     */
    /**
     * The constant WEDOC_CREATE_DOC.
     */
    String WEDOC_CREATE_DOC = "/cgi-bin/wedoc/create_doc";
    /**
     * The constant WEDOC_RENAME_DOC.
     */
    String WEDOC_RENAME_DOC = "/cgi-bin/wedoc/rename_doc";
    /**
     * The constant WEDOC_DEL_DOC.
     */
    String WEDOC_DEL_DOC = "/cgi-bin/wedoc/del_doc";
    /**
     * The constant WEDOC_GET_DOC_BASE_INFO.
     */
    String WEDOC_GET_DOC_BASE_INFO = "/cgi-bin/wedoc/get_doc_base_info";
    /**
     * The constant WEDOC_DOC_SHARE.
     */
    String WEDOC_DOC_SHARE = "/cgi-bin/wedoc/doc_share";

    /**
     * 邮件
     * https://developer.work.weixin.qq.com/document/path/95486
     */
    /**
     * The constant EXMAIL_APP_COMPOSE_SEND.
     */
    String EXMAIL_APP_COMPOSE_SEND = "/cgi-bin/exmail/app/compose_send";

  }

  /**
   * The interface School.
   */
  interface School {
    /**
     * The constant GET_HEALTH_REPORT_STAT.
     */
    String GET_HEALTH_REPORT_STAT = "/cgi-bin/health/get_health_report_stat";
    /**
     * The constant GET_REPORT_JOBIDS.
     */
    String GET_REPORT_JOBIDS = "/cgi-bin/health/get_report_jobids";
    /**
     * The constant GET_REPORT_JOB_INFO.
     */
    String GET_REPORT_JOB_INFO = "/cgi-bin/health/get_report_job_info";
    /**
     * The constant GET_REPORT_ANSWER.
     */
    String GET_REPORT_ANSWER = "/cgi-bin/health/get_report_answer";

    /**
     * The constant GET_TEACHER_CUSTOMIZE_HEALTH_INFO.
     */
    String GET_TEACHER_CUSTOMIZE_HEALTH_INFO = "/cgi-bin/school/user/get_teacher_customize_health_info";
    /**
     * The constant GET_STUDENT_CUSTOMIZE_HEALTH_INFO.
     */
    String GET_STUDENT_CUSTOMIZE_HEALTH_INFO = "/cgi-bin/school/user/get_student_customize_health_info";
    /**
     * The constant GET_HEALTH_QRCODE.
     */
    String GET_HEALTH_QRCODE = "/cgi-bin/school/user/get_health_qrcode";

    /**
     * The constant BATCH_CREATE_STUDENT.
     */
    String BATCH_CREATE_STUDENT = "/cgi-bin/school/user/batch_create_student";
    /**
     * The constant BATCH_DELETE_STUDENT.
     */
    String BATCH_DELETE_STUDENT = "/cgi-bin/school/user/batch_delete_student";
    /**
     * The constant BATCH_UPDATE_STUDENT.
     */
    String BATCH_UPDATE_STUDENT = "/cgi-bin/school/user/batch_update_student";
    /**
     * The constant BATCH_CREATE_PARENT.
     */
    String BATCH_CREATE_PARENT = "/cgi-bin/school/user/batch_create_parent";
    /**
     * The constant BATCH_DELETE_PARENT.
     */
    String BATCH_DELETE_PARENT = "/cgi-bin/school/user/batch_delete_parent";
    /**
     * The constant BATCH_UPDATE_PARENT.
     */
    String BATCH_UPDATE_PARENT = "/cgi-bin/school/user/batch_update_parent";

    /**
     * The constant CREATE_STUDENT.
     */
    String CREATE_STUDENT = "/cgi-bin/school/user/create_student";
    /**
     * The constant DELETE_STUDENT.
     */
    String DELETE_STUDENT = "/cgi-bin/school/user/delete_student?userid=";
    /**
     * The constant UPDATE_STUDENT.
     */
    String UPDATE_STUDENT = "/cgi-bin/school/user/update_student";
    /**
     * The constant CREATE_PARENT.
     */
    String CREATE_PARENT = "/cgi-bin/school/user/create_parent";
    /**
     * The constant UPDATE_PARENT.
     */
    String UPDATE_PARENT = "/cgi-bin/school/user/update_parent";
    /**
     * The constant DELETE_PARENT.
     */
    String DELETE_PARENT = "/cgi-bin/school/user/delete_parent?userid=";
    /**
     * The constant GET_USER.
     */
    String GET_USER = "/cgi-bin/school/user/get?userid=";
    /**
     * The constant GET_USER_LIST.
     */
    String GET_USER_LIST = "/cgi-bin/school/user/list?department_id=%s&fetch_child=%d";
    /**
     * The constant GET_USER_LIST_PARENT.
     */
    String GET_USER_LIST_PARENT = "/cgi-bin/school/user/list_parent?department_id=";
    /**
     * The constant SET_ARCH_SYNC_MODE.
     */
    String SET_ARCH_SYNC_MODE = "/cgi-bin/school/set_arch_sync_mode";
    /**
     * The constant SET_UPGRADE_INFO.
     */
    String SET_UPGRADE_INFO = "/cgi-bin/school/set_upgrade_info";

    /**
     * The constant DEPARTMENT_CREATE.
     */
    String DEPARTMENT_CREATE = "/cgi-bin/school/department/create";
    /**
     * The constant DEPARTMENT_UPDATE.
     */
    String DEPARTMENT_UPDATE = "/cgi-bin/school/department/update";
    /**
     * The constant DEPARTMENT_DELETE.
     */
    String DEPARTMENT_DELETE = "/cgi-bin/school/department/delete?id=";
    /**
     * The constant DEPARTMENT_LIST.
     */
    String DEPARTMENT_LIST = "/cgi-bin/school/department/list";

    /**
     * The constant GET_PAYMENT_RESULT.
     */
    String GET_PAYMENT_RESULT = "/cgi-bin/school/get_payment_result";
    /**
     * The constant GET_TRADE.
     */
    String GET_TRADE = "/cgi-bin/school/get_trade";
    /**
     * The constant GET_ALLOW_SCOPE.
     */
    String GET_ALLOW_SCOPE = "/cgi-bin/school/agent/get_allow_scope?agentid=";

    /**
     * 上课直播
     */
    String GET_LIVING_INFO = "/cgi-bin/school/living/get_living_info?livingid=";
    /**
     * The constant GET_WATCH_STAT.
     */
    String GET_WATCH_STAT = "/cgi-bin/school/living/get_watch_stat";
    /**
     * The constant GET_UNWATCH_STAT.
     */
    String GET_UNWATCH_STAT = "/cgi-bin/school/living/get_unwatch_stat";
  }

  /**
   * The interface Living.
   */
  interface Living {
    /**
     * The constant GET_LIVING_CODE.
     */
    String GET_LIVING_CODE = "/cgi-bin/living/get_living_code";
    /**
     * The constant GET_LIVING_INFO.
     */
    String GET_LIVING_INFO = "/cgi-bin/living/get_living_info?livingid=";
    /**
     * The constant GET_WATCH_STAT.
     */
    String GET_WATCH_STAT = "/cgi-bin/living/get_watch_stat";
    /**
     * The constant GET_LIVING_SHARE_INFO.
     */
    String GET_LIVING_SHARE_INFO = "/cgi-bin/living/get_living_share_info";
    /**
     * The constant GET_USER_ALL_LIVINGID.
     */
    String GET_USER_ALL_LIVINGID = "/cgi-bin/living/get_user_all_livingid";

    /**
     * The constant CREATE.
     */
    String CREATE = "/cgi-bin/living/create";
    /**
     * The constant MODIFY.
     */
    String MODIFY = "/cgi-bin/living/modify";
    /**
     * The constant CANCEL.
     */
    String CANCEL = "/cgi-bin/living/cancel";
    /**
     * The constant DELETE_REPLAY_DATA.
     */
    String DELETE_REPLAY_DATA = "/cgi-bin/living/delete_replay_data";
  }

  /**
   * The interface Msg audit.
   */
  interface MsgAudit {
    /**
     * The constant GET_PERMIT_USER_LIST.
     */
    String GET_PERMIT_USER_LIST = "/cgi-bin/msgaudit/get_permit_user_list";
    /**
     * The constant GET_GROUP_CHAT.
     */
    String GET_GROUP_CHAT = "/cgi-bin/msgaudit/groupchat/get";
    /**
     * The constant CHECK_SINGLE_AGREE.
     */
    String CHECK_SINGLE_AGREE = "/cgi-bin/msgaudit/check_single_agree";
  }

  /**
   * The interface Tag.
   */
  interface Tag {
    /**
     * The constant TAG_CREATE.
     */
    String TAG_CREATE = "/cgi-bin/tag/create";
    /**
     * The constant TAG_UPDATE.
     */
    String TAG_UPDATE = "/cgi-bin/tag/update";
    /**
     * The constant TAG_DELETE.
     */
    String TAG_DELETE = "/cgi-bin/tag/delete?tagid=%s";
    /**
     * The constant TAG_LIST.
     */
    String TAG_LIST = "/cgi-bin/tag/list";
    /**
     * The constant TAG_GET.
     */
    String TAG_GET = "/cgi-bin/tag/get?tagid=%s";
    /**
     * The constant TAG_ADD_TAG_USERS.
     */
    String TAG_ADD_TAG_USERS = "/cgi-bin/tag/addtagusers";
    /**
     * The constant TAG_DEL_TAG_USERS.
     */
    String TAG_DEL_TAG_USERS = "/cgi-bin/tag/deltagusers";
  }

  /**
   * The interface Task card.
   */
  interface TaskCard {
    /**
     * The constant UPDATE_TASK_CARD.
     */
    String UPDATE_TASK_CARD = "/cgi-bin/message/update_taskcard";
    /**
     * The constant UPDATE_TEMPLATE_CARD.
     */
    String UPDATE_TEMPLATE_CARD = "/cgi-bin/message/update_template_card";
  }

  /**
   * The interface Tp.
   */
  interface Tp {
    /**
     * The constant JSCODE_TO_SESSION.
     */
    String JSCODE_TO_SESSION = "/cgi-bin/service/miniprogram/jscode2session";
    /**
     * The constant GET_CORP_TOKEN.
     */
    String GET_CORP_TOKEN = "/cgi-bin/service/get_corp_token";
    /**
     * The constant GET_PERMANENT_CODE.
     */
    String GET_PERMANENT_CODE = "/cgi-bin/service/get_permanent_code";
    /**
     * The constant GET_V2_PERMANENT_CODE.
     */
    String GET_V2_PERMANENT_CODE = "/cgi-bin/service/v2/get_permanent_code";
    /**
     * The constant GET_SUITE_TOKEN.
     */
    String GET_SUITE_TOKEN = "/cgi-bin/service/get_suite_token";
    /**
     * The constant GET_PROVIDER_TOKEN.
     */
    String GET_PROVIDER_TOKEN = "/cgi-bin/service/get_provider_token";
    /**
     * The constant GET_PREAUTH_CODE.
     */
    String GET_PREAUTH_CODE = "/cgi-bin/service/get_pre_auth_code";
    /**
     * The constant GET_AUTH_INFO.
     */
    String GET_AUTH_INFO = "/cgi-bin/service/get_auth_info";
    /**
     * The constant GET_AUTH_CORP_JSAPI_TICKET.
     */
    String GET_AUTH_CORP_JSAPI_TICKET = "/cgi-bin/get_jsapi_ticket";
    /**
     * The constant GET_SUITE_JSAPI_TICKET.
     */
    String GET_SUITE_JSAPI_TICKET = "/cgi-bin/ticket/get";
    /**
     * The constant GET_USERINFO3RD.
     */
    String GET_USERINFO3RD = "/cgi-bin/service/auth/getuserinfo3rd";
    /**
     * The constant GET_USERDETAIL3RD.
     */
    String GET_USERDETAIL3RD = "/cgi-bin/service/auth/getuserdetail3rd";
    /**
     * The constant GET_LOGIN_INFO.
     */
    String GET_LOGIN_INFO = "/cgi-bin/service/get_login_info";

    /**
     * The constant GET_CUSTOMIZED_AUTH_URL.
     */
    String GET_CUSTOMIZED_AUTH_URL = "/cgi-bin/service/get_customized_auth_url";


    /**
     * The constant CONTACT_SEARCH.
     */
    String CONTACT_SEARCH = "/cgi-bin/service/contact/search";
    /**
     * The constant GET_ADMIN_LIST.
     */
    String GET_ADMIN_LIST = "/cgi-bin/service/get_admin_list";
    /**
     * The constant GET_APP_QRCODE.
     */
    String GET_APP_QRCODE = "/cgi-bin/service/get_app_qrcode";

    /**
     * The constant CORPID_TO_OPENCORPID.
     */
    String CORPID_TO_OPENCORPID = "/cgi-bin/service/corpid_to_opencorpid";

    /**
     * The constant GET_ORDER.
     */
// 获取订单详情
    String GET_ORDER = "/cgi-bin/service/get_order";

    /**
     * The constant GET_ORDER_LIST.
     */
// 获取订单列表
    String GET_ORDER_LIST = "/cgi-bin/service/get_order_list";

    /**
     * The constant PROLONG_TRY.
     */
// 延长试用期
    String PROLONG_TRY = "/cgi-bin/service/prolong_try";

  }

  /**
   * The interface License.
   */
  interface License {
    /**
     * The constant CREATE_NEW_ORDER.
     */
    String CREATE_NEW_ORDER = "/cgi-bin/license/create_new_order";
    /**
     * The constant CREATE_RENEW_ORDER_JOB.
     */
    String CREATE_RENEW_ORDER_JOB = "/cgi-bin/license/create_renew_order_job";
    /**
     * The constant SUBMIT_ORDER_JOB.
     */
    String SUBMIT_ORDER_JOB = "/cgi-bin/license/submit_order_job";
    /**
     * The constant LIST_ORDER.
     */
    String LIST_ORDER = "/cgi-bin/license/list_order";
    /**
     * The constant GET_ORDER.
     */
    String GET_ORDER = "/cgi-bin/license/get_order";
    /**
     * The constant LIST_ORDER_ACCOUNT.
     */
    String LIST_ORDER_ACCOUNT = "/cgi-bin/license/list_order_account";
    /**
     * The constant ACTIVE_ACCOUNT.
     */
    String ACTIVE_ACCOUNT = "/cgi-bin/license/active_account";
    /**
     * The constant BATCH_ACTIVE_ACCOUNT.
     */
    String BATCH_ACTIVE_ACCOUNT = "/cgi-bin/license/batch_active_account";
    /**
     * The constant GET_ACTIVE_INFO_BY_CODE.
     */
    String GET_ACTIVE_INFO_BY_CODE = "/cgi-bin/license/get_active_info_by_code";
    /**
     * The constant BATCH_GET_ACTIVE_INFO_BY_CODE.
     */
    String BATCH_GET_ACTIVE_INFO_BY_CODE = "/cgi-bin/license/batch_get_active_info_by_code";
    /**
     * The constant LIST_ACTIVED_ACCOUNT.
     */
    String LIST_ACTIVED_ACCOUNT = "/cgi-bin/license/list_actived_account";
    /**
     * The constant GET_ACTIVE_INFO_BY_USER.
     */
    String GET_ACTIVE_INFO_BY_USER = "/cgi-bin/license/get_active_info_by_user";
    /**
     * The constant BATCH_TRANSFER_LICENSE.
     */
    String BATCH_TRANSFER_LICENSE = "/cgi-bin/license/batch_transfer_license";
  }

  /**
   * The interface User.
   */
  interface User {
    /**
     * The constant USER_AUTHENTICATE.
     */
    String USER_AUTHENTICATE = "/cgi-bin/user/authsucc?userid=";
    /**
     * The constant USER_CREATE.
     */
    String USER_CREATE = "/cgi-bin/user/create";
    /**
     * The constant USER_UPDATE.
     */
    String USER_UPDATE = "/cgi-bin/user/update";
    /**
     * The constant USER_DELETE.
     */
    String USER_DELETE = "/cgi-bin/user/delete?userid=";
    /**
     * The constant USER_BATCH_DELETE.
     */
    String USER_BATCH_DELETE = "/cgi-bin/user/batchdelete";
    /**
     * The constant USER_GET.
     */
    String USER_GET = "/cgi-bin/user/get?userid=";
    /**
     * The constant USER_LIST.
     */
    String USER_LIST = "/cgi-bin/user/list?department_id=";
    /**
     * The constant USER_SIMPLE_LIST.
     */
    String USER_SIMPLE_LIST = "/cgi-bin/user/simplelist?department_id=";
    /**
     * The constant BATCH_INVITE.
     */
    String BATCH_INVITE = "/cgi-bin/batch/invite";
    /**
     * The constant USER_CONVERT_TO_OPENID.
     */
    String USER_CONVERT_TO_OPENID = "/cgi-bin/user/convert_to_openid";
    /**
     * The constant USER_CONVERT_TO_USERID.
     */
    String USER_CONVERT_TO_USERID = "/cgi-bin/user/convert_to_userid";
    /**
     * The constant GET_USER_ID.
     */
    String GET_USER_ID = "/cgi-bin/user/getuserid";
    /**
     * The constant GET_USER_ID_BY_EMAIL.
     */
    String GET_USER_ID_BY_EMAIL = "/cgi-bin/user/get_userid_by_email";
    /**
     * The constant GET_EXTERNAL_CONTACT.
     */
    String GET_EXTERNAL_CONTACT = "/cgi-bin/crm/get_external_contact?external_userid=";
    /**
     * The constant GET_JOIN_QR_CODE.
     */
    String GET_JOIN_QR_CODE = "/cgi-bin/corp/get_join_qrcode?size_type=";
    /**
     * The constant GET_ACTIVE_STAT.
     */
    String GET_ACTIVE_STAT = "/cgi-bin/user/get_active_stat";
    /**
     * The constant USERID_TO_OPEN_USERID.
     */
    String USERID_TO_OPEN_USERID = "/cgi-bin/batch/userid_to_openuserid";
    /**
     * The constant OPEN_USERID_TO_USERID.
     */
    String OPEN_USERID_TO_USERID = "/cgi-bin/batch/openuserid_to_userid";

    /**
     * The constant USER_LIST_ID.
     */
    String USER_LIST_ID = "/cgi-bin/user/list_id";
  }

  /**
   * The interface External contact.
   */
  interface ExternalContact {
    /**
     * The constant GET_EXTERNAL_CONTACT.
     */
    @Deprecated
    String GET_EXTERNAL_CONTACT = "/cgi-bin/crm/get_external_contact?external_userid=";

    /**
     * The constant ADD_CONTACT_WAY.
     */
    String ADD_CONTACT_WAY = "/cgi-bin/externalcontact/add_contact_way";
    /**
     * The constant GET_CONTACT_WAY.
     */
    String GET_CONTACT_WAY = "/cgi-bin/externalcontact/get_contact_way";
    /**
     * The constant LIST_CONTACT_WAY.
     */
    String LIST_CONTACT_WAY = "/cgi-bin/externalcontact/list_contact_way";
    /**
     * The constant UPDATE_CONTACT_WAY.
     */
    String UPDATE_CONTACT_WAY = "/cgi-bin/externalcontact/update_contact_way";
    /**
     * The constant DEL_CONTACT_WAY.
     */
    String DEL_CONTACT_WAY = "/cgi-bin/externalcontact/del_contact_way";
    /**
     * The constant CLOSE_TEMP_CHAT.
     */
    String CLOSE_TEMP_CHAT = "/cgi-bin/externalcontact/close_temp_chat";
    /**
     * The constant GET_FOLLOW_USER_LIST.
     */
    String GET_FOLLOW_USER_LIST = "/cgi-bin/externalcontact/get_follow_user_list";
    /**
     * The constant GET_CONTACT_DETAIL.
     */
    String GET_CONTACT_DETAIL = "/cgi-bin/externalcontact/get?external_userid=";
    /**
     * The constant CONVERT_TO_OPENID.
     */
    String CONVERT_TO_OPENID = "/cgi-bin/externalcontact/convert_to_openid";
    /**
     * The constant UNIONID_TO_EXTERNAL_USERID.
     */
    String UNIONID_TO_EXTERNAL_USERID = "/cgi-bin/externalcontact/unionid_to_external_userid";
    /**
     * The constant UNIONID_TO_EXTERNAL_USERID_3RD.
     */
    String UNIONID_TO_EXTERNAL_USERID_3RD = "/cgi-bin/service/externalcontact/unionid_to_external_userid_3rd";
    /**
     * The constant GET_NEW_EXTERNAL_USERID.
     */
    String GET_NEW_EXTERNAL_USERID = "/cgi-bin/externalcontact/get_new_external_userid";
    /**
     * The constant TO_SERVICE_EXTERNAL_USERID.
     */
    String TO_SERVICE_EXTERNAL_USERID = "/cgi-bin/externalcontact/to_service_external_userid";
    /**
     * The constant FROM_SERVICE_EXTERNAL_USERID.
     */
    String FROM_SERVICE_EXTERNAL_USERID = "/cgi-bin/externalcontact/from_service_external_userid";
    /**
     * The constant FINISH_EXTERNAL_USERID_MIGRATION.
     */
    String FINISH_EXTERNAL_USERID_MIGRATION = "/cgi-bin/externalcontact/finish_external_userid_migration";
    /**
     * The constant GET_CONTACT_DETAIL_BATCH.
     */
    String GET_CONTACT_DETAIL_BATCH = "/cgi-bin/externalcontact/batch/get_by_user?";

    String GET_CONTACT_LIST = "/cgi-bin/externalcontact/contact_list?";

    /**
     * The constant UPDATE_REMARK.
     */
    String UPDATE_REMARK = "/cgi-bin/externalcontact/remark";
    /**
     * The constant LIST_EXTERNAL_CONTACT.
     */
    String LIST_EXTERNAL_CONTACT = "/cgi-bin/externalcontact/list?userid=";
    /**
     * The constant LIST_UNASSIGNED_CONTACT.
     */
    String LIST_UNASSIGNED_CONTACT = "/cgi-bin/externalcontact/get_unassigned_list";

    /**
     * The constant TRANSFER_UNASSIGNED_CONTACT.
     */
    @Deprecated
    String TRANSFER_UNASSIGNED_CONTACT = "/cgi-bin/externalcontact/transfer";
    /**
     * The constant TRANSFER_CUSTOMER.
     */
    String TRANSFER_CUSTOMER = "/cgi-bin/externalcontact/transfer_customer";
    /**
     * The constant TRANSFER_RESULT.
     */
    String TRANSFER_RESULT = "/cgi-bin/externalcontact/transfer_result";
    /**
     * The constant RESIGNED_TRANSFER_CUSTOMER.
     */
    String RESIGNED_TRANSFER_CUSTOMER = "/cgi-bin/externalcontact/resigned/transfer_customer";
    /**
     * The constant RESIGNED_TRANSFER_RESULT.
     */
    String RESIGNED_TRANSFER_RESULT = "/cgi-bin/externalcontact/resigned/transfer_result";
    /**
     * The constant GROUP_CHAT_LIST.
     */
    String GROUP_CHAT_LIST = "/cgi-bin/externalcontact/groupchat/list";
    /**
     * The constant GROUP_CHAT_INFO.
     */
    String GROUP_CHAT_INFO = "/cgi-bin/externalcontact/groupchat/get";
    /**
     * The constant OPENID_TO_CHATID.
     */
    String OPENID_TO_CHATID = "/cgi-bin/externalcontact/opengid_to_chatid";
    /**
     * The constant GROUP_CHAT_TRANSFER.
     */
    String GROUP_CHAT_TRANSFER = "/cgi-bin/externalcontact/groupchat/transfer";
    /**
     * The constant GROUP_CHAT_ONJOB_TRANSFER.
     */
    String GROUP_CHAT_ONJOB_TRANSFER = "/cgi-bin/externalcontact/groupchat/onjob_transfer";
    /**
     * The constant LIST_USER_BEHAVIOR_DATA.
     */
    String LIST_USER_BEHAVIOR_DATA = "/cgi-bin/externalcontact/get_user_behavior_data";
    /**
     * The constant LIST_GROUP_CHAT_DATA.
     */
    String LIST_GROUP_CHAT_DATA = "/cgi-bin/externalcontact/groupchat/statistic";
    /**
     * The constant ADD_JOIN_WAY.
     */
    String ADD_JOIN_WAY = "/cgi-bin/externalcontact/groupchat/add_join_way";
    /**
     * The constant GET_JOIN_WAY.
     */
    String GET_JOIN_WAY = "/cgi-bin/externalcontact/groupchat/get_join_way";
    /**
     * The constant UPDATE_JOIN_WAY.
     */
    String UPDATE_JOIN_WAY = "/cgi-bin/externalcontact/groupchat/update_join_way";
    /**
     * The constant DEL_JOIN_WAY.
     */
    String DEL_JOIN_WAY = "/cgi-bin/externalcontact/groupchat/del_join_way";
    /**
     * The constant ADD_MSG_TEMPLATE.
     */
    String ADD_MSG_TEMPLATE = "/cgi-bin/externalcontact/add_msg_template";
    /**
     * 提醒成员群发
     */
    String REMIND_GROUP_MSG_SEND = "/cgi-bin/externalcontact/remind_groupmsg_send";
    /**
     * 停止企业群发
     */
    String CANCEL_GROUP_MSG_SEND = "/cgi-bin/externalcontact/cancel_groupmsg_send";
    /**
     * The constant SEND_WELCOME_MSG.
     */
    String SEND_WELCOME_MSG = "/cgi-bin/externalcontact/send_welcome_msg";

    /**
     * The constant GET_CORP_TAG_LIST.
     */
    String GET_CORP_TAG_LIST = "/cgi-bin/externalcontact/get_corp_tag_list";
    /**
     * The constant ADD_CORP_TAG.
     */
    String ADD_CORP_TAG = "/cgi-bin/externalcontact/add_corp_tag";
    /**
     * The constant EDIT_CORP_TAG.
     */
    String EDIT_CORP_TAG = "/cgi-bin/externalcontact/edit_corp_tag";
    /**
     * The constant DEL_CORP_TAG.
     */
    String DEL_CORP_TAG = "/cgi-bin/externalcontact/del_corp_tag";
    /**
     * The constant MARK_TAG.
     */
    String MARK_TAG = "/cgi-bin/externalcontact/mark_tag";

    /**
     * The constant ADD_MOMENT_TASK.
     */
    String ADD_MOMENT_TASK = "/cgi-bin/externalcontact/add_moment_task";
    /**
     * The constant GET_MOMENT_TASK_RESULT.
     */
    String GET_MOMENT_TASK_RESULT = "/cgi-bin/externalcontact/get_moment_task_result";

    /**
     * 停止发表企业朋友圈
     */
    String CANCEL_MOMENT_TASK = "/cgi-bin/externalcontact/cancel_moment_task";

    /**
     * The constant GET_MOMENT_LIST.
     */
    String GET_MOMENT_LIST = "/cgi-bin/externalcontact/get_moment_list";
    /**
     * The constant GET_MOMENT_TASK.
     */
    String GET_MOMENT_TASK = "/cgi-bin/externalcontact/get_moment_task";
    /**
     * The constant GET_MOMENT_CUSTOMER_LIST.
     */
    String GET_MOMENT_CUSTOMER_LIST = "/cgi-bin/externalcontact/get_moment_customer_list";
    /**
     * The constant GET_MOMENT_SEND_RESULT.
     */
    String GET_MOMENT_SEND_RESULT = "/cgi-bin/externalcontact/get_moment_send_result";
    /**
     * The constant GET_MOMENT_COMMENTS.
     */
    String GET_MOMENT_COMMENTS = "/cgi-bin/externalcontact/get_moment_comments";

    /**
     * The constant GET_GROUP_MSG_SEND_RESULT.
     */
    String GET_GROUP_MSG_SEND_RESULT = "/cgi-bin/externalcontact/get_groupmsg_send_result";
    /**
     * The constant GET_GROUP_MSG_TASK.
     */
    String GET_GROUP_MSG_TASK = "/cgi-bin/externalcontact/get_groupmsg_task";
    /**
     * The constant GET_GROUP_MSG_LIST_V2.
     */
    String GET_GROUP_MSG_LIST_V2 = "/cgi-bin/externalcontact/get_groupmsg_list_v2";
    /**
     * The constant GET_GROUP_MSG_RESULT.
     */
    String GET_GROUP_MSG_RESULT = "/cgi-bin/externalcontact/get_group_msg_result";


    /**
     * The constant GET_PRODUCT_ALBUM.
     */
    String GET_PRODUCT_ALBUM = "/cgi-bin/externalcontact/get_product_album";
    /**
     * The constant GET_PRODUCT_ALBUM_LIST.
     */
    String GET_PRODUCT_ALBUM_LIST = "/cgi-bin/externalcontact/get_product_album_list";
    /**
     * The constant ADD_PRODUCT_ALBUM.
     */
    String ADD_PRODUCT_ALBUM = "/cgi-bin/externalcontact/add_product_album";
    /**
     * The constant UPDATE_PRODUCT_ALBUM.
     */
    String UPDATE_PRODUCT_ALBUM = "/cgi-bin/externalcontact/update_product_album";
    /**
     * The constant DELETE_PRODUCT_ALBUM.
     */
    String DELETE_PRODUCT_ALBUM = "/cgi-bin/externalcontact/delete_product_album";

    /**
     * The constant GROUP_WELCOME_TEMPLATE_ADD.
     */
    String GROUP_WELCOME_TEMPLATE_ADD = "/cgi-bin/externalcontact/group_welcome_template/add";
    /**
     * The constant GROUP_WELCOME_TEMPLATE_EDIT.
     */
    String GROUP_WELCOME_TEMPLATE_EDIT = "/cgi-bin/externalcontact/group_welcome_template/edit";
    /**
     * The constant GROUP_WELCOME_TEMPLATE_GET.
     */
    String GROUP_WELCOME_TEMPLATE_GET = "/cgi-bin/externalcontact/group_welcome_template/get";
    /**
     * The constant GROUP_WELCOME_TEMPLATE_DEL.
     */
    String GROUP_WELCOME_TEMPLATE_DEL = "/cgi-bin/externalcontact/group_welcome_template/del";

    /**
     * The constant UPLOAD_ATTACHMENT.
     */
    String UPLOAD_ATTACHMENT = "/cgi-bin/media/upload_attachment";

    /**
     * The constant GET_SUBSCRIBE_QR_CODE.
     */
    String GET_SUBSCRIBE_QR_CODE = "/cgi-bin/externalcontact/get_subscribe_qr_code";
    /**
     * The constant SET_SUBSCRIBE_MODE.
     */
    String SET_SUBSCRIBE_MODE = "/cgi-bin/externalcontact/set_subscribe_mode";
    /**
     * The constant GET_SUBSCRIBE_MODE.
     */
    String GET_SUBSCRIBE_MODE = "/cgi-bin/externalcontact/get_subscribe_mode";
    /**
     * The constant EXTERNAL_CONTACT_GET.
     */
    String EXTERNAL_CONTACT_GET = "/cgi-bin/externalcontact/get?external_userid=";

    /**
     * The constant ADD_INTERCEPT_RULE.
     */
    String ADD_INTERCEPT_RULE = "/cgi-bin/externalcontact/add_intercept_rule";
    /**
     * The constant UPDATE_INTERCEPT_RULE.
     */
    String UPDATE_INTERCEPT_RULE = "/cgi-bin/externalcontact/update_intercept_rule";
    /**
     * The constant DEL_INTERCEPT_RULE.
     */
    String DEL_INTERCEPT_RULE = "/cgi-bin/externalcontact/del_intercept_rule";
    /**
     * 获取敏感词规则列表
     */
    String GET_INTERCEPT_RULE_LIST = "/cgi-bin/externalcontact/get_intercept_rule_list";
    /**
     * 获取敏感词规则详情
     */
    String GET_INTERCEPT_RULE = "/cgi-bin/externalcontact/get_intercept_rule";
    /**
     * 获取当前仍然有效的获客链接
     */
    String CUSTOMER_ACQUISITION_LINK_LIST = "/cgi-bin/externalcontact/customer_acquisition/list_link";
    /**
     * 获取获客链接详情
     */
    String CUSTOMER_ACQUISITION_LINK_GET = "/cgi-bin/externalcontact/customer_acquisition/get";
    /**
     * 创建获客链接
     */
    String CUSTOMER_ACQUISITION_LINK_CREATE = "/cgi-bin/externalcontact/customer_acquisition/create_link";
    /**
     * 编辑获客链接
     */
    String CUSTOMER_ACQUISITION_LINK_UPDATE = "/cgi-bin/externalcontact/customer_acquisition/update_link";
    /**
     * 删除获客链接
     */
    String CUSTOMER_ACQUISITION_LINK_DELETE = "/cgi-bin/externalcontact/customer_acquisition/delete_link";
    /**
     * 获取获客客户列表
     */
    String CUSTOMER_ACQUISITION_CUSTOMER = "/cgi-bin/externalcontact/customer_acquisition/customer";
    /**
     * 查询剩余使用量
     */
    String CUSTOMER_ACQUISITION_QUOTA = "/cgi-bin/externalcontact/customer_acquisition_quota";

    /**
     * 查询链接使用详情
     */
    String CUSTOMER_ACQUISITION_STATISTIC = "/cgi-bin/externalcontact/customer_acquisition/statistic";
  }

  /**
   * The interface Kf.
   */
  interface Kf {
    /**
     * The constant ACCOUNT_ADD.
     */
    String ACCOUNT_ADD = "/cgi-bin/kf/account/add";
    /**
     * The constant ACCOUNT_UPD.
     */
    String ACCOUNT_UPD = "/cgi-bin/kf/account/update";
    /**
     * The constant ACCOUNT_DEL.
     */
    String ACCOUNT_DEL = "/cgi-bin/kf/account/del";
    /**
     * The constant ACCOUNT_LIST.
     */
    String ACCOUNT_LIST = "/cgi-bin/kf/account/list";
    /**
     * The constant ADD_CONTACT_WAY.
     */
    String ADD_CONTACT_WAY = "/cgi-bin/kf/add_contact_way";

    /**
     * The constant SERVICER_ADD.
     */
    String SERVICER_ADD = "/cgi-bin/kf/servicer/add";
    /**
     * The constant SERVICER_DEL.
     */
    String SERVICER_DEL = "/cgi-bin/kf/servicer/del";
    /**
     * The constant SERVICER_LIST.
     */
    String SERVICER_LIST = "/cgi-bin/kf/servicer/list?open_kfid=";

    /**
     * The constant SERVICE_STATE_GET.
     */
    String SERVICE_STATE_GET = "/cgi-bin/kf/service_state/get";
    /**
     * The constant SERVICE_STATE_TRANS.
     */
    String SERVICE_STATE_TRANS = "/cgi-bin/kf/service_state/trans";

    /**
     * The constant SYNC_MSG.
     */
    String SYNC_MSG = "/cgi-bin/kf/sync_msg";
    /**
     * The constant SEND_MSG.
     */
    String SEND_MSG = "/cgi-bin/kf/send_msg";

    /**
     * The constant SEND_MSG_ON_EVENT.
     */
    String SEND_MSG_ON_EVENT = "/cgi-bin/kf/send_msg_on_event";
    /**
     * The constant CUSTOMER_BATCH_GET.
     */
    String CUSTOMER_BATCH_GET = "/cgi-bin/kf/customer/batchget";
    /**
     * The constant GET_CORP_STATISTIC.
     */
    String GET_CORP_STATISTIC = "/cgi-bin/kf/get_corp_statistic";

    /**
     * The constant GET_SERVICER_STATISTIC.
     */
    String GET_SERVICER_STATISTIC = "/cgi-bin/kf/get_servicer_statistic";

    /**
     * The constant CUSTOMER_GET_UPGRADE_SERVICE_CONFIG.
     */
    String CUSTOMER_GET_UPGRADE_SERVICE_CONFIG = "/cgi-bin/kf/customer/get_upgrade_service_config";
    /**
     * The constant CUSTOMER_UPGRADE_SERVICE.
     */
    String CUSTOMER_UPGRADE_SERVICE = "/cgi-bin/kf/customer/upgrade_service";
    /**
     * The constant CUSTOMER_CANCEL_UPGRADE_SERVICE.
     */
    String CUSTOMER_CANCEL_UPGRADE_SERVICE = "/cgi-bin/kf/customer/cancel_upgrade_service";

  }

  /**
   * The interface Export.
   */
  interface Export {
    /**
     * The constant SIMPLE_USER.
     */
    String SIMPLE_USER = "/cgi-bin/export/simple_user";
    /**
     * The constant USER.
     */
    String USER = "/cgi-bin/export/user";
    /**
     * The constant DEPARTMENT.
     */
    String DEPARTMENT = "/cgi-bin/export/department";
    /**
     * The constant TAG_USER.
     */
    String TAG_USER = "/cgi-bin/export/taguser";
    /**
     * The constant GET_RESULT.
     */
    String GET_RESULT = "/cgi-bin/export/get_result?jobid=%s";
  }

  interface CorpGroup {
    /**
     * 获取应用共享信息
     * https://developer.work.weixin.qq.com/document/path/93403
     */
    String LIST_SHARE_APP_INFO = "/cgi-bin/corpgroup/corp/list_app_share_info";
    /**
     * 获取下级/下游企业的access_token
     * https://developer.work.weixin.qq.com/document/path/93359
     */
    String CORP_GET_TOKEN = "/cgi-bin/corpgroup/corp/gettoken";
    /**
     * 获取下级/下游企业小程序session
     * https://developer.work.weixin.qq.com/document/path/93355
     */
    String MA_TRANSFER_SESSION = "/cgi-bin/miniprogram/transfer_session";
  }

  interface LinkedCorp {
    /**
     * 获取应用的可见范围
     * https://developer.work.weixin.qq.com/document/path/93172
     */
    String GET_PERM_LIST = "/cgi-bin/linkedcorp/agent/get_perm_list";
    /**
     * 获取互联企业成员详细信息
     * https://developer.work.weixin.qq.com/document/path/93171
     */
    String GET_USER = "/cgi-bin/linkedcorp/user/get";
    /**
     * 获取互联企业部门成员
     * https://developer.work.weixin.qq.com/document/path/93168
     */
    String GET_USER_SIMPLELIST = "/cgi-bin/linkedcorp/user/simplelist";
    /**
     * 获取互联企业部门成员详情
     * https://developer.work.weixin.qq.com/document/path/93169
     */
    String GET_USER_LIST = "/cgi-bin/linkedcorp/user/list";
    /**
     * 获取互联企业部门列表
     * https://developer.work.weixin.qq.com/document/path/93170
     */
    String GET_DEPARTMENT_LIST = "/cgi-bin/linkedcorp/department/list";
    /**
     * 发送应用消息
     * https://developer.work.weixin.qq.com/document/path/90250
     */
    String SENG_MESSAGE = "/cgi-bin/linkedcorp/message/send";
  }

  interface IdConvert {

    /**
     * 将企业主体下的客户标签ID转换成服务商主体下的客户标签ID。
     */
    String EXTERNAL_TAG_ID = "/cgi-bin/idconvert/external_tagid";

    /**
     * 将微信客户的unionid转为第三方主体的external_userid
     * 该接口有调用频率限制，当subject_type为0时，按企业作如下的限制：10万次/小时、48万次/天、750万次/月
     */
    String UNION_ID_TO_EXTERNAL_USER_ID = "/cgi-bin/idconvert/unionid_to_external_userid";

    /**
     * 将企业主体下的微信客服ID转换成服务商主体下的微信客服ID
     */
    String OPEN_KF_ID = "/cgi-bin/idconvert/open_kfid";

    /**
     * 将应用获取的外部用户临时idtmp_external_userid，转换为external_userid。
     */
    String CONVERT_TMP_EXTERNAL_USER_ID = "/cgi-bin/idconvert/convert_tmp_external_userid";
  }

  /**
   * 智能机器人相关接口
   * 官方文档: https://developer.work.weixin.qq.com/document/path/101039
   */
  interface IntelligentRobot {
    /**
     * 创建智能机器人
     */
    String CREATE_ROBOT = "/cgi-bin/intelligent_robot/create";
    
    /**
     * 删除智能机器人
     */
    String DELETE_ROBOT = "/cgi-bin/intelligent_robot/delete";
    
    /**
     * 更新智能机器人
     */
    String UPDATE_ROBOT = "/cgi-bin/intelligent_robot/update";
    
    /**
     * 查询智能机器人
     */
    String GET_ROBOT = "/cgi-bin/intelligent_robot/get";
    
    /**
     * 智能机器人会话
     */
    String CHAT = "/cgi-bin/intelligent_robot/chat";
    
    /**
     * 重置智能机器人会话
     */
    String RESET_SESSION = "/cgi-bin/intelligent_robot/reset_session";
  }
}
