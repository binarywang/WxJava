package me.chanjar.weixin.common.session;

/**
 * @author Daniel Qian
 */
public interface InternalSessionManager {

  /**
   * Return the active Session, associated with this Manager, with the
   * specified session id (if any); otherwise return {@code null}.
   *
   * @param id The session id for the session to be returned
   * @return the session or null
   * @throws IllegalStateException if a new session cannot be
   *                               instantiated for any reason
   */
  InternalSession findSession(String id);

  /**
   * Construct and return a new session object, based on the default
   * settings specified by this Manager's properties.  The session
   * id specified will be used as the session id.
   * If a new session cannot be created for any reason, return
   * <code>null</code>.
   *
   * @param sessionId The session id which should be used to create the
   *                  new session; if <code>null</code>, a new session id will be
   *                  generated
   * @throws IllegalStateException if a new session cannot be
   *                               instantiated for any reason
   */
  InternalSession createSession(String sessionId);

  /**
   * Remove this Session from the active Sessions for this Manager.
   *
   * @param session Session to be removed
   */
  void remove(InternalSession session);

  /**
   * Remove this Session from the active Sessions for this Manager.
   *
   * @param session Session to be removed
   * @param update  Should the expiration statistics be updated
   */
  void remove(InternalSession session, boolean update);

  /**
   * Add this Session to the set of active Sessions for this Manager.
   *
   * @param session Session to be added
   */
  void add(InternalSession session);


  /**
   * Returns the number of active sessions
   *
   * @return number of sessions active
   */
  int getActiveSessions();

  /**
   * Get a session from the recycled ones or create a new empty one.
   * The PersistentManager manager does not need to create session data
   * because it reads it from the Store.
   */
  InternalSession createEmptySession();

  InternalSession[] findSessions();

  /**
   * Implements the Manager interface, direct call to processExpires
   */
  void backgroundProcess();

  /**
   * Set the default maximum inactive interval (in seconds)
   * for Sessions created by this Manager.
   *
   * @param interval The new default value
   */
  void setMaxInactiveInterval(int interval);

  /**
   * <pre>
   * Set the manager checks frequency.
   * 设置每尝试多少次清理过期session，才真的会执行一次清理动作
   * 要和{@link #setBackgroundProcessorDelay(int)}联合起来看
   * 如果把这个数字设置为6（默认），那么就是说manager要等待 6 * backgroundProcessorDay的时间才会清理过期session
   * </pre>
   *
   * @param processExpiresFrequency the new manager checks frequency
   */
  void setProcessExpiresFrequency(int processExpiresFrequency);

  /**
   * <pre>
   * Set the manager background processor delay
   * 设置manager sleep几秒，尝试执行一次background操作（清理过期session）
   * </pre>
   *
   * @param backgroundProcessorDelay
   */
  void setBackgroundProcessorDelay(int backgroundProcessorDelay);


  /**
   * Set the maximum number of active Sessions allowed, or -1 for
   * no limit.
   * 设置最大活跃session数，默认无限
   *
   * @param max The new maximum number of sessions
   */
  void setMaxActiveSessions(int max);

}
