package com.study.wac1.healthcare.user;

import java.util.ArrayList;
import java.util.List;

/**
 * 複数件のユーザー情報を保持します。
 *
 * DBからユーザー情報が取得できない場合は、リストが空となります。
 *
 * @author 情報太郎
 */
public record UserEntity(
    /** ユーザー情報のリスト */
    List<UserData> userlist,

    /** エラーメッセージ(表示用) */
    String errorMessage) {

  public UserEntity() {
    this(new ArrayList<>(), "");
  }
}
