package com.study.wac1.healthcare.weather;

import java.util.List;

/**
 * 天気予報情報を保持します。
 *
 * 各項目のデータ仕様については、APIの仕様を参照してください。
 * 予報日は今日/明日/明後日の3つが配列で取得できるため、リスト構造となっています。
 *
 * @author 情報太郎
 *
 */
public record WeatherData(
    /** タイトル */
    String title,

    /** 説明 */
    Description description,

    /** 予報 */
    List<Forecast> forecasts) {

  public record Description(
      /** テキスト */
      String bodyText) {
  }

  public record Forecast(
      /** 予報日 */
      String date,

      /** 予報日ラベル */
      String dateLabel,

      /** 天気 */
      String telop,

      /** 天気画像 */
      Image image) {

    public record Image(
        /** URL */
        String url) {
    }
  }

}
