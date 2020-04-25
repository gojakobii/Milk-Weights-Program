package application.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Stack;

import application.Farm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;


public class FarmTest {
  
  protected Farm farm;
  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
       farm = new Farm();
  }
  
  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {}
  
  @Test
  void farmTest_001_testAdd() {
   try {
     System.out.println("Test Add");
    farm.add("2020", "1", "2");
    farm.add("2020", "2", "3");
    farm.add("2", "3", "3");
    farm.add("12302020", "2", "20");
    ArrayList<Farm.Details> list = farm.getValues("12302020");
    for(int i = 0; i < list.size(); i++)
    {
      System.out.println(list.get(i).getFarmID()+ " " + list.get(i).getMilkWeight());
    }    
    
   }
   catch(Exception e) {
     fail();
   }
  }
  
  /**
   * Tests edit method
   */
  @Test
  void farmTest_002_testEdit() {
    try {
      farm.add("1", "2", "3");
      farm.add("1", "3", "6");
      System.out.println("Test Edit");
      farm.edit("1","2","3",3,"","","4");
      ArrayList<Farm.Details> list = farm.getValues("1");
      for(int i = 0; i < list.size(); i++)
      {
        System.out.println(list.get(i).getFarmID()+ " " + list.get(i).getMilkWeight());
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
   
  
  /**
   * Tests remove method
   */
  @Test
  void farmTest_003_testRemove() {
    try {
      farm.add("1", "2", "3");
      farm.add("1", "5", "8");
      farm.add("1", "3", "6");
      System.out.println("Test Remove");
      farm.remove("1","5","8");
      ArrayList<Farm.Details> list = farm.getValues("1");
      for(int i = 0; i < list.size(); i++)
      {
        System.out.println(list.get(i).getFarmID()+ " " + list.get(i).getMilkWeight());
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
  
  /**
   * Tests remove method
   */
  @Test
  void farmTest_004_testMonthlyMonth() {
    try {
      System.out.println("Monthly");
      farm.add("01032020", "2", "3");
      farm.add("01042020", "5", "8");
      farm.add("01042020", "3", "6");
      ArrayList<Farm.Details> list = farm.monthlyReport("01", "2020");      
      for(int i = 0; i < list.size(); i++)
      {
        System.out.println(list.get(i).getFarmID()+ " " + list.get(i).getMilkWeight()+ " " + list.get(i).getMonth());
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
  
  /**
   * Tests Farm Report method
   */
  @Test
  void farmTest_005_testFarmReport() {
    try {
      System.out.println("Test FarmReport");
      farm.add("01032020", "2", "3");
      farm.add("01042020", "2", "8");
      farm.add("01052020", "2", "7");
      farm.add("02052020", "2", "4");
      farm.add("01312020", "2", "12");
      farm.add("11302020", "2", "420");
      farm.add("12102020", "2", "20");
      ArrayList<Farm.Details> list = farm.farmReport("2", "2020");
      for(int i = 0; i < list.size(); i++)
      {
        System.out.println(list.get(i).getFarmID()+ " " + list.get(i).getMilkWeight()+ " "+list.get(i).getMonth());
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
  
  /**
   * Tests Annual Report method
   */
  @Test
  void farmTest_006_testAnnualReport() {
    try {
      System.out.println("Test AnnualReport");
      farm.add("01032020", "2", "3");
      farm.add("01042020", "2", "8");
      farm.add("01052020", "3", "7");
      farm.add("02052020", "3", "4");
      farm.add("01312020", "5", "12");
      farm.add("11302020", "4", "420");
      farm.add("12102020", "4", "20");
      farm.add("12152020", "10", "22");      
      ArrayList<Farm.Details> list = farm.annualReport("2020");
      for(int i = 0; i < list.size(); i++)
      {
        System.out.println(list.get(i).getFarmID()+ " " + list.get(i).getMilkWeight());
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
  
  /**
   * Tests Annual Report method
   */
  @Test
  void farmTest_006_testDateRangeReport() {
    try {
      System.out.println("Test Date Range");
      farm.add("01032020", "2", "3");
      farm.add("01042020", "2", "8");
      farm.add("01052020", "3", "7");
      farm.add("02052020", "3", "4");      
      farm.add("11302020", "4", "420");
      farm.add("12102020", "4", "20");
      farm.add("01312020", "5", "12");
      farm.add("12152020", "10", "22");      
      ArrayList<Farm.Details> list = farm.dateRange("2020", "01", "01", "12", "01");
      for(int i = 0; i < list.size(); i++)
      {
        System.out.println(list.get(i).getFarmID()+ " " + list.get(i).getMilkWeight());
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
}
