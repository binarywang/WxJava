package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaUploadAuthMaterialResult;
import jodd.http.HttpConnectionProvider;
import jodd.http.ProxyInfo;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.OkHttpClient;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * 小程序认证上传补充材料
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @author penhuozhu
 * @since 2024/01/07
 */
public abstract class UploadAuthMaterialRequestExecutor<H, P> implements RequestExecutor<WxMaUploadAuthMaterialResult, File> {
    protected RequestHttp<H, P> requestHttp;

    public UploadAuthMaterialRequestExecutor(RequestHttp<H, P> requestHttp) {
        this.requestHttp = requestHttp;
    }

    @Override
    public void execute(String uri, File data, ResponseHandler<WxMaUploadAuthMaterialResult> handler, WxType wxType) throws WxErrorException, IOException {
        handler.handle(this.execute(uri, data, wxType));
    }

    @SuppressWarnings("unchecked")
    public static RequestExecutor<WxMaUploadAuthMaterialResult, File> create(RequestHttp<?, ?> requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new ApacheUploadAuthMaterialRequestExecutor((RequestHttp<CloseableHttpClient, HttpHost>) requestHttp);
            case JODD_HTTP:
                return new JoddHttpUploadAuthMaterialRequestExecutor((RequestHttp<HttpConnectionProvider, ProxyInfo>) requestHttp);
            case OK_HTTP:
                return new OkHttpUploadAuthMaterialRequestExecutor((RequestHttp<OkHttpClient, OkHttpProxyInfo>) requestHttp);
            default:
                return null;
        }
    }
}
