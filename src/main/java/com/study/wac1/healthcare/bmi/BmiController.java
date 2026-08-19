package com.study.wac1.healthcare.bmi;

import com.study.wac1.healthcare.user.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * BMI計算を行うためのコントローラークラスです。
 *
 * このクラスはSpring MVCフレームワークでリクエストを処理し、BMI計算の画面表示を担当します。
 *
 * 入力チェックを実施し、正しい郵便番号のみ処理します。
 * クライアント側でも入力チェックを実施することを推奨します。
 *
 * @author 情報太郎
 */
@Controller
public class BmiController {

  @Autowired
  private LoginService loginService;

  @Autowired
  private BmiService bmiService;

  /**
   * BMI計算画面を表示するためのリクエストハンドラです。
   *
   * @return BMI計算画面のパス
   */
  @GetMapping("/bmi")
  public String getBmi() {
    // ログインチェック
    if (!loginService.isLogin()) {
      return "login";
    }

    // 画面を返却
    return "bmi/input";
  }

  /**
   * BMIを計算し、結果を表示するためのリクエストハンドラです。
   *
   * @param model  モデルオブジェクト
   * @param height 身長（単位：センチメートル）
   * @param weight 体重（単位：キログラム）
   * @return BMIの計算結果を表示する画面のパス
   */
  @PostMapping("/bmi")
  public String postBmi(
      Model model,
      @RequestParam(name = "cm") String height,
      @RequestParam(name = "kg") String weight) {
    // ログインチェック
    if (!loginService.isLogin()) {
      return "login";
    }

    // 入力値のチェック
    boolean result = bmiService.validate(height, weight);
    if (!result) {
      return "bmi/input";
    }

    // データを取得
    BmiData data = bmiService.execute(height, weight);
    // データをモデルオブジェクトに設定
    model.addAttribute("bmi", data);
    // 画面を返却
    return "bmi/result";
  }
}
