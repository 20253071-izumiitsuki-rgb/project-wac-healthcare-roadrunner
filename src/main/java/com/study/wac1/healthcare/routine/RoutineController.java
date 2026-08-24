package com.study.wac1.healthcare.routine;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class RoutineController {

    private final Map<String, String> shiftData = new ConcurrentHashMap<>();

    @GetMapping("/routine")
    public String showRoutinePage() {
        return "routine/routine";
    }

    public static class RoutineItem {
        public String time;
        public String action;

        public RoutineItem(String time, String action) {
            this.time = time;
            this.action = action;
        }

        public String getTime() {
            return time;
        }

        public String getAction() {
            return action;
        }
    }

    @GetMapping("/api/routine")
    @ResponseBody
    public List<RoutineItem> getRoutine(@RequestParam String shift) {
        List<RoutineItem> list = new ArrayList<>();
        if ("day".equals(shift)) {
            list.add(new RoutineItem("07:30", "朝食 ＆ ビタミンサプリ摂取"));
            list.add(new RoutineItem("09:00", "業務開始"));
            list.add(new RoutineItem("20:00", "入浴（湯船に浸かって疲労回復）"));
            list.add(new RoutineItem("23:00", "就寝（質の良い睡眠へ）"));
        } else if ("night".equals(shift)) {
            list.add(new RoutineItem("12:00", "起床 ＆ 軽めの食事"));
            list.add(new RoutineItem("16:00", "夜勤開始"));
            list.add(new RoutineItem("24:00", "夜食（消化に良いもの）"));
        } else if ("off-morning".equals(shift)) {
            list.add(new RoutineItem("09:00", "夜勤終了・帰宅"));
            list.add(new RoutineItem("10:30", "仮眠（3時間程度にとどめる）"));
            list.add(new RoutineItem("21:30", "早めの就寝で自律神経を整える"));
        } else {
            list.add(new RoutineItem("08:30", "軽い運動やストレッチ"));
            list.add(new RoutineItem("12:30", "栄養バランスの良い食事"));
            list.add(new RoutineItem("22:00", "画面を消して入眠準備"));
        }
        return list;
    }

    @GetMapping("/api/shifts")
    @ResponseBody
    public Map<String, String> getShifts() {
        return shiftData;
    }

    @PostMapping("/api/shifts")
    @ResponseBody
    public void saveShift(@RequestParam String date, @RequestParam String shift) {
        shiftData.put(date, shift);
    }
}
