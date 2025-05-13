package cn.binarywang.wx.miniapp.api;

import cn.binarywang.wx.miniapp.bean.cloud.*;
import cn.binarywang.wx.miniapp.bean.cloud.request.WxCloudSendSmsV2Request;
import com.google.gson.JsonArray;
import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;
import java.util.Map;

/**
 * 云开发相关接口.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020 -01-22
 */
public interface WxMaCloudService {
  /**
   * Invoke cloud function string.
   *
   * @param name the name
   * @param body the body
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String invokeCloudFunction(String name, String body) throws WxErrorException;

  /**
   * <pre>
   * 触发云函数。注意：HTTP API 途径触发云函数不包含用户信息。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/functions/invokeCloudFunction.html
   *
   * 请求地址
   * POST https://api.weixin.qq.com/tcb/invokecloudfunction?access_token=ACCESS_TOKEN&env=ENV&name=FUNCTION_NAME
   *
   * </pre>
   *
   * @param env  string		是	云开发环境ID
   * @param name string		是	云函数名称
   * @param body string		是	云函数的传入参数，具体结构由开发者定义。
   * @return resp_data string	云函数返回的buffer
   * @throws WxErrorException .
   */
  String invokeCloudFunction(String env, String name, String body) throws WxErrorException;

  /**
   * Add list.
   *
   * @param collection the collection
   * @param list       the list
   * @return the list
   * @throws WxErrorException the wx error exception
   */
  List<String> add(String collection, List<?> list) throws WxErrorException;

  /**
   * Add string.
   *
   * @param collection the collection
   * @param obj        the obj
   * @return the string
   * @throws WxErrorException the wx error exception
   */
  String add(String collection, Object obj) throws WxErrorException;

  /**
   * Database add json array.
   *
   * @param query the query
   * @return the json array
   * @throws WxErrorException the wx error exception
   */
  JsonArray databaseAdd(String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库插入记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseAdd.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databaseadd?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return 插入成功的数据集合主键_id json array
   * @throws WxErrorException .
   */
  JsonArray databaseAdd(String env, String query) throws WxErrorException;

  /**
   * Delete integer.
   *
   * @param collection the collection
   * @param whereJson  the where json
   * @return the integer
   * @throws WxErrorException the wx error exception
   */
  Integer delete(String collection, String whereJson) throws WxErrorException;

  /**
   * Database delete int.
   *
   * @param query the query
   * @return the int
   * @throws WxErrorException the wx error exception
   */
  int databaseDelete(String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库删除记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseDelete.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasedelete?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return 删除记录数量 int
   * @throws WxErrorException .
   */
  int databaseDelete(String env, String query) throws WxErrorException;

  /**
   * Update wx cloud database update result.
   *
   * @param collection the collection
   * @param whereJson  the where json
   * @param updateJson the update json
   * @return the wx cloud database update result
   * @throws WxErrorException the wx error exception
   */
  WxCloudDatabaseUpdateResult update(String collection, String whereJson, String updateJson) throws WxErrorException;

  /**
   * Database update wx cloud database update result.
   *
   * @param query the query
   * @return the wx cloud database update result
   * @throws WxErrorException the wx error exception
   */
  WxCloudDatabaseUpdateResult databaseUpdate(String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库更新记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseUpdate.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databaseupdate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return . wx cloud database update result
   * @throws WxErrorException .
   */
  WxCloudDatabaseUpdateResult databaseUpdate(String env, String query) throws WxErrorException;

  /**
   * db.collection('geo')
   * .where({
   * price: _.gt(10)
   * })
   * .orderBy('_id', 'asc')
   * .orderBy('price', 'desc')
   * .skip(1)
   * .limit(10)
   * .get()
   *
   * @param collection the collection
   * @param whereJson  the where json
   * @param orderBy    the order by
   * @param skip       the skip
   * @param limit      the limit
   * @return wx cloud database query result
   * @throws WxErrorException the wx error exception
   */
  WxCloudDatabaseQueryResult query(String collection, String whereJson, Map<String, String> orderBy,
                                   Integer skip, Integer limit) throws WxErrorException;

  /**
   * Database query wx cloud database query result.
   *
   * @param query the query
   * @return the wx cloud database query result
   * @throws WxErrorException the wx error exception
   */
  WxCloudDatabaseQueryResult databaseQuery(String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库查询记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseQuery.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasequery?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return . wx cloud database query result
   * @throws WxErrorException .
   */
  WxCloudDatabaseQueryResult databaseQuery(String env, String query) throws WxErrorException;

  /**
   * Database aggregate json array.
   *
   * @param query the query
   * @return the json array
   * @throws WxErrorException the wx error exception
   */
  JsonArray databaseAggregate(String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库聚合记录
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseAggregate.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databaseaggregate?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return . json array
   * @throws WxErrorException .
   */
  JsonArray databaseAggregate(String env, String query) throws WxErrorException;

  /**
   * Count long.
   *
   * @param collection the collection
   * @param whereJson  the where json
   * @return the long
   * @throws WxErrorException the wx error exception
   */
  Long count(String collection, String whereJson) throws WxErrorException;

  /**
   * Database count long.
   *
   * @param query the query
   * @return the long
   * @throws WxErrorException the wx error exception
   */
  Long databaseCount(String query) throws WxErrorException;

  /**
   * <pre>
   * 统计集合记录数或统计查询语句对应的结果记录数
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCount.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasecount?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param query 数据库操作语句
   * @return 记录数量 long
   * @throws WxErrorException .
   */
  Long databaseCount(String env, String query) throws WxErrorException;

  /**
   * Update index.
   *
   * @param collectionName the collection name
   * @param createIndexes  the create indexes
   * @param dropIndexNames the drop index names
   * @throws WxErrorException the wx error exception
   */
  void updateIndex(String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes,
                   List<String> dropIndexNames) throws WxErrorException;

  /**
   * <pre>
   * 变更数据库索引
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/updateIndex.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/updateindex?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param collectionName 集合名称
   * @param createIndexes  新增索引对象
   * @param dropIndexNames 要删除的索引的名字
   * @throws WxErrorException .
   */
  void updateIndex(String env, String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes,
                   List<String> dropIndexNames) throws WxErrorException;

  /**
   * Database migrate import long.
   *
   * @param collectionName the collection name
   * @param filePath       the file path
   * @param fileType       the file type
   * @param stopOnError    the stop on error
   * @param conflictMode   the conflict mode
   * @return the long
   * @throws WxErrorException the wx error exception
   */
  Long databaseMigrateImport(String collectionName, String filePath, int fileType,
                             boolean stopOnError, int conflictMode) throws WxErrorException;

  /**
   * <pre>
   * 数据库导入
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseMigrateImport
   * .html
   * 请求地址： POST https://api.weixin.qq.com/tcb/databasemigrateimport?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param collectionName 导入collection名
   * @param filePath       导入文件路径(导入文件需先上传到同环境的存储中，可使用开发者工具或 HTTP API的上传文件 API上传）
   * @param fileType       导入文件类型， 1	JSON， 2	CSV
   * @param stopOnError    是否在遇到错误时停止导入
   * @param conflictMode   冲突处理模式 ： 1	INSERT	,    2	UPSERT
   * @return jobId long
   * @throws WxErrorException .
   */
  Long databaseMigrateImport(String env, String collectionName, String filePath, int fileType, boolean stopOnError,
                             int conflictMode) throws WxErrorException;

  /**
   * Database migrate export long.
   *
   * @param filePath the file path
   * @param fileType the file type
   * @param query    the query
   * @return the long
   * @throws WxErrorException the wx error exception
   */
  Long databaseMigrateExport(String filePath, int fileType, String query) throws WxErrorException;

  /**
   * <pre>
   * 数据库导出
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseMigrateExport
   * .html
   * 请求地址： POST https://api.weixin.qq.com/tcb/databasemigrateexport?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env      云环境ID
   * @param filePath 导出文件路径（文件会导出到同环境的云存储中，可使用获取下载链接 API 获取下载链接）
   * @param fileType 导出文件类型， 1	JSON， 2	CSV
   * @param query    导出条件
   * @return jobId long
   * @throws WxErrorException .
   */
  Long databaseMigrateExport(String env, String filePath, int fileType, String query) throws WxErrorException;

  /**
   * Database migrate query info wx cloud cloud database migrate query info result.
   *
   * @param jobId the job id
   * @return the wx cloud cloud database migrate query info result
   * @throws WxErrorException the wx error exception
   */
  WxCloudCloudDatabaseMigrateQueryInfoResult databaseMigrateQueryInfo(Long jobId) throws WxErrorException;

  /**
   * <pre>
   *   数据库迁移状态查询
   *
   *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database
   *  /databaseMigrateQueryInfo.html
   *  请求地址：POST https://api.weixin.qq.com/tcb/databasemigratequeryinfo?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env   云环境ID
   * @param jobId 迁移任务ID
   * @return . wx cloud cloud database migrate query info result
   * @throws WxErrorException .
   */
  WxCloudCloudDatabaseMigrateQueryInfoResult databaseMigrateQueryInfo(String env, Long jobId) throws WxErrorException;

  /**
   * Upload file wx cloud upload file result.
   *
   * @param path the path
   * @return the wx cloud upload file result
   * @throws WxErrorException the wx error exception
   */
  WxCloudUploadFileResult uploadFile(String path) throws WxErrorException;

  /**
   * <pre>
   * 获取文件上传链接
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/uploadFile.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/uploadfile?access_token=ACCESS_TOKEN
   *
   * </pre>
   *
   * @param env  云环境ID
   * @param path 上传路径
   * @return 上传结果 wx cloud upload file result
   * @throws WxErrorException .
   */
  WxCloudUploadFileResult uploadFile(String env, String path) throws WxErrorException;

  /**
   * Batch download file wx cloud batch download file result.
   *
   * @param fileIds the file ids
   * @param maxAges the max ages
   * @return the wx cloud batch download file result
   * @throws WxErrorException the wx error exception
   */
  WxCloudBatchDownloadFileResult batchDownloadFile(String[] fileIds, long[] maxAges) throws WxErrorException;

  /**
   * <pre>
   * 获取文件下载链接
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/batchDownloadFile.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/batchdownloadfile?access_token=ACCESS_TOKEN
   *
   * </pre>
   *
   * @param env     云环境ID
   * @param fileIds 文件ID列表
   * @param maxAges 下载链接有效期列表，对应文件id列表
   * @return 下载链接信息 wx cloud batch download file result
   * @throws WxErrorException .
   */
  WxCloudBatchDownloadFileResult batchDownloadFile(String env, String[] fileIds, long[] maxAges) throws WxErrorException;

  /**
   * Batch delete file wx cloud batch delete file result.
   *
   * @param fileIds the file ids
   * @return the wx cloud batch delete file result
   * @throws WxErrorException the wx error exception
   */
  WxCloudBatchDeleteFileResult batchDeleteFile(String[] fileIds) throws WxErrorException;

  /**
   * <pre>
   * 删除文件
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/batchDeleteFile.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/batchdeletefile?access_token=ACCESS_TOKEN
   *
   * </pre>
   *
   * @param env     云环境ID
   * @param fileIds 文件ID列表
   * @return 下载链接信息 wx cloud batch delete file result
   * @throws WxErrorException .
   */
  WxCloudBatchDeleteFileResult batchDeleteFile(String env, String[] fileIds) throws WxErrorException;

  /**
   * <pre>
   *  获取腾讯云API调用凭证
   *
   *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/utils/getQcloudToken.html
   *  请求地址：POST https://api.weixin.qq.com/tcb/getqcloudtoken?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param lifeSpan 有效期（单位为秒，最大7200）
   * @return . qcloud token
   * @throws WxErrorException .
   */
  WxCloudGetQcloudTokenResult getQcloudToken(long lifeSpan) throws WxErrorException;

  /**
   * Database collection add.
   *
   * @param collectionName the collection name
   * @throws WxErrorException the wx error exception
   */
  void databaseCollectionAdd(String collectionName) throws WxErrorException;

  /**
   * <pre>
   * 新增集合
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCollectionAdd
   * .html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasecollectionadd?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param collectionName 集合名称
   * @throws WxErrorException .
   */
  void databaseCollectionAdd(String env, String collectionName) throws WxErrorException;

  /**
   * Database collection delete.
   *
   * @param collectionName the collection name
   * @throws WxErrorException the wx error exception
   */
  void databaseCollectionDelete(String collectionName) throws WxErrorException;

  /**
   * <pre>
   * 删除集合
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database
   * /databaseCollectionDelete.html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasecollectionadd?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env            云环境ID
   * @param collectionName 集合名称
   * @throws WxErrorException .
   */
  void databaseCollectionDelete(String env, String collectionName) throws WxErrorException;

  /**
   * Database collection get wx cloud database collection get result.
   *
   * @param limit  the limit
   * @param offset the offset
   * @return the wx cloud database collection get result
   * @throws WxErrorException the wx error exception
   */
  WxCloudDatabaseCollectionGetResult databaseCollectionGet(Long limit, Long offset) throws WxErrorException;

  /**
   * <pre>
   * 获取特定云环境下集合信息
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCollectionGet
   * .html
   * 请求地址：POST https://api.weixin.qq.com/tcb/databasecollectionget?access_token=ACCESS_TOKEN
   * </pre>
   *
   * @param env    云环境ID
   * @param limit  获取数量限制，默认值：10
   * @param offset 偏移量,默认值：0
   * @return . wx cloud database collection get result
   * @throws WxErrorException .
   */
  WxCloudDatabaseCollectionGetResult databaseCollectionGet(String env, Long limit, Long offset) throws WxErrorException;

  /**
   * 发送携带 URL Link 的短信
   *
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/cloudbase/cloudbase.sendSmsV2.html
   * @param request
   * @return WxCloudSendSmsV2Result
   * @throws WxErrorException
   */
  WxCloudSendSmsV2Result sendSmsV2(WxCloudSendSmsV2Request request) throws WxErrorException;

}
