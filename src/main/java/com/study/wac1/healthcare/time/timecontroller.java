package com.study.wac1.healthcare.time;

import com.example.healthcare.form.Record;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class timecontroller {

  private List<timeRecord> recordList = new ArrayList<>();

  @GetMapping("/time")
  public String showTimePage(Model model) {
    // HTMLへこれまでの記録リストを渡す
    model.addAttribute("records", recordList);
    return "time";
  }

  @PostMapping("/time/add")
  public String addRecord(@RequestParam("date") String date,
      @RequestParam("interval") Integer interval) {
    timeRecord newRecord = new timeRecord();
    newRecord.setDate(date);
    newRecord.setInterval(interval);
    recordList.add(newRecord);

    // 登録後、GETの /time へリダイレクトする
    return "redirect:/time";
  }
}
