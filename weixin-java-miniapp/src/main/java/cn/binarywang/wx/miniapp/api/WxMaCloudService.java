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
   * 触发云函数。注意：HTTP API 途径触发云函数不包含用户信息。
   *
   * @param name             云函数名称
   * @param body             云函数的传入参数，具体结构由开发者定义
   * @return                 云函数返回的buffer
   * @throws WxErrorException 调用失败时抛出
   */
  String invokeCloudFunction(String name, String body) throws WxErrorException;

  /**
   * 触发云函数。注意：HTTP API 途径触发云函数不包含用户信息。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/functions/invokeCloudFunction.html
   *
   * @param env              云开发环境ID
   * @param name             云函数名称
   * @param body             云函数的传入参数，具体结构由开发者定义
   * @return                 云函数返回的buffer
   * @throws WxErrorException 调用失败时抛出
   */
  String invokeCloudFunction(String env, String name, String body) throws WxErrorException;

  /**
   * 批量添加记录到集合。
   *
   * @param collection       集合名称
   * @param list             要添加的记录列表
   * @return                 插入成功的记录ID列表
   * @throws WxErrorException 添加失败时抛出
   */
  List<String> add(String collection, List<?> list) throws WxErrorException;

  /**
   * 添加单条记录到集合。
   *
   * @param collection       集合名称
   * @param obj              要添加的记录对象
   * @return                 插入成功的记录ID
   * @throws WxErrorException 添加失败时抛出
   */
  String add(String collection, Object obj) throws WxErrorException;

  /**
   * 数据库插入记录。
   *
   * @param query            数据库操作语句
   * @return                 插入成功的数据集合主键_id列表
   * @throws WxErrorException 插入失败时抛出
   */
  JsonArray databaseAdd(String query) throws WxErrorException;

  /**
   * 数据库插入记录。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseAdd.html
   *
   * @param env              云环境ID
   * @param query            数据库操作语句
   * @return                 插入成功的数据集合主键_id列表
   * @throws WxErrorException 插入失败时抛出
   */
  JsonArray databaseAdd(String env, String query) throws WxErrorException;

  /**
   * 删除集合中符合条件的记录。
   *
   * @param collection       集合名称
   * @param whereJson        查询条件JSON字符串
   * @return                 删除的记录数量
   * @throws WxErrorException 删除失败时抛出
   */
  Integer delete(String collection, String whereJson) throws WxErrorException;

  /**
   * 数据库删除记录。
   *
   * @param query            数据库操作语句
   * @return                 删除记录数量
   * @throws WxErrorException 删除失败时抛出
   */
  int databaseDelete(String query) throws WxErrorException;

  /**
   * 数据库删除记录。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseDelete.html
   *
   * @param env              云环境ID
   * @param query            数据库操作语句
   * @return                 删除记录数量
   * @throws WxErrorException 删除失败时抛出
   */
  int databaseDelete(String env, String query) throws WxErrorException;

  /**
   * 更新集合中符合条件的记录。
   *
   * @param collection       集合名称
   * @param whereJson        查询条件JSON字符串
   * @param updateJson       更新内容JSON字符串
   * @return                 更新结果对象
   * @throws WxErrorException 更新失败时抛出
   */
  WxCloudDatabaseUpdateResult update(String collection, String whereJson, String updateJson) throws WxErrorException;

  /**
   * 数据库更新记录。
   *
   * @param query            数据库操作语句
   * @return                 更新结果对象
   * @throws WxErrorException 更新失败时抛出
   */
  WxCloudDatabaseUpdateResult databaseUpdate(String query) throws WxErrorException;

  /**
   * 数据库更新记录。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseUpdate.html
   *
   * @param env              云环境ID
   * @param query            数据库操作语句
   * @return                 更新结果对象
   * @throws WxErrorException 更新失败时抛出
   */
  WxCloudDatabaseUpdateResult databaseUpdate(String env, String query) throws WxErrorException;

  /**
   * 查询集合中的记录。
   * 示例：
   * db.collection('geo')
   *   .where({price: _.gt(10)})
   *   .orderBy('_id', 'asc')
   *   .orderBy('price', 'desc')
   *   .skip(1)
   *   .limit(10)
   *   .get()
   *
   * @param collection       集合名称
   * @param whereJson        查询条件JSON字符串
   * @param orderBy          排序条件Map
   * @param skip             跳过记录数
   * @param limit            限制返回记录数
   * @return                 查询结果对象
   * @throws WxErrorException 查询失败时抛出
   */
  WxCloudDatabaseQueryResult query(String collection, String whereJson, Map<String, String> orderBy,
                                   Integer skip, Integer limit) throws WxErrorException;

  /**
   * 数据库查询记录。
   *
   * @param query            数据库操作语句
   * @return                 查询结果对象
   * @throws WxErrorException 查询失败时抛出
   */
  WxCloudDatabaseQueryResult databaseQuery(String query) throws WxErrorException;

  /**
   * 数据库查询记录。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseQuery.html
   *
   * @param env              云环境ID
   * @param query            数据库操作语句
   * @return                 查询结果对象
   * @throws WxErrorException 查询失败时抛出
   */
  WxCloudDatabaseQueryResult databaseQuery(String env, String query) throws WxErrorException;

  /**
   * 数据库聚合记录。
   *
   * @param query            数据库操作语句
   * @return                 聚合结果JSON数组
   * @throws WxErrorException 聚合失败时抛出
   */
  JsonArray databaseAggregate(String query) throws WxErrorException;

  /**
   * 数据库聚合记录。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseAggregate.html
   *
   * @param env              云环境ID
   * @param query            数据库操作语句
   * @return                 聚合结果JSON数组
   * @throws WxErrorException 聚合失败时抛出
   */
  JsonArray databaseAggregate(String env, String query) throws WxErrorException;

  /**
   * 统计集合中符合条件的记录数。
   *
   * @param collection       集合名称
   * @param whereJson        查询条件JSON字符串
   * @return                 记录数量
   * @throws WxErrorException 统计失败时抛出
   */
  Long count(String collection, String whereJson) throws WxErrorException;

  /**
   * 统计集合记录数或统计查询语句对应的结果记录数。
   *
   * @param query            数据库操作语句
   * @return                 记录数量
   * @throws WxErrorException 统计失败时抛出
   */
  Long databaseCount(String query) throws WxErrorException;

  /**
   * 统计集合记录数或统计查询语句对应的结果记录数。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCount.html
   *
   * @param env              云环境ID
   * @param query            数据库操作语句
   * @return                 记录数量
   * @throws WxErrorException 统计失败时抛出
   */
  Long databaseCount(String env, String query) throws WxErrorException;

  /**
   * 变更数据库索引。
   *
   * @param collectionName   集合名称
   * @param createIndexes    新增索引对象列表
   * @param dropIndexNames   要删除的索引名称列表
   * @throws WxErrorException 更新失败时抛出
   */
  void updateIndex(String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes,
                   List<String> dropIndexNames) throws WxErrorException;

  /**
   * 变更数据库索引。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/updateIndex.html
   *
   * @param env              云环境ID
   * @param collectionName   集合名称
   * @param createIndexes    新增索引对象列表
   * @param dropIndexNames   要删除的索引名称列表
   * @throws WxErrorException 更新失败时抛出
   */
  void updateIndex(String env, String collectionName, List<WxCloudDatabaseCreateIndexRequest> createIndexes,
                   List<String> dropIndexNames) throws WxErrorException;

  /**
   * 数据库导入。
   *
   * @param collectionName   导入collection名
   * @param filePath         导入文件路径
   * @param fileType         导入文件类型，1：JSON，2：CSV
   * @param stopOnError      是否在遇到错误时停止导入
   * @param conflictMode     冲突处理模式，1：INSERT，2：UPSERT
   * @return                 任务ID
   * @throws WxErrorException 导入失败时抛出
   */
  Long databaseMigrateImport(String collectionName, String filePath, int fileType,
                             boolean stopOnError, int conflictMode) throws WxErrorException;

  /**
   * 数据库导入。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseMigrateImport.html
   * 注意：导入文件需先上传到同环境的存储中，可使用开发者工具或HTTP API的上传文件API上传
   *
   * @param env              云环境ID
   * @param collectionName   导入collection名
   * @param filePath         导入文件路径
   * @param fileType         导入文件类型，1：JSON，2：CSV
   * @param stopOnError      是否在遇到错误时停止导入
   * @param conflictMode     冲突处理模式，1：INSERT，2：UPSERT
   * @return                 任务ID
   * @throws WxErrorException 导入失败时抛出
   */
  Long databaseMigrateImport(String env, String collectionName, String filePath, int fileType, boolean stopOnError,
                             int conflictMode) throws WxErrorException;

  /**
   * 数据库导出。
   *
   * @param filePath         导出文件路径
   * @param fileType         导出文件类型，1：JSON，2：CSV
   * @param query            导出条件
   * @return                 任务ID
   * @throws WxErrorException 导出失败时抛出
   */
  Long databaseMigrateExport(String filePath, int fileType, String query) throws WxErrorException;

  /**
   * 数据库导出。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseMigrateExport.html
   * 注意：文件会导出到同环境的云存储中，可使用获取下载链接API获取下载链接
   *
   * @param env              云环境ID
   * @param filePath         导出文件路径
   * @param fileType         导出文件类型，1：JSON，2：CSV
   * @param query            导出条件
   * @return                 任务ID
   * @throws WxErrorException 导出失败时抛出
   */
  Long databaseMigrateExport(String env, String filePath, int fileType, String query) throws WxErrorException;

  /**
   * 数据库迁移状态查询。
   *
   * @param jobId            迁移任务ID
   * @return                 迁移状态查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxCloudCloudDatabaseMigrateQueryInfoResult databaseMigrateQueryInfo(Long jobId) throws WxErrorException;

  /**
   * 数据库迁移状态查询。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseMigrateQueryInfo.html
   *
   * @param env              云环境ID
   * @param jobId            迁移任务ID
   * @return                 迁移状态查询结果
   * @throws WxErrorException 查询失败时抛出
   */
  WxCloudCloudDatabaseMigrateQueryInfoResult databaseMigrateQueryInfo(String env, Long jobId) throws WxErrorException;

  /**
   * 获取文件上传链接。
   *
   * @param path             上传路径
   * @return                 上传结果对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxCloudUploadFileResult uploadFile(String path) throws WxErrorException;

  /**
   * 获取文件上传链接。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/uploadFile.html
   *
   * @param env              云环境ID
   * @param path             上传路径
   * @return                 上传结果对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxCloudUploadFileResult uploadFile(String env, String path) throws WxErrorException;

  /**
   * 获取文件下载链接。
   *
   * @param fileIds          文件ID数组
   * @param maxAges          下载链接有效期数组，对应文件id列表
   * @return                 下载链接信息对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxCloudBatchDownloadFileResult batchDownloadFile(String[] fileIds, long[] maxAges) throws WxErrorException;

  /**
   * 获取文件下载链接。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/batchDownloadFile.html
   *
   * @param env              云环境ID
   * @param fileIds          文件ID数组
   * @param maxAges          下载链接有效期数组，对应文件id列表
   * @return                 下载链接信息对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxCloudBatchDownloadFileResult batchDownloadFile(String env, String[] fileIds, long[] maxAges) throws WxErrorException;

  /**
   * 删除文件。
   *
   * @param fileIds          文件ID数组
   * @return                 删除结果对象
   * @throws WxErrorException 删除失败时抛出
   */
  WxCloudBatchDeleteFileResult batchDeleteFile(String[] fileIds) throws WxErrorException;

  /**
   * 删除文件。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/storage/batchDeleteFile.html
   *
   * @param env              云环境ID
   * @param fileIds          文件ID数组
   * @return                 删除结果对象
   * @throws WxErrorException 删除失败时抛出
   */
  WxCloudBatchDeleteFileResult batchDeleteFile(String env, String[] fileIds) throws WxErrorException;

  /**
   * 获取腾讯云API调用凭证。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/utils/getQcloudToken.html
   *
   * @param lifeSpan         有效期（单位为秒，最大7200）
   * @return                 腾讯云Token结果对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxCloudGetQcloudTokenResult getQcloudToken(long lifeSpan) throws WxErrorException;

  /**
   * 新增集合。
   *
   * @param collectionName   集合名称
   * @throws WxErrorException 新增失败时抛出
   */
  void databaseCollectionAdd(String collectionName) throws WxErrorException;

  /**
   * 新增集合。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCollectionAdd.html
   *
   * @param env              云环境ID
   * @param collectionName   集合名称
   * @throws WxErrorException 新增失败时抛出
   */
  void databaseCollectionAdd(String env, String collectionName) throws WxErrorException;

  /**
   * 删除集合。
   *
   * @param collectionName   集合名称
   * @throws WxErrorException 删除失败时抛出
   */
  void databaseCollectionDelete(String collectionName) throws WxErrorException;

  /**
   * 删除集合。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCollectionDelete.html
   *
   * @param env              云环境ID
   * @param collectionName   集合名称
   * @throws WxErrorException 删除失败时抛出
   */
  void databaseCollectionDelete(String env, String collectionName) throws WxErrorException;

  /**
   * 获取特定云环境下集合信息。
   *
   * @param limit            获取数量限制，默认值：10
   * @param offset           偏移量，默认值：0
   * @return                 集合信息获取结果对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxCloudDatabaseCollectionGetResult databaseCollectionGet(Long limit, Long offset) throws WxErrorException;

  /**
   * 获取特定云环境下集合信息。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/wxcloud/reference-http-api/database/databaseCollectionGet.html
   *
   * @param env              云环境ID
   * @param limit            获取数量限制，默认值：10
   * @param offset           偏移量，默认值：0
   * @return                 集合信息获取结果对象
   * @throws WxErrorException 获取失败时抛出
   */
  WxCloudDatabaseCollectionGetResult databaseCollectionGet(String env, Long limit, Long offset) throws WxErrorException;

  /**
   * 发送携带 URL Link 的短信。
   * 文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/cloudbase/cloudbase.sendSmsV2.html
   *
   * @param request          短信发送请求对象
   * @return                 短信发送结果对象
   * @throws WxErrorException 发送失败时抛出
   */
  WxCloudSendSmsV2Result sendSmsV2(WxCloudSendSmsV2Request request) throws WxErrorException;
}
