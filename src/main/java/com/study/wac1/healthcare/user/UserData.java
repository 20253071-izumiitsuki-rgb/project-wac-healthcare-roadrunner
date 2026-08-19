package com.study.wac1.healthcare.user;

/**
 * 1件分のユーザー情報を保持します。
 *
 * 各項目のデータ構造については、データベース定義を参照してください。
 *
 * @author 情報太郎
 */
public record UserData(
                /** ユーザーID（メールアドレス）：主キー、必須入力、メールアドレス形式 */
                String userId,

                /** パスワード：必須入力、長さ4から100桁まで、半角英数字のみ */
                String password,

                /** ユーザー名：必須入力 */
                String userName,

                /** 権限：管理 : "ROLE_ADMIN"、上位： "ROLE_TOP"、一般 : "ROLE_GENERAL" */
                String role,

                /** アカウント有効性：有効 : true、無効 : false */
                boolean enabled) {
}
