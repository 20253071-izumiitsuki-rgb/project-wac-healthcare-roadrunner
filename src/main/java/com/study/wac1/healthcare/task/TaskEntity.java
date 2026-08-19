package com.study.wac1.healthcare.task;

import java.util.ArrayList;
import java.util.List;

/**
 * 複数件のタスク情報を保持します。
 *
 * DBからタスク情報が取得できない場合は、リストが空となります。
 *
 * @author 情報太郎
 */
public record TaskEntity(
    /** タスク情報のリスト */
    List<TaskData> taskList,
    /** エラーメッセージ(表示用) */
    String errorMessage) {

  public TaskEntity() {
    this(new ArrayList<>(), "");
  }
}
