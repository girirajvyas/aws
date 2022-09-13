package io.github.girirajvyas.clashofkings.model;

public class PlatoonCount {
  private int count;
  private Platoon platoon;

  
  public PlatoonCount(String platoon, String count) {
    this.platoon = Platoon.getValue(platoon);
    this.count = Integer.valueOf(count);
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public Platoon getPlatoon() {
    return platoon;
  }

  public void setPlatoon(Platoon platoon) {
    this.platoon = platoon;
  }

}
