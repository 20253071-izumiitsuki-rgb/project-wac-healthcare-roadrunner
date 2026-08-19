package com.study.wac1.healthcare.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ログインを行うためのコントローラークラスです。
 *
 * このクラスはSpring MVCフレームワークでリクエストを処理し、ログイン関連処理を担当します。
 *
 * @author 情報太郎
 */
@Controller
public class LoginController {

  @Autowired
  private LoginService loginService;

  /**
   * ログイン画面を表示するためのリクエストハンドラです。
   *
   * @return ログイン画面
   */
  @GetMapping("/login")
  public String getLogin() {
    return "login";
  }

  /**
   * ログイン処理を行います。
   *
   * @param userId   ユーザーID
   * @param password パスワード
   * @param model    モデルオブジェクト
   * @return ログイン成功時はトップ画面、失敗時はログイン画面
   */
  @PostMapping("/login")
  public String login(
      @RequestParam(name = "user_id") String userId,
      @RequestParam(name = "password") String password,
      Model model) {
    // ログイン処理
    boolean result = loginService.login(userId, password);
    if (!result) {
      model.addAttribute("errorMessage", "ユーザーIDまたはパスワードが違います。");
      return "login";
    }

    return "redirect:/";
  }

  /**
   * ログアウト処理を行います。
   *
   * @return ログイン画面
   */
  @GetMapping("/logout")
  public String logout() {
    // ログアウト処理
    loginService.logout();

    return "login";
  }
}
