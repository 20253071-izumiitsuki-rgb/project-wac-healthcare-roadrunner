package com.study.wac1.healthcare.user;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ログイン管理の業務ロジッククラスです。
 *
 * ログイン関連の操作を提供します。
 *
 * @author 情報太郎
 */
@Transactional
@Service
public class LoginService {

  /** セッションに格納するユーザーデータのキー */
  private final String SESSION_USER_DATA_KEY = "userData";

  /** 管理者権限判定用文字列 */
  private final String ROLE_ADMIN_KEY = "ROLE_ADMIN";

  /** セッション情報 */
  @Autowired
  HttpSession session;

  @Autowired
  private UserRepository userRepository;

  /**
   * ログイン処理を行います。
   *
   * @param userId   ユーザーID
   * @param password パスワード
   * @return ログイン成功時はtrue、失敗時はfalse
   */
  public boolean login(String userId, String password) {

    UserData userData = userRepository.login(userId, password);
    if (userData == null) {
      // ユーザーデータが取得できなかった場合はログイン失敗
      return false;
    }

    // ログイン成功時はセッションにユーザーデータを格納
    session.setAttribute(SESSION_USER_DATA_KEY, userData);

    return true;
  }

  /**
   * ログアウト処理を行います。
   */
  public void logout() {
    // セッション情報を破棄
    session.invalidate();
  }

  /**
   * ログインチェックを行います。
   *
   * @return ログイン中の場合はtrue、未ログインの場合はfalse
   */
  public boolean isLogin() {
    // ログインチェック
    UserData userData = (UserData) session.getAttribute(SESSION_USER_DATA_KEY);
    if (userData == null) {
      return false;
    }

    return true;
  }

  /**
   * 管理者権限を持っているかチェックします。
   *
   * @return 管理者権限を持っている場合はtrue、持っていない場合はfalse
   */
  public boolean isAdmin() {
    UserData userData = (UserData) session.getAttribute(SESSION_USER_DATA_KEY);

    if (userData == null) {
      return false;
    }

    if (userData.role().equals(ROLE_ADMIN_KEY)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * ログイン中ユーザーのユーザーIDを取得します。
   *
   * @return ログイン中ユーザーのユーザーID
   */
  public String getLoginUserId() {
    UserData userData = (UserData) session.getAttribute(SESSION_USER_DATA_KEY);

    if (userData == null) {
      return "Unknown User(セッション格納無し)";
    }

    return userData.userId();
  }
}
