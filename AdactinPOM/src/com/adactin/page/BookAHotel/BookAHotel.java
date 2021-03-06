package com.adactin.page.BookAHotel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.adactin.page.bookingconfirmation.BookingConfirmation;

public class BookAHotel {
	WebDriver driver=null;
	
	public BookAHotel(WebDriver dr)
	{
		driver=dr;
	}
	
	public String[] CalacuateTotalPriceWithGST()
	{
		String[] PriceRes=new String[3];
		
		String Rooms =driver.findElement(By.id("room_num_dis")).getAttribute("value");
		int nRooms=Integer.parseInt(Rooms.replaceAll("[\\D]", ""));
		
		String pricepernight=driver.findElement(By.id("price_night_dis")).getAttribute("value");
		int nPrice=Integer.parseInt(pricepernight.replaceAll("[\\D]", ""));
		
		String Nightno=driver.findElement(By.id("total_days_dis")).getAttribute("value");
		int nNights=Integer.parseInt(Nightno.replaceAll("[\\D]", ""));
		
		float nGST=(float) (nRooms*nPrice*nNights*0.1);
		float TotalValue=(nRooms*nPrice*nNights)+nGST;
		
		String Expected_FinalPrice=String.valueOf(TotalValue);
				
		String FinalPrice=driver.findElement(By.id("final_price_dis")).getAttribute("value");		
		String ActualFinalPrice=FinalPrice.replaceAll("[\\D]", "");
		
		PriceRes[0]=FinalPrice;
		PriceRes[1]="AUD $"+ Expected_FinalPrice;
		if(ActualFinalPrice.equals(Expected_FinalPrice))
			PriceRes[2]="true";
		else
			PriceRes[2]="false";
		
		return PriceRes;
	}
	
	public String[] CalacuateTotalPrice()
	{
		String[] PriceRes=new String[3];
		
		String Rooms =driver.findElement(By.id("room_num_dis")).getAttribute("value");
		int nRooms=Integer.parseInt(Rooms.replaceAll("[\\D]", ""));
		
		String pricepernight=driver.findElement(By.id("price_night_dis")).getAttribute("value");
		int nPrice=Integer.parseInt(pricepernight.replaceAll("[\\D]", ""));
		
		String Nightno=driver.findElement(By.id("total_days_dis")).getAttribute("value");
		int nNights=Integer.parseInt(Nightno.replaceAll("[\\D]", ""));		
		
		String Expected_FinalPrice=String.valueOf(nRooms*nPrice*nNights);
				
		String FinalPrice=driver.findElement(By.id("total_price")).getAttribute("value");		
		String ActualFinalPrice=FinalPrice.replaceAll("[\\D]", "");
		
		PriceRes[0]=FinalPrice;
		PriceRes[1]="AUD $"+ Expected_FinalPrice;
		if(ActualFinalPrice.equals(Expected_FinalPrice))
			PriceRes[2]="true";
		else
			PriceRes[2]="false";
		
		return PriceRes;
	}
	
	public String[] GetData()
	{
		String[] sData=new String[10];
		sData[1]=driver.findElement(By.id("hotel_name_dis")).getAttribute("value");
		sData[2]=driver.findElement(By.id("location_dis")).getAttribute("value");
		sData[3]=driver.findElement(By.id("room_type_dis")).getAttribute("value");
		sData[4]=driver.findElement(By.id("room_num_dis")).getAttribute("value");
		sData[5]=driver.findElement(By.id("total_days_dis")).getAttribute("value");
		sData[6]=driver.findElement(By.id("price_night_dis")).getAttribute("value");
		sData[7]=driver.findElement(By.id("total_price_dis")).getAttribute("value");
		return sData;		
	}
	
	public void EnterPersonnelData()
	{
		driver.findElement(By.id("first_name")).sendKeys("Sony");
		driver.findElement(By.id("last_name")).sendKeys("Joseph");
		driver.findElement(By.id("address")).sendKeys("Unit 53,Hospital Road, Parramatta");
		driver.findElement(By.id("cc_num")).sendKeys("1234567812345678");	
		
		Select sel_cardtype=new Select(driver.findElement(By.id("cc_type")));
		sel_cardtype.selectByIndex(2);
		
		Select sel_expmonth=new Select(driver.findElement(By.id("cc_exp_month")));
		sel_expmonth.selectByIndex(2);
		Select sel_expyr=new Select(driver.findElement(By.id("cc_exp_year")));
		
		sel_expyr.selectByIndex(9);
		driver.findElement(By.id("cc_cvv")).sendKeys("123");		
	}
	
	public BookingConfirmation BookNowClick()
	{
		driver.findElement(By.id("book_now")).click();
		return PageFactory.initElements(driver, BookingConfirmation.class);
	}
}
