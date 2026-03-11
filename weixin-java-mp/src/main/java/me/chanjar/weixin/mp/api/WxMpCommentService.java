package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.comment.WxMpCommentListVo;

/**
 * 图文消息留言管理接口.
 * https://developers.weixin.qq.com/doc/offiaccount/Comments_management/Image_Comments_Management_Interface.html
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2019-06-16
 */
public interface WxMpCommentService {
    /**
     * 打开已群发文章评论.
     * https://api.weixin.qq.com/cgi-bin/comment/open?access_token=ACCESS_TOKEN
     *
     * @param msgDataId 群发返回的msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @throws WxErrorException 异常
     */
    void open(String msgDataId, Integer index) throws WxErrorException;

    /**
     * 关闭已群发文章评论.
     * https://api.weixin.qq.com/cgi-bin/comment/close?access_token=ACCESS_TOKEN
     *
     * @param msgDataId 群发返回的msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @throws WxErrorException 异常
     */
    void close(String msgDataId, Integer index) throws WxErrorException;

    /**
     * 查看指定文章的评论数据.
     * https://api.weixin.qq.com/cgi-bin/comment/list?access_token=ACCESS_TOKEN
     *
     * @param msgDataId 群发返回的msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param begin     起始位置
     * @param count     获取数目（大于等于50会被拒绝）
     * @param type      type=0 普通评论和精选评论 type=1 普通评论 type=2 精选评论
     * @return 评论列表数据 wx mp comment list vo
     * @throws WxErrorException 异常
     */
    WxMpCommentListVo list(String msgDataId, Integer index, int begin, int count, int type) throws WxErrorException;

    /**
     * 2.4 将评论标记精选.
     * 接口调用请求: POST https://api.weixin.qq.com/cgi-bin/comment/markelect?access_token=ACCESS_TOKEN
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @throws WxErrorException 异常
     */
    void markElect(String msgDataId, Integer index, Long userCommentId) throws WxErrorException;

    /**
     * 2.5 将评论取消精选.
     * 接口调用请求: POST https://api.weixin.qq.com/cgi-bin/comment/unmarkelect?access_token=ACCESS_TOKEN
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @throws WxErrorException 异常
     */
    void unmarkElect(String msgDataId, Integer index, Long userCommentId) throws WxErrorException;

    /**
     * 2.6 删除评论.
     * 接口调用请求: POST https://api.weixin.qq.com/cgi-bin/comment/delete?access_token=ACCESS_TOKEN
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @throws WxErrorException 异常
     */
    void delete(String msgDataId, Integer index, Long userCommentId) throws WxErrorException;

    /**
     * 2.7 回复评论.
     * 接口调用请求: POST https://api.weixin.qq.com/cgi-bin/comment/reply/add?access_token=ACCESS_TOKEN
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @param content       回复内容
     * @throws WxErrorException 异常
     */
    void replyAdd(String msgDataId, Integer index, Long userCommentId, String content) throws WxErrorException;

    /**
     * 2.8 删除回复.
     * 接口调用请求: POST https://api.weixin.qq.com/cgi-bin/comment/reply/delete?access_token=ACCESS_TOKEN
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @throws WxErrorException 异常
     */
    void replyDelete(String msgDataId, Integer index, Long userCommentId) throws WxErrorException;
}
