package io.github.girirajvyas.clashofkings.model;

import java.util.Arrays;
import java.util.List;

public enum Platoon {
  MILITIA(Arrays.asList("SPEARMEN", "LIGHTCAVALRY")),
  SPEARMEN(Arrays.asList("LIGHTCAVALRY", "HEAVYCAVALRY")),
  
  LIGHTCAVALRY(Arrays.asList("FOOTARCHER", "CAVALRYARCHER")),
  HEAVYCAVALRY(Arrays.asList("MILITIA", "FOOTARCHER", "LIGHTCAVALRY")),
  
  FOOTARCHER(Arrays.asList("MILITIA","CAVALRYARCHER")),
  CAVALRYARCHER(Arrays.asList("SPEARMEN", "HEAVYCAVALRY"));
  
  private List<String> advantageOverPlatoon;
  private int advantageMultiplier=2;

  private Platoon(List<String> advantageOverPlatoon) {
    this.advantageOverPlatoon = advantageOverPlatoon;
  }

  public List<String> getAdvantageOverPlatoon() {
    return advantageOverPlatoon;
  }

  public Integer getAdvantageCount(String p, Integer count) {
    for(Platoon platoon : Platoon.values()) {
      if (platoon.getAdvantageOverPlatoon().contains(Platoon.getValueString(p))) {
        count*=advantageMultiplier;
        break;
      }
    }
    
    return count;
  }
  
  public static Platoon getValue(String s) {
    return Platoon.valueOf(s.toUpperCase());
  }
  
  public static String getValueString(String s) {
    return Platoon.valueOf(s.toUpperCase()).toString();
  }
}
