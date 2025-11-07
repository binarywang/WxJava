package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

import java.io.File;

/**
 * <pre>
 * 二维码相关操作接口
 * 文档地址：https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435
 * </pre>
 *
 * @author Binary Wang
 */
public interface WxMpQrcodeService {
    /**
     * <pre>
     * 换取临时二维码ticket
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param sceneId       场景值ID，临时二维码时为32位非0整型
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @return 二维码ticket，可用于获取二维码图片
     * @throws WxErrorException 微信API调用异常
     */
    WxMpQrCodeTicket qrCodeCreateTmpTicket(int sceneId, Integer expireSeconds) throws WxErrorException;


    /**
     * <pre>
     * 换取临时二维码ticket
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param sceneStr      场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
     * @return 二维码ticket，可用于获取二维码图片
     * @throws WxErrorException 微信API调用异常
     */
    WxMpQrCodeTicket qrCodeCreateTmpTicket(String sceneStr, Integer expireSeconds) throws WxErrorException;

    /**
     * <pre>
     * 换取永久二维码ticket
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param sceneId 场景值ID，最大值为100000（目前参数只支持1--100000）
     * @return 二维码ticket，可用于获取二维码图片
     * @throws WxErrorException 微信API调用异常
     */
    WxMpQrCodeTicket qrCodeCreateLastTicket(int sceneId) throws WxErrorException;

    /**
     * <pre>
     * 换取永久字符串二维码ticket
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param sceneStr 参数。字符串类型长度现在为1到64
     * @return 二维码ticket，可用于获取二维码图片
     * @throws WxErrorException 微信API调用异常
     */
    WxMpQrCodeTicket qrCodeCreateLastTicket(String sceneStr) throws WxErrorException;

    /**
     * <pre>
     * 换取二维码图片文件，jpg格式
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param ticket 二维码ticket，通过创建二维码接口获取
     * @return 二维码图片文件，jpg格式
     * @throws WxErrorException 微信API调用异常
     */
    File qrCodePicture(WxMpQrCodeTicket ticket) throws WxErrorException;

    /**
     * <pre>
     * 换取二维码图片url地址（可以选择是否生成压缩的网址）
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param ticket       二维码ticket
     * @param needShortUrl 是否需要压缩的二维码地址
     * @return 二维码图片的URL地址
     * @throws WxErrorException 微信API调用异常
     * @deprecated 请使用 {@link #qrCodePictureUrl(String)} 方法
     */
    @Deprecated
    String qrCodePictureUrl(String ticket, boolean needShortUrl) throws WxErrorException;

    /**
     * <pre>
     * 换取二维码图片url地址
     * 详情请见: <a href="https://mp.weixin.qq.com/wiki?action=doc&id=mp1443433542&t=0.9274944716856435">生成带参数的二维码</a>
     * </pre>
     *
     * @param ticket 二维码ticket，通过创建二维码接口获取
     * @return 二维码图片的URL地址
     * @throws WxErrorException 微信API调用异常
     */
    String qrCodePictureUrl(String ticket) throws WxErrorException;

}
