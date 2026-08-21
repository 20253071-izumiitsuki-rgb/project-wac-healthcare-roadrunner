package com.study.wac1.healthcare.routine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@Controller // 画面とデータの両方を扱えるように変更しました
public class RoutineController {

    // 1. 画面（templates/routine/routine.html）を表示する処理
    @GetMapping("/routine")
    public String showRoutinePage() {
        return "routine/routine";
    }

    // 時間と行動をセットにするクラス
    public static class RoutineItem {
        private String time;
        private String action;

        public RoutineItem(String time, String action) {
            this.time = time;
            this.action = action;
        }
        public String getTime() { return time; }
        public String getAction() { return action; }
    }

    // 2. JavaScriptにシフトごとのスケジュールデータを返す処理
    @GetMapping("/api/routine")
    @ResponseBody // データをJSON形式で返すための設定です
    public List<RoutineItem> getRoutine(@RequestParam String shift) {
        List<RoutineItem> list = new ArrayList<>();

        if ("day".equals(shift)) {
            list.add(new RoutineItem("07:30", "🍳 朝食 ＆ ビタミンサプリ摂取"));
            list.add(new RoutineItem("09:00", "💼 業務開始"));
            list.add(new RoutineItem("20:00", "🛁 入浴（湯船に浸かって疲労回復）"));
            list.add(new RoutineItem("23:00", "🛌 就寝（質の良い睡眠へ）"));
        } else if ("night".equals(shift)) {
            list.add(new RoutineItem("12:00", "🥞 起床 ＆ 軽めの食事"));
            list.add(new RoutineItem("16:00", "🌙 夜勤開始"));
            list.add(new RoutineItem("24:00", "🍌 夜食（消化に良いもの）"));
        } else if ("off-morning".equals(shift)) {
            list.add(new RoutineItem("09:00", "🚗 夜勤終了・帰宅"));
            list.add(new RoutineItem("10:30", "💤 仮眠（3時間程度にとどめる）"));
            list.add(new RoutineItem("21:30", "🛌 早めの就寝で自律神経を整える"));
        } else {
            list.add(new RoutineItem("08:30", "🏃‍♂️ 軽い運動やストレッチ"));
            list.add(new RoutineItem("12:30", "🥩 栄養バランスの良い食事"));
            list.add(new RoutineItem("22:00", "📱 画面を消して入眠準備"));
        }
        return list;
    }
}
