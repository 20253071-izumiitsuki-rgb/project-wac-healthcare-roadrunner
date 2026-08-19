package com.study.wac1.healthcare.bmi;

import org.springframework.stereotype.Service;

/**
 * BMI計算の業務ロジックを提供するサービスクラスです。
 * このクラスは身長と体重を受け取り、BMI情報を計算して返すビジネスロジックを提供します。
 * 参考サイト：BMIと適正体重 - 高精度計算サイト - Keisan
 * https://keisan.casio.jp/exec/system/1161228732"
 *
 * @author 情報太郎
 */
@Service
public class BmiService {

  /**
   * 指定された身長と体重の文字列を検証します。
   *
   * @param height 身長を表す文字列（センチメートル単位）
   * @param weight 体重を表す文字列（キログラム単位）
   * @return 入力値が適切な範囲内にある場合は true、それ以外の場合は false
   */
  public boolean validate(String height, String weight) {
    try {
      // 身長
      double cm = Double.parseDouble(height);
      // 体重
      double kg = Double.parseDouble(weight);

      // 適正値チェック
      if (cm > 250 || cm < 30) {
        return false;
      }
      if (kg > 200 || kg < 5) {
        return false;
      }
    } catch (NumberFormatException e) {
      // 空文字や数字でない場合
      return false;
    } catch (NullPointerException e) {
      // nullチェック
      return false;
    }

    return true;
  }

  /**
   * 身長と体重を受け取り、BMI情報を算出して返却します。
   *
   * @param height 身長（単位：センチメートル）
   * @param weight 体重（単位：キログラム）
   * @return BMI情報
   */
  public BmiData execute(String height, String weight) {
    // BMI計算
    String ans = calc(height, weight);

    // コメント
    String comment = judge(ans);

    // 画像パス
    String img = img(ans);

    BmiData entity = new BmiData(ans, img, comment);
    return entity;
  }

  /**
   * 身長と体重を受け取り、BMI値を計算して返却します。
   *
   * @param height 身長（単位：センチメートル）
   * @param weight 体重（単位：キログラム）
   * @return BMI値
   */
  private String calc(String height, String weight) {
    // 身長をセンチメートルからメートルへ返還
    double m = Double.parseDouble(height) / 100;
    // BMIを計算
    double bmi = Double.parseDouble(weight) / (Integer.parseInt(height) * m);
    // BMIを小数点第三位まで切り捨てて文字列形式で返す
    String ans = String.format("%.3f", bmi * 100);
    return ans;
  }

  /**
   * BMI値に応じたコメントを返却します。
   *
   * @param ans BMI値
   * @return コメント
   */
  private String judge(String ans) {
    // 文字列型から浮動小数点型へ変換
    double bmi = Double.parseDouble(ans);

    String comment;
    if (bmi < 16) {
      comment = "痩せすぎ";
    } else if (bmi <= 16.99) {
      comment = "痩せ";
    } else if (bmi <= 18.49) {
      comment = "痩せぎみ";
    } else if (bmi <= 24.99) {
      comment = "普通体重";
    } else if (bmi <= 29.99) {
      comment = "前肥満";
    } else if (bmi <= 34.99) {
      comment = "肥満(1度)";
    } else if (bmi <= 39.99) {
      comment = "肥満(2度)";
    } else {
      comment = "肥満(3度)";
    }
    return comment;
  }

  /**
   * BMI値に応じた画像パスを返却します。
   *
   * @param ans BMI値
   * @return 画像パス
   */
  private String img(String ans) {
    // 文字列型から浮動小数点型へ変換
    double bmi = Double.parseDouble(ans);

    String path;
    if (bmi < 18.49) {
      path = "/img/bmi/gari.png";
    } else if (bmi <= 24.99) {
      path = "/img/bmi/normal.png";
    } else {
      path = "/img/bmi/puni.png";
    }
    return path;
  }
}
