package com.study.wac1.healthcare;

import com.study.wac1.healthcare.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ダッシュボード画面を制御する
 */
@Controller
public class HealthcareController {

  /** ログインチェック用サービス */
  @Autowired
  private LoginService loginService;

  /**
   * ダッシュボード画面を表示する
   *
   * @return ダッシュボード画面
   */
  @GetMapping("/")
  public String index() {
    // ログインチェック
    if (!loginService.isLogin()) {
      return "login";
    }

    return "index";
  }
}
