package com.study.wac1.healthcare.time;

import java.util.ArrayList;
import java.util.List;

public class timeManeger {
  private List<time> records = new ArrayList<>();

  public void addRecord(String date, int interval) {
    records.add(new time(date, interval));
  }

  public List<time> getRecords() {
    return records;
  }

  public int getTotalTime() {
    int total = 0;
    for (time e : records) {
      total += e.getInterval();
    }
    return total;
  }
}
