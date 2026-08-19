package com.study.wac1.healthcare.weather;

import com.study.wac1.healthcare.user.LoginService;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;

/**
 * 天気予報検索を行うためのコントローラークラスです。
 *
 * このクラスはSpring MVCフレームワークでリクエストを処理し、天気予報検索機能の画面表示を担当します。
 *
 * 入力チェックを実施し、正しい市区町村コードのみ処理します。
 * クライアント側でも入力チェックを実施することを推奨します。
 *
 * @author 情報太郎
 *
 */
@Controller
public class WeatherController {

  @Autowired
  private LoginService loginService;

  @Autowired
  private WeatherService weatherService;

  /**
   * 天気予報検索画面を表示します。
   *
   * @return 天気予報検索画面へのパス
   */
  @GetMapping("/weather")
  public String getWeather() {
    // ログインチェック
    if (!loginService.isLogin()) {
      return "login";
    }

    return "weather/input";
  }

  /**
   * 都市コードをもとに該当地域の天気予報を検索し、結果を表示するためのリクエストハンドラです。
   *
   * @param citycode 都市コードを格納
   * @param model    Viewに値を渡すオブジェクト
   * @return 天気予報検索結果画面へのパス
   */
  @PostMapping("/weather")
  public String postWeather(@RequestParam(name = "citycode") String citycode, Model model)
      throws JsonProcessingException {
    // ログインチェック
    if (!loginService.isLogin()) {
      return "login";
    }

    // 入力チェック
    boolean result = weatherService.validate(citycode);
    if (result) {
      model.addAttribute("error", "都市コードが正しくありません。");
      return "weather/input";
    }

    // 結果取得
    try {
      WeatherData weatherData = weatherService.execute(citycode);
      model.addAttribute("weatherData", weatherData);
      return "weather/result";

    } catch (RestClientException | JsonProcessingException e) {
      e.printStackTrace();
      model.addAttribute("errorMessage", "天気予報の取得に失敗しました。");
      return "weather/input";
    }
  }
}
