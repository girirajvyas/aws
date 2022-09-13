package io.github.girirajvyas.aws;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.github.girirajvyas.clashofkings.model.PlatoonCount;

@SpringBootApplication
public class AwsSpringbootApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(AwsSpringbootApplication.class, args);
//	}

	 public static void main(String[] args) {
	    String myclan ="Spearmen#10;Militia#30;FootArcher#20;LightCavalry#1000;HeavyCavalry#120";
	    String opponentClan = "Militia#10;Spearmen#10;FootArcher#1000;LightCavalry#120;CavalryArcher#100";
	    
	    List<PlatoonCount> myPlatoon = getPlatoonWithCount(myclan);
	    List<PlatoonCount> opponentPlatoon = getPlatoonWithCount(opponentClan);
	    
	    for (PlatoonCount my : myPlatoon) {
	      
	      for (PlatoonCount oppenent : opponentPlatoon) {
            
	        
          }
          
        }
	  }
	  
	  private static List<PlatoonCount> getPlatoonWithCount(String input) {
	    List<PlatoonCount> platoonCount = new ArrayList<>();
	    Stream.of(input.split(";")).collect(Collectors.toList()).forEach(p -> {
	      String[] str = p.split("#");
	      platoonCount.add(new PlatoonCount(str[0], str[1]));
	    });; 
	    
	    return platoonCount;
	  }

}
