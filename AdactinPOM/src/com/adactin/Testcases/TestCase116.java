package com.adactin.Testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.adactin.page.BookAHotel.BookAHotel;
import com.adactin.page.BookedItenary.BookedItenaryPage;
import com.adactin.page.base.Page;
import com.adactin.page.bookingconfirmation.BookingConfirmation;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.page.selecthotel.SelectHotel;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase116 extends Page {
	@Test(dataProvider="getSearchData")
	public void CompareBookedWithItenary(Hashtable<String, String> data)
	{
		String checkindate;
		String checkoutdate;
		String Order_no;
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		SearchPage Sp=login.dologin("adactin123", "adactin123");
		//entering data in the Search Page			
		Sp.SelectFromDropDown(data.get("Location"),"Location");
		Sp.SelectFromDropDown(data.get("Hotels"), "Hotels");
		Sp.SelectFromDropDown(data.get("RoomType"), "RoomType");		
		Sp.SelectFromDropDown(data.get("Numberrooms"), "Numberrooms");
		
		checkindate=data.get("CheckInDate");
		checkindate=checkindate.replace("\"", "");
		
		checkoutdate=data.get("CheckOutDate");
		checkoutdate=checkoutdate.replace("\"", "");
				
		Sp.EnterValues("CheckIn_Date",checkindate);		
		Sp.EnterValues("CheckOut_Date",checkoutdate);
		
		Sp.SelectDropdownRooms(data.get("AdultsperRoom"),"AdultsperRoom");
		Sp.SelectDropdownRooms(data.get("ChildrenperRoom"),"ChildrenperRoom");
		
		SelectHotel Sh=Sp.Search("Search");
		BookAHotel Bh=Sh.HotelSelection();
		Bh.EnterPersonnelData();
		BookingConfirmation Bc=Bh.BookNowClick();
		
		String[] Bookdetails=new String[13];
		Bookdetails=Bc.GetDisplayedBookedDetails();
		BookedItenaryPage Bip=Bc.MyItenaryClick();
		
		String OrderNo="Cancel "+Bookdetails[0];
		String[] Itenarydetails=new String[15];
		Itenarydetails=Bip.MyItenarydetails(OrderNo);
		Assert.assertTrue(Bookdetails[0].equals(Itenarydetails[1]), "Order Number does not match:Bookdetails="+Bookdetails[0]+" Itenarydetails[1]="+Itenarydetails[1]);
		
		
	}
	@DataProvider
	public Object[][] getSearchData()
	{
		return TestUtil.getData("TC_104", xls);
	}

	@AfterTest
	public void Teardown()
	{
		//driver.close();
	}
}
