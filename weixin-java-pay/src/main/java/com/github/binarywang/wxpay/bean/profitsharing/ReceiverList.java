package com.github.binarywang.wxpay.bean.profitsharing;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Wang GuangXin 2019/10/22 11:01
 * @version 1.0
 */

public class ReceiverList implements Serializable {
  private static final long serialVersionUID = -1316860887694489921L;
  ArrayList<Receiver> list;

  private ReceiverList() {
  }

  /**
   * 获取一个实例
   * @return
   */
  public static ReceiverList getInstance() {
    ReceiverList receiverList = new ReceiverList();
    receiverList.list = new ArrayList<>();
    return receiverList;
  }

  /**
   * 添加一个分账条目
   * 注意微信上限为50个
   * @param receiver
   * @return
   */
  public ReceiverList add(Receiver receiver) {
    this.list.add(receiver);
    return this;
  }


  /**
   * 转为JSON格式
   * @return
   */
  public String toJSONString() {
    Gson gson = new Gson();
    return gson.toJson(this.list);
  }

}
