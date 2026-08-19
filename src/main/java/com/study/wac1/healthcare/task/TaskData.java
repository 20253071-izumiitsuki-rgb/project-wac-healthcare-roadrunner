package com.study.wac1.healthcare.task;

import java.util.Date;

/**
 * 1件分のタスク情報を保持します。
 *
 * 各データ構造については、データベース定義を参照してください。
 *
 * @author 情報太郎
 */
public record TaskData(
                /** タスクID：主キー、SQLにて自動採番 */
                int id,

                /** ユーザーID（メールアドレス）：Userテーブルの主キーと紐づく、ログイン情報から取得 */
                String userId,

                /** 件名：必須入力 */
                String title,

                /** 期限日：必須入力 */
                Date limitday,

                /** 完了フラグ：デフォルト値は、false(未完了) */
                boolean isComplate) {
}
