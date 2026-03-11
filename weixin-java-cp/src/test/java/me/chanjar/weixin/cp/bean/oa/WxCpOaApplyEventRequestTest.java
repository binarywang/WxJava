package me.chanjar.weixin.cp.bean.oa;

import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.bean.oa.applydata.ApplyDataContent;
import me.chanjar.weixin.cp.bean.oa.applydata.ContentValue;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-07-18
 */
public class WxCpOaApplyEventRequestTest {
  /**
   * Test to json.
   */
  @Test
  public void testToJson() {
    String json = "{\n" +
      "    \"creator_userid\": \"WangXiaoMing\",\n" +
      "    \"template_id\": \"3Tka1eD6v6JfzhDMqPd3aMkFdxqtJMc2ZRioeFXkaaa\",\n" +
      "    \"use_template_approver\":0,\n" +
      "    \"approver\": [\n" +
      "        {\n" +
      "            \"attr\": 2,\n" +
      "            \"userid\": [\"WuJunJie\",\"WangXiaoMing\"]\n" +
      "        },\n" +
      "        {\n" +
      "            \"attr\": 1,\n" +
      "            \"userid\": [\"LiuXiaoGang\"]\n" +
      "        }\n" +
      "    ],\n" +
      "    \"notifyer\":[ \"WuJunJie\",\"WangXiaoMing\" ],\n" +
      "    \"notify_type\" : 1,\n" +
      "    \"apply_data\": {\n" +
      "         \"contents\": [\n" +
      "                {\n" +
      "                    \"control\": \"Text\",\n" +
      "                    \"id\": \"Text-15111111111\",\n" +
      "                    \"value\": {\n" +
      "                        \"text\": \"文本填写的内容\"\n" +
      "                    }\n" +
      "                }\n" +
      "            ]\n" +
      "    },\n" +
      "    \"summary_list\": [\n" +
      "        {\n" +
      "            \"summary_info\": [{\n" +
      "                \"text\": \"摘要第1行\",\n" +
      "                \"lang\": \"zh_CN\"\n" +
      "            }]\n" +
      "        },\n" +
      "        {\n" +
      "            \"summary_info\": [{\n" +
      "                \"text\": \"摘要第2行\",\n" +
      "                \"lang\": \"zh_CN\"\n" +
      "            }]\n" +
      "        },\n" +
      "        {\n" +
      "            \"summary_info\": [{\n" +
      "                \"text\": \"摘要第3行\",\n" +
      "                \"lang\": \"zh_CN\"\n" +
      "            }]\n" +
      "        }\n" +
      "    ]\n" +
      "}";

    WxCpOaApplyEventRequest request = new WxCpOaApplyEventRequest();
    request.setCreatorUserId("WangXiaoMing")
      .setTemplateId("3Tka1eD6v6JfzhDMqPd3aMkFdxqtJMc2ZRioeFXkaaa")
      .setUseTemplateApprover(0)
      .setApprovers(Arrays.asList(new WxCpOaApplyEventRequest.Approver().setAttr(2).setUserIds(new String[]{"WuJunJie"
          , "WangXiaoMing"}),
        new WxCpOaApplyEventRequest.Approver().setAttr(1).setUserIds(new String[]{"LiuXiaoGang"})))
      .setNotifiers(new String[]{"WuJunJie", "WangXiaoMing"})
      .setNotifyType(1)
      .setApplyData(new WxCpOaApplyEventRequest.ApplyData()
        .setContents(Collections.singletonList(new ApplyDataContent()
          .setControl("Text").setId("Text-15111111111").setValue(new ContentValue().setText("文本填写的内容")))))
      .setSummaryList(Arrays.asList(new SummaryInfo()
          .setSummaryInfoData(Collections.singletonList(new SummaryInfo.SummaryInfoData().setLang("zh_CN").setText(
            "摘要第1行"))),
        new SummaryInfo()
          .setSummaryInfoData(Collections.singletonList(new SummaryInfo.SummaryInfoData().setLang("zh_CN").setText(
            "摘要第2行"))),
        new SummaryInfo()
          .setSummaryInfoData(Collections.singletonList(new SummaryInfo.SummaryInfoData().setLang("zh_CN").setText(
            "摘要第3行")))))
    ;

    assertThat(request.toJson()).isEqualTo(GsonParser.parse(json).toString());
  }

  /**
   * Test to json with process.
   */
  @Test
  public void testToJsonWithProcess() {
    String json = "{\n" +
      "    \"creator_userid\": \"WangXiaoMing\",\n" +
      "    \"template_id\": \"3Tka1eD6v6JfzhDMqPd3aMkFdxqtJMc2ZRioeFXkaaa\",\n" +
      "    \"use_template_approver\":0,\n" +
      "    \"process\": {\n" +
      "        \"node_list\": [\n" +
      "            {\n" +
      "                \"type\": 1,\n" +
      "                \"apv_rel\": 2,\n" +
      "                \"userid\": [\"WuJunJie\",\"WangXiaoMing\"]\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": 1,\n" +
      "                \"apv_rel\": 1,\n" +
      "                \"userid\": [\"LiuXiaoGang\"]\n" +
      "            },\n" +
      "            {\n" +
      "                \"type\": 2,\n" +
      "                \"userid\": [\"ZhangSan\",\"LiSi\"]\n" +
      "            }\n" +
      "        ]\n" +
      "    },\n" +
      "    \"apply_data\": {\n" +
      "         \"contents\": [\n" +
      "                {\n" +
      "                    \"control\": \"Text\",\n" +
      "                    \"id\": \"Text-15111111111\",\n" +
      "                    \"value\": {\n" +
      "                        \"text\": \"文本填写的内容\"\n" +
      "                    }\n" +
      "                }\n" +
      "            ]\n" +
      "    },\n" +
      "    \"summary_list\": [\n" +
      "        {\n" +
      "            \"summary_info\": [{\n" +
      "                \"text\": \"摘要第1行\",\n" +
      "                \"lang\": \"zh_CN\"\n" +
      "            }]\n" +
      "        },\n" +
      "        {\n" +
      "            \"summary_info\": [{\n" +
      "                \"text\": \"摘要第2行\",\n" +
      "                \"lang\": \"zh_CN\"\n" +
      "            }]\n" +
      "        },\n" +
      "        {\n" +
      "            \"summary_info\": [{\n" +
      "                \"text\": \"摘要第3行\",\n" +
      "                \"lang\": \"zh_CN\"\n" +
      "            }]\n" +
      "        }\n" +
      "    ]\n" +
      "}";

    WxCpOaApplyEventRequest request = new WxCpOaApplyEventRequest();
    request.setCreatorUserId("WangXiaoMing")
      .setTemplateId("3Tka1eD6v6JfzhDMqPd3aMkFdxqtJMc2ZRioeFXkaaa")
      .setUseTemplateApprover(0)
      .setProcess(new WxCpOaApplyEventRequest.Process()
        .setNodeList(Arrays.asList(
          new WxCpOaApplyEventRequest.ProcessNode()
            .setType(1)
            .setApvRel(2)
            .setUserIds(new String[]{"WuJunJie", "WangXiaoMing"}),
          new WxCpOaApplyEventRequest.ProcessNode()
            .setType(1)
            .setApvRel(1)
            .setUserIds(new String[]{"LiuXiaoGang"}),
          new WxCpOaApplyEventRequest.ProcessNode()
            .setType(2)
            .setUserIds(new String[]{"ZhangSan", "LiSi"})
        )))
      .setApplyData(new WxCpOaApplyEventRequest.ApplyData()
        .setContents(Collections.singletonList(new ApplyDataContent()
          .setControl("Text").setId("Text-15111111111").setValue(new ContentValue().setText("文本填写的内容")))))
      .setSummaryList(Arrays.asList(new SummaryInfo()
          .setSummaryInfoData(Collections.singletonList(new SummaryInfo.SummaryInfoData().setLang("zh_CN").setText(
            "摘要第1行"))),
        new SummaryInfo()
          .setSummaryInfoData(Collections.singletonList(new SummaryInfo.SummaryInfoData().setLang("zh_CN").setText(
            "摘要第2行"))),
        new SummaryInfo()
          .setSummaryInfoData(Collections.singletonList(new SummaryInfo.SummaryInfoData().setLang("zh_CN").setText(
            "摘要第3行")))))
    ;

    assertThat(request.toJson()).isEqualTo(GsonParser.parse(json).toString());
  }
}
