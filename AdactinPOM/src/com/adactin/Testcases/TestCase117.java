package com.adactin.Testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.adactin.page.BookedItenary.BookedItenaryPage;
import com.adactin.page.base.Page;
import com.adactin.page.landingpage.SearchPage;
import com.adactin.pages.login.LoginPage;
import com.adactin.util.TestUtil;

public class TestCase117 extends Page {

@Test(dataProvider="Orderdata")
public void SearchOrderId(Hashtable<String, String> data) throws InterruptedException
{
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	SearchPage Sp=login.dologin("adactin123", "adactin123");
	BookedItenaryPage Bip=Sp.GotoBookedItenary();	
	String[] BeforeSearch=new String[14];
	String[] AfterSearch=new String[14];
	BeforeSearch=Bip.MyItenarydetails(data.get("OrderId"));
	AfterSearch=Bip.SearchOrder(data.get("OrderId"));
	for(int i=0;i<14;i++)
	{
	 System.out.println("Result is :"+ "Values are "+BeforeSearch[i]+","+AfterSearch[i]);
	}

}

@DataProvider
public Object[][] Orderdata()
{
	return TestUtil.getData("TC_117", xls);
}
}
