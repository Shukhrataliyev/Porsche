package com.cybertek;

//import static com.cybertek.Porsche.*;

import org.openqa.selenium.By;
public class Utilities {

	
	public static double Price(String s) {
		double price=0.0; 
		if (s.contains("From")) {
			s=s.substring(6, 16);
			price = Double.parseDouble(s.replace(",", ""));
		}else if(s.length()<=2){
			price=0.0;
		}else{
		price = Double.parseDouble(s.substring(1).replace(",", ""));
		}
		return price;

	}

}
