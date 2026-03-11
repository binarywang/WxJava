package me.chanjar.weixin.cp.bean.messagebuilder;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Base builder.
 */
public abstract class BaseBuilder<T> {
  /**
   * The Msg type.
   */
  protected String msgType;
  /**
   * The Agent id.
   */
  protected Integer agentId;
  /**
   * The To user.
   */
  protected String toUser;
  /**
   * The To party.
   */
  protected String toParty;
  /**
   * The To tag.
   */
  protected String toTag;
  /**
   * The Safe.
   */
  protected String safe;

  /**
   * Agent id t.
   *
   * @param agentId the agent id
   * @return the t
   */
  public T agentId(Integer agentId) {
    this.agentId = agentId;
    return (T) this;
  }

  /**
   * To user t.
   *
   * @param toUser the to user
   * @return the t
   */
  public T toUser(String toUser) {
    this.toUser = toUser;
    return (T) this;
  }

  /**
   * To party t.
   *
   * @param toParty the to party
   * @return the t
   */
  public T toParty(String toParty) {
    this.toParty = toParty;
    return (T) this;
  }

  /**
   * To tag t.
   *
   * @param toTag the to tag
   * @return the t
   */
  public T toTag(String toTag) {
    this.toTag = toTag;
    return (T) this;
  }

  /**
   * Safe t.
   *
   * @param safe the safe
   * @return the t
   */
  public T safe(String safe) {
    this.safe = safe;
    return (T) this;
  }

  /**
   * Build wx cp message.
   *
   * @return the wx cp message
   */
  public WxCpMessage build() {
    WxCpMessage m = new WxCpMessage();
    m.setAgentId(this.agentId);
    m.setMsgType(this.msgType);
    m.setToUser(this.toUser);
    m.setToParty(this.toParty);
    m.setToTag(this.toTag);
    m.setSafe(StringUtils.defaultIfBlank(this.safe, WxConsts.KefuMsgSafe.NO));
    return m;
  }

}
