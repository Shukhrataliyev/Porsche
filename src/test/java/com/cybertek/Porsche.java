package com.cybertek;

import static com.cybertek.Utilities.Price;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Porsche {
	static WebDriver driver;
	static double basePrice718; 

	 String equipmentXpath = "//section[@id='s_price']/div[1]/div[2]/div[2]";
	 String deliveryXpath = "//section[@id='s_price']//div[1]/div[3]//div[2]";
	 String baseXpath = "//section[@id='s_price']//div[@class='ccaPrice'][1]";
	 String totalXpath = "//section[@id='s_price']/div[1]/div[4]/div[2]";
	 String miamiXpath = "//li[@id='s_exterieur_x_FJ5']";
	 String wheelXpath = "//li[@id='s_exterieur_x_MXRD']//span[@class='img-element']";
	 String seatXpath = "//div[@id='seats_73']/div[2]/div[1]/div[3]/div";
	 String interiorXpath = "//div[@id='vs_table_IIC_x_PEKH_x_c04_PEKH_x_shorttext']";
	 String colorXpath = "//li[@id=\'s_exterieur_x_FJ5\']";
	 String wheels= "//li[@id=\'s_exterieur_x_MXRD\']";
	 
	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		
		//driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		String url = "https://www.porsche.com/usa/modelstart";
		driver.get(url);
		
		driver.findElement(By.xpath(("//img[@alt='Porsche - 718']"))).click();
		
		basePrice718 = Price(driver.findElement(By.xpath(("//div[@class='m-14-model-price'][1]"))).getText());
		
		driver.findElement(By.xpath("(//a[@class='m-01-link m-14-build'])[1]")).click();
		 
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		    // switch focus of 
		    // driver.close(); //close newly opened window when done with it
	        // driver.switchTo().window(parentHandle); 
		    // switch back to the
	        // original window
		}
	}

	//6.Verify that Base price displayed on the page is same as the price from step 4
	@Test
	public void testCase1_6() {
		double actualBasePrice = Price(driver.findElement(By.xpath((baseXpath))).getText());
		Assert.assertEquals(basePrice718, actualBasePrice);
	}

	//7.Verify that Price for Equipment is 0
	@Test
	public void testCase1_7(){
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		Assert.assertEquals(eqPrice, 0.0);
	}
	
	//8.Verify that total price is the sum of base price + Delivery, Processing and Handling Fee
	@Test
	public void testCase1_8(){
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double delPrice = Price(driver.findElement(By.xpath((deliveryXpath))).getText());
		double actualBasePrice = Price(driver.findElement(By.xpath((baseXpath))).getText());
		double sumPrice = eqPrice+delPrice+actualBasePrice;
		double totalPrice = Price(driver.findElement(By.xpath((totalXpath))).getText());
		Assert.assertEquals(totalPrice, sumPrice);
	}
	
	//9.Select color “Miami Blue”
	@Test
	public void testCase1_9(){
		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click();

	}
	
	//10.Verify that Price for Equipment is Equal to Miami Blue price
	@Test 
	public void testCase2_0(){
		double eqPrice = Price(driver.findElement(By.xpath(equipmentXpath)).getText());
		double miamiPrice = Price(driver.findElement(By.xpath((miamiXpath))).getAttribute("data-price"));
		Assert.assertEquals(eqPrice, miamiPrice);
	}
	
	//11.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
	@Test
	public void testCase2_1(){
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double delPrice = Price(driver.findElement(By.xpath((deliveryXpath))).getText());
		double actualBasePrice = Price(driver.findElement(By.xpath((baseXpath))).getText());
		double sumPrice = eqPrice+delPrice+actualBasePrice;
		double totalPrice = Price(driver.findElement(By.xpath((totalXpath))).getText());
		Assert.assertEquals(totalPrice, sumPrice);
	}
	
	//12.Select 20" Carrera Sport Wheels"
	@Test
	public void testCase2_2(){
		driver.findElement(By.xpath(wheelXpath)).click();
	}
	
	//13.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels
	@Test
	public void testCase2_3(){
		double miamiPrice = Price(driver.findElement(By.xpath((miamiXpath))).getAttribute("data-price"));
		double wheelPrice = Price(driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']")).getAttribute("data-price"));
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double sumPrice = miamiPrice+wheelPrice;
		Assert.assertEquals(eqPrice, sumPrice);
	}
	
	//14.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
	@Test
	public void testCase2_4(){
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double delPrice = Price(driver.findElement(By.xpath((deliveryXpath))).getText());
		double actualBasePrice = Price(driver.findElement(By.xpath((baseXpath))).getText());
		double sumPrice = eqPrice+delPrice+actualBasePrice;
		double totalPrice = Price(driver.findElement(By.xpath((totalXpath))).getText());
		Assert.assertEquals(totalPrice, sumPrice);
	}

	//15.Select seats ‘Power Sport Seats (14-way) with Memory Package’
	@Test
	public void testCase2_5() throws Exception{
		driver.findElement(By.xpath("//*[@id='s_conf_submenu']/div/div")).click();
		driver.findElement(By.xpath("//*[@id='submenu_interieur_x_AI_submenu_x_submenu_parent']/span")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='submenu_interieur_x_AI_submenu_x_submenu_seats']/a")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='s_interieur_x_PP06']")).click();	
	}
	
	
	//16.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package"
	@Test
	public void testCase2_6(){
		double miamiPrice = Price(driver.findElement(By.xpath((miamiXpath))).getAttribute("data-price"));
		double wheelPrice = Price(driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']")).getAttribute("data-price"));
		double seatPrice = Price(driver.findElement(By.xpath((seatXpath))).getText());
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double sumPrice = miamiPrice+wheelPrice+seatPrice;
		Assert.assertEquals(eqPrice, sumPrice);
	}

	//17.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
	@Test
	public void testCase2_7(){
		double actualBasePrice = Price(driver.findElement(By.xpath((baseXpath))).getText());
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double delPrice = Price(driver.findElement(By.xpath((deliveryXpath))).getText());
		double sumPrice = actualBasePrice+eqPrice+delPrice;
		double totalPrice = Price(driver.findElement(By.xpath((totalXpath))).getText());
		Assert.assertEquals(totalPrice, sumPrice);
	}

	//18.Click on Interior Carbon Fiber"
	@Test
	public void testCase2_8() throws Exception{
		driver.findElement(By.xpath("//*[@id='s_conf_submenu']/div/div")).click();
		driver.findElement(By.xpath("//*[@id='submenu_individualization_x_individual_submenu_x_submenu_parent']/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='submenu_individualization_x_individual_submenu_x_IIC']/a")).click();
	}

	//19.Select Interior Trim in Carbon Fiber i.c.w. Standard Interior"
	@Test
	public void testCase2_9() throws Exception{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='vs_table_IIC_x_PEKH_x_c04_PEKH_x_shorttext']")).click();
	}

	//20.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w. Standard Interior"
	@Test
	public void testCase3_0(){
		double miamiPrice = Price(driver.findElement(By.xpath((miamiXpath))).getAttribute("data-price"));
		double wheelPrice = Price(driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']")).getAttribute("data-price"));
		double seatPrice = Price(driver.findElement(By.xpath((seatXpath))).getText());
		double interiorPrice = Price(driver.findElement(By.xpath(("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div"))).getText());
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double sumPrice = miamiPrice+wheelPrice+seatPrice+interiorPrice;
		//System.out.println(eqPrice+" "+sumPrice);
		Assert.assertEquals(eqPrice, sumPrice);
	}
	//21.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
	@Test
	public void testCase3_1(){
		double actualBasePrice = Price(driver.findElement(By.xpath((baseXpath))).getText());
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double delPrice = Price(driver.findElement(By.xpath((deliveryXpath))).getText());
		double sumPrice = actualBasePrice+eqPrice+delPrice;
		double totalPrice = Price(driver.findElement(By.xpath((totalXpath))).getText());
		Assert.assertEquals(totalPrice, sumPrice);
	}
	//22.Click on Performance  23.Select 7-speed Porsche Doppelkupplung (PDK) 	//24.Select Porsche Ceramic Composite Brakes (PCCB)
	@Test
	public void testCase3_2() throws Exception{
		driver.findElement(By.xpath("//*[@id='s_conf_submenu']/div/div")).click();
		Thread.sleep(2000);                    
		driver.findElement(By.xpath("//*[@id='submenu_individualization_x_individual_submenu_x_IMG']/a")).click();
		Thread.sleep(2000);                    
		driver.findElement(By.xpath("//*[@id='vs_table_IMG_x_M250_x_c11_M250']")).click();
		Thread.sleep(2000);                    
		driver.findElement(By.xpath("//*[@id='vs_table_IMG_x_M450_x_c91_M450']")).click();

	}

	//25.Verify that Price for Equipment is the sum of Miami Blue price + 20" Carrera Sport Wheels + Power Sport Seats (14-way) with Memory Package + Interior Trim in Carbon Fiber i.c.w. Standard Interior + 7-speed Porsche Doppelkupplung (PDK) + Porsche Ceramic Composite Brakes (PCCB)"
	@Test
	public void testCase3_5(){
		double speedPrice = Price(driver.findElement(By.xpath(("//div[@id='vs_table_IMG_x_M250']/div[1]/div[2]/div"))).getText());
		double breaksPrice = Price(driver.findElement(By.xpath(("//div[@id='vs_table_IMG_x_M450']/div[1]/div[2]/div"))).getText());
		double miamiPrice = Price(driver.findElement(By.xpath((miamiXpath))).getAttribute("data-price"));
		double wheelPrice = Price(driver.findElement(By.xpath("//li[@id='s_exterieur_x_MXRD']")).getAttribute("data-price"));
		double seatPrice = Price(driver.findElement(By.xpath((seatXpath))).getText());
		double interiorPrice = Price(driver.findElement(By.xpath(("//*[@id=\"vs_table_IIC_x_PEKH\"]/div[1]/div[2]/div"))).getText());
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double sumPrice = miamiPrice+wheelPrice+seatPrice+interiorPrice+speedPrice+breaksPrice;
		//System.out.println(eqPrice+" "+sumPrice);
		Assert.assertEquals(eqPrice, sumPrice);
	}

	//26.Verify that total price is the sum of base price + Price for Equipment + Delivery, Processing and Handling Fee
	@Test
	public void testCase3_6(){
		double actualBasePrice = Price(driver.findElement(By.xpath((baseXpath))).getText());
		double eqPrice = Price(driver.findElement(By.xpath((equipmentXpath))).getText());
		double delPrice = Price(driver.findElement(By.xpath((deliveryXpath))).getText());
		double sumPrice = actualBasePrice+eqPrice+delPrice;
		double totalPrice = Price(driver.findElement(By.xpath((totalXpath))).getText());
		Assert.assertEquals(totalPrice, sumPrice);
	}

	
}

