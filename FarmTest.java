package application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class FarmTest {
  
  protected Farm farm;
  protected displayStats stat;
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
    farm.add("2020", "Farm 1", "2");
    farm.add("2020", "Farm 2", "3");
    farm.add("2", "Farm 3", "3");
    farm.add("12302020", "Farm 2", "20");
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
      farm.add("1", "Farm 2", "3");
      farm.add("1", "Farm 3", "6");
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
      farm.add("1", "Farm 2", "3");
      farm.add("1", "Farm 5", "8");
      farm.add("1", "Farm 3", "6");
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
      farm.add("01032020", "Farm 2", "3");
      farm.add("01042020", "Farm 5", "8");
      farm.add("01042020", "Farm 3", "6");
      ArrayList<Farm.Details> list = farm.monthlyReport("01", "2020");      
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
   * Tests Farm Report method
   */
  @Test
  void farmTest_005_testFarmReport() {
    try {
      System.out.println("Test FarmReport");
      farm.add("01032020", "Farm 2", "3");
      farm.add("01042020", "Farm 2", "8");
      farm.add("01052020", "Farm 2", "7");
      farm.add("02052020", "Farm 2", "4");
      farm.add("01312020", "Farm 2", "12");
      farm.add("11302020", "Farm 2", "420");
      farm.add("12102020", "Farm 2", "20");
      ArrayList<Farm.Details> list = farm.farmReport("Farm 2", "2020");
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
      farm.add("01032020", "Farm 2", "3");
      farm.add("01042020", "Farm 2", "8");
      farm.add("01052020", "Farm 3", "7");
      farm.add("02052020", "Farm 3", "4");
      farm.add("01312020", "Farm 5", "12");
      farm.add("11302020", "Farm 4", "420");
      farm.add("12102020", "Farm 4", "20");
      farm.add("12152020", "Farm 10", "22");      
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
   * Tests Date Range Report method
   */
  @Test
  void farmTest_006_testDateRangeReport() {
    try {
      System.out.println("Test Date Range");
      farm.add("01032020", "Farm 2", "3");
      farm.add("01042020", "Farm 2", "8");
      farm.add("01052020", "Farm 3", "7");
      farm.add("02052020", "Farm 3", "4");      
      farm.add("11302020", "Farm 4", "420");
      farm.add("12102020", "Farm 4", "20");
      farm.add("01312020", "Farm 5", "12");
      farm.add("12152020", "Farm 10", "22");      
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
  
  /**
   * Tests Farm dispStat Report method
   */
  @Test
  void farmTest_007_FarmReportDispStat() {
    try {      
      System.out.println("Test Farm dispStat");
      farm.add("01032020", "Farm 2", "3");
      farm.add("01042020", "Farm 2", "8");
      farm.add("01052020", "Farm 2", "7");
      farm.add("02052020", "Farm 2", "4");
      farm.add("01312020", "Farm 2", "12");
      farm.add("11302020", "Farm 2", "420");
      farm.add("12102020", "Farm 2", "20");
      stat = new displayStats(farm);
      String [][] matrix = stat.farmReportResult("Farm 2", "2020");
      for(int i = 0; i < matrix.length; i++)
      {
        for(int j = 0; j < matrix[0].length; j++)
          {
            System.out.print(matrix[i][j] + " ");
          }
        System.out.println();
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
  
  /**
   * Tests Monthly dispStat Report method
   */
  @Test
  void farmTest_008_MonthlyReportDispStat() {
    try {      
      System.out.println("Test Monthly dispStat");      
      farm.add("01032020", "Farm 2", "3");
      farm.add("01042020", "Farm 5", "8");
      farm.add("01042020", "Farm 3", "6");
      stat = new displayStats(farm);
      String [][] matrix = stat.monthlyReportResult("01", "2020");
      for(int i = 0; i < matrix.length; i++)
      {
        for(int j = 0; j < matrix[0].length; j++)
          {
            System.out.print(matrix[i][j] + " ");
          }
        System.out.println();
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
  
  /**
   * Tests Monthly dispStat Report method
   */
  @Test
  void farmTest_008_DateRangeReportDispStat() {
    try {      
      System.out.println("Test dispStat DateRange");
      farm.add("01032020", "Farm 2", "3");
      farm.add("01042020", "Farm 2", "8");
      farm.add("01052020", "Farm 3", "7");
      farm.add("02052020", "Farm 3", "4");      
      farm.add("11302020", "Farm 4", "420");
      farm.add("12102020", "Farm 4", "20");
      farm.add("01312020", "Farm 5", "12");
      farm.add("12152020", "Farm 10", "22");     
      stat = new displayStats(farm);
      String [][] matrix = stat.dateRangeResult("2020", "01", "01", "12", "01");
      for(int i = 0; i < matrix.length; i++)
      {
        for(int j = 0; j < matrix[0].length; j++)
          {
            System.out.print(matrix[i][j] + " ");
          }
        System.out.println();
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
  
  /**
   * Tests Monthly dispStat Report method
   */
  @Test
  void farmTest_009_AnnualReportDispStat() {
    try {      
      System.out.println("Test dispStat AnnualReport");
      farm.add("01032020", "Farm 2", "3");
      farm.add("01042020", "Farm 2", "8");
      farm.add("01052020", "Farm 3", "7");
      farm.add("02052020", "Farm 3", "4");
      farm.add("01312020", "Farm 5", "12");
      farm.add("11302020", "Farm 4", "420");
      farm.add("12102020", "Farm 4", "20");
      farm.add("12152020", "Farm 10", "22");       
      stat = new displayStats(farm);
      String [][] matrix = stat.annualReportResult("2020");
      for(int i = 0; i < matrix.length; i++)
      {
        for(int j = 0; j < matrix[0].length; j++)
          {
            System.out.print(matrix[i][j] + " ");
          }
        System.out.println();
      }
    }
    catch(Exception e) {
      e.printStackTrace();
      fail();
    }
  }
}
