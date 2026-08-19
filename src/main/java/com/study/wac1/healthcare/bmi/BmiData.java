package com.study.wac1.healthcare.bmi;

/**
 * BMI情報を格納するためのエンティティクラスです。
 *
 * このクラスはBMI値、画像のパス、コメントを保持します。
 *
 * @author 情報太郎
 */
public record BmiData(
                /** BMI値(小数点第3位まで) */
                String ans,

                /** 画像のパス */
                String path,

                /** コメント */
                String comment

) {
}
