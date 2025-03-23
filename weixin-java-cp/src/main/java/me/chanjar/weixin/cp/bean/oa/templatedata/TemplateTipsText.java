package me.chanjar.weixin.cp.bean.oa.templatedata;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author  mrsiu@msn.com
 * @date  2025/1/16 09:43
 * @version  1.0
 */
@Data
public class TemplateTipsText {
    @SerializedName("sub_text")
    private List<TemplateTipsSubText> subText;
}
