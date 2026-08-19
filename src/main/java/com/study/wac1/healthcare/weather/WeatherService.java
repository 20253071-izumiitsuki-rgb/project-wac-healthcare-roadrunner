package com.study.wac1.healthcare.weather;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 天気予報の業務ロジックを実現するクラスです。
 *
 * 本機能は、天気予報API（livedoor 天気互換）を内部で呼び出して結果を表示します。
 * 仕様については、下記のドキュメントを参照してください。
 * https://weather.tsukumijima.net/
 *
 * @author 情報太郎
 *
 */
@Service
public class WeatherService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  private List<String> citycodes = Arrays.asList(
      "011000",
      "012010",
      "012020",
      "013010",
      "013020",
      "013030",
      "014010",
      "014020",
      "014030",
      "015010",
      "015020",
      "016010",
      "016020",
      "016030",
      "017010",
      "017020");

  /**
   * 都市コードのバリデーションを行います。
   *
   * @param citycode 都市コード
   * @return バリデーションが成功した場合は true、それ以外の場合は false
   */
  public boolean validate(String citycode) {
    // citycodesリスト内にcitycodeが存在しないかどうかをチェックし、その結果を返す
    // 存在しない場合は true（バリデーション成功）、存在する場合は false（バリデーション失敗）
    return !citycodes.stream().anyMatch(v -> v.equals(citycode));
  }

  /**
   * 都市コード検索を実行し、結果を取得します。
   *
   * 検索に失敗した場合は、エラーメッセージのみ設定されます。
   *
   * @param cityCode 都市コード
   * @return 天気予報結果を格納したWeatherDataオブジェクト
   * @throws RestClientException
   * @throws JsonProcessingException
   */
  public WeatherData execute(String cityCode) throws RestClientException, JsonProcessingException {
    /* エンドポイント */
    String URL = "https://weather.tsukumijima.net/api/forecast?city={cityCode}";

    // APIを呼び出して結果を取得
    String jsonResponse = restTemplate.getForObject(URL, String.class, cityCode);

    // JSONレスポンスをデータオブジェクトに変換
    WeatherData weatherData = objectMapper.readValue(jsonResponse, WeatherData.class);

    return weatherData;
  }
}
