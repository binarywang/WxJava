package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaUploadAuthMaterialResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

/**
 * @author penhuozhu
 * @since 2024/01/07
 */
public class OkHttpUploadAuthMaterialRequestExecutor extends UploadAuthMaterialRequestExecutor<OkHttpClient, OkHttpProxyInfo> {

    public OkHttpUploadAuthMaterialRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp) {
        super(requestHttp);
    }

    @Override
    public WxMaUploadAuthMaterialResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {

        RequestBody body = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"))
                .addFormDataPart("media",
                        file.getName(),
                        RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .build();
        Request request = new Request.Builder().url(uri).post(body).build();

        Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
        String responseContent = response.body().string();
        WxError error = WxError.fromJson(responseContent, wxType);
        if (error.getErrorCode() != 0) {
            throw new WxErrorException(error);
        }
        return WxMaUploadAuthMaterialResult.fromJson(responseContent);
    }
}
