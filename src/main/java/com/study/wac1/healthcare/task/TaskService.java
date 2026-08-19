package com.study.wac1.healthcare.task;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * タスク管理の業務ロジッククラスです。
 *
 * タスク関連の操作を提供します。
 *
 * @author 情報太郎
 */
@Transactional
@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  /**
   * 入力チェックを行います。
   *
   * @param comment  コメント
   * @param limitday 期限日
   * @return 入力チェック結果
   */
  boolean validate(String comment, String limitday) {
    // nullチェック、必須チェック、50文字超過チェック
    if (comment == null || comment.isBlank() || comment.length() > 50) {
      return false;
    }

    // nullチェック、必須チェック
    if (limitday == null || limitday.isBlank()) {
      return false;
    }

    // 日付形式チェック(SimpleDateFomatの変換可否を利用)
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      format.parse(limitday);
    } catch (ParseException e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * 入力チェックを行います。
   *
   * @param id タスクID
   * @return 入力チェック結果
   */
  boolean validate(String id) {
    // 数値チェック(1桁～Intの最大値)
    Pattern p = Pattern.compile("^\\d{1,9}$");
    if (id.isBlank()) {
      return false;
    } else if (!p.matcher(id).find()) {
      return false;
    }
    return true;
  }

  /**
   * ユーザーIDに合致するタスク一覧を取得します。
   *
   * DBエラーが発生した場合は、空のタスク一覧を設定して呼び出し元へ返却します。
   *
   * @param userId ユーザーID
   * @return タスク一覧
   */
  public TaskEntity selectAll(String userId) {
    TaskEntity taskEntity = taskRepository.findAll(userId);
    return taskEntity;
  }

  /**
   * タスクを保存します。
   *
   * DBエラーが発生した場合は、呼び出し元に失敗の通知を行います。
   *
   * @param userId   ユーザーID
   * @param title    タイトル
   * @param limitday 期限日
   * @return 成功可否
   */
  public boolean insert(String userId, String title, String limitday) {
    // Date型へ変換
    Date limitdayDate = null;
    try {
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      limitdayDate = new Date(format.parse(limitday).getTime());
    } catch (ParseException e) {
      e.printStackTrace();
      // 何もしない（入力チェック済みのため、変換エラーは起こり得ない）
    }

    // TaskData型へ詰め替える
    TaskData taskData = new TaskData(
        0, // IDはDBで自動採番
        userId,
        title,
        limitdayDate,
        false // 完了フラグはデフォルトでfalse
    );

    try {
      taskRepository.save(taskData);
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * タスクを削除します。
   *
   * DBエラーが発生した場合は、呼び出し元に失敗の通知を行います。
   *
   * @param id タスクID
   * @return 成功可否
   */
  public boolean delete(String id) {
    int i = Integer.parseInt(id);
    try {
      taskRepository.delete(i);
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * タスクを完了状態にします。
   *
   * DBエラーが発生した場合は、呼び出し元に失敗の通知を行います。
   *
   * @param id タスクID
   * @return 成功可否
   */
  public boolean complete(String id) {
    int i = Integer.parseInt(id);
    try {
      taskRepository.update(i);
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

}
