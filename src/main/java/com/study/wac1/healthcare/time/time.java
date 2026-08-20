package com.study.wac1.healthcare.time;

public class time {
  private String date;
  private int interval;

  public time(String date, int interval) {
    this.date = date;
    this.interval = interval;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getInterval() {
    return interval;
  }

  public void setInterval(int interval) {
    this.interval = interval;
  }
}
