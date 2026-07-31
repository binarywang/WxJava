package me.chanjar.weixin.open.bean.result;

import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


public class WxFastMaCanSetCategoryResultTest {
  @Test
  public void testFromJson() throws Exception {
    String json = "{\n" +
      "    \"errcode\": 0, \n" +
      "    \"errmsg\": \"ok\", \n" +
      "    \"categories_list\": {\n" +
      "        \"categories\": [\n" +
      "            {\n" +
      "                \"id\": 1, \n" +
      "                \"name\": \"快递业与邮政\", \n" +
      "                \"level\": 1, \n" +
      "                \"father\": 0, \n" +
      "                \"children\": [\n" +
      "                    2, \n" +
      "                    5, \n" +
      "                    556, \n" +
      "                    558, \n" +
      "                    1033\n" +
      "                ], \n" +
      "                \"sensitive_type\": 0, \n" +
      "                \"type_list\": [ ], \n" +
      "                \"qualify\": {\n" +
      "                    \"exter_list\": [ ], \n" +
      "                    \"remark\": \"\"\n" +
      "                }, \n" +
      "                \"available_api_list\": [ ], \n" +
      "                \"apis\": [ ], \n" +
      "                \"available_for_plugin\": true\n" +
      "            }, \n" +
      "            {\n" +
      "                \"id\": 8, \n" +
      "                \"name\": \"教育\", \n" +
      "                \"level\": 1, \n" +
      "                \"father\": 0, \n" +
      "                \"children\": [\n" +
      "                    9, \n" +
      "                    590, \n" +
      "                    592, \n" +
      "                    27, \n" +
      "                    32, \n" +
      "                    37, \n" +
      "                    39, \n" +
      "                    578, \n" +
      "                    580, \n" +
      "                    582, \n" +
      "                    1043\n" +
      "                ], \n" +
      "                \"sensitive_type\": 0, \n" +
      "                \"type_list\": [ ], \n" +
      "                \"qualify\": {\n" +
      "                    \"exter_list\": [ ], \n" +
      "                    \"remark\": \"\"\n" +
      "                }, \n" +
      "                \"is_hidden\": false, \n" +
      "                \"available_api_list\": [ ], \n" +
      "                \"type\": \"NORMAL\", \n" +
      "                \"apis\": [ ], \n" +
      "                \"available_for_plugin\": true\n" +
      "            }\n" +
      "        ]\n" +
      "    }\n" +
      "}";
    WxFastMaCanSetCategoryResult res = WxOpenGsonBuilder.create().fromJson(json, WxFastMaCanSetCategoryResult.class);

    assertNotNull(res);
    assertNotNull(res.getCategoriesList());
    assertEquals(res.getErrcode(), "0");
    assertEquals(res.getErrmsg(), "ok");
    assertEquals(res.getErrCode(), 0);
    assertTrue(res.isSuccess());
    System.out.println(res);
  }

}
