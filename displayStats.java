import java.text.DecimalFormat;
import java.util.ArrayList;

public class displayStats {
  
  private final static String[] MONTHS_OF_YEAR = {"January", "February", "March", "April", "May",  //Stores month of years as numbers
      "June", "July", "August", "September", "October", "November", "December"};
  
  DecimalFormat df = new DecimalFormat("#.00");
  
  Farm farm;
  public displayStats(Farm farm) {
    this.farm = farm;
  }
  
  /**
   * returns total and percent total of each month for FarmReport as a 2D matrix with 1st row as total
   * @param farmID
   * @param year
   * @return
   * @throws Exception 
   */
  public String[][] farmReportResult(String farmID, String year) throws Exception{    
    ArrayList<Farm.Details> list = farm.farmReport(farmID, year);
    String matrix[][] = new String[list.size() + 1][2];
    int total = 0;
    for(int i = 0; i < matrix.length; i++)
    {
      if(i == 0)
      {
        matrix[i][0] = "Total";        
        for(int j = 0; j < list.size(); j++)
        {
          total += list.get(j).getMilkWeight();
        }
        matrix[0][1] = Integer.toString(total);
        continue;
      }  
      
        matrix[i][0] = MONTHS_OF_YEAR[list.get(i-1).getMonth() - 1];        
        double percent = list.get(i-1).getMilkWeight() / (double)total * 100;
        matrix[i][1] = df.format(percent) + "%";
        
    } 
    
    return matrix;
  }
  
  /**
   * returns total and percent total of each farm for AnnualReport as a 2D matrix with 1st row as total
   * @param farmID
   * @param year
   * @return
   * @throws Exception 
   */
  public String[][] annualReportResult(String year) throws Exception{    
    ArrayList<Farm.Details> list = farm.annualReport(year);
    String matrix[][] = new String[list.size() + 1][2];    
    int total = 0;
    for(int i = 0; i < matrix.length; i++)
    {
      if(i == 0)
      {
        matrix[i][0] = "Total";        
        for(int j = 0; j < list.size(); j++)
        {
          total += list.get(j).getMilkWeight();
        }
        matrix[0][1] = Integer.toString(total);
        continue;
      }
      matrix[i][0] = list.get(i-1).getFarmID();
      double percent = list.get(i-1).getMilkWeight() / (double)total * 100;
      matrix[i][1] = df.format(percent) + "%";
    }
    
    return matrix;
  }
  
  /**
   * returns total and percent total of each farm for Monthly Report as a 2D matrix with 1st row as total
   * @param farmID
   * @param year
   * @return
   * @throws Exception 
   */
  public String[][] monthlyReportResult(String month, String year) throws Exception{    
    ArrayList<Farm.Details> list = farm.monthlyReport(month, year);   
    String matrix[][] = new String[list.size() + 1][2];
    int total = 0;
    for(int i = 0; i < matrix.length; i++)
    {
      if(i == 0)
      {
        matrix[i][0] = "Total";        
        for(int j = 0; j < list.size(); j++)
        {
          total += list.get(j).getMilkWeight();
        }
        matrix[0][1] = Integer.toString(total);
        continue;
      }
      matrix[i][0] = list.get(i-1).getFarmID();
      double percent = list.get(i-1).getMilkWeight() / (double)total * 100;
      matrix[i][1] = df.format(percent) + "%";
    }
    
    return matrix;
  }
  
  /**
   * returns total and percent total of each farm for Date-Range Report as a 2D matrix with 1st row as total
   * @param farmID
   * @param year
   * @return
   * @throws Exception 
   */
  public String[][] dateRangeResult(String year, String month, String day, String endMonth, String endDay) throws Exception{
    ArrayList<Farm.Details> list = farm.dateRange(year, month, day, endMonth, endDay);
    String matrix[][] = new String[list.size() + 1][2];
    int total = 0;
    for(int i = 0; i < matrix.length; i++)
    {
      if(i == 0)
      {
        matrix[i][0] = "Total";        
        for(int j = 0; j < list.size(); j++)
        {
          total += list.get(j).getMilkWeight();
        }
        matrix[0][1] = Integer.toString(total);
        continue;
      }      
      matrix[i][0] = list.get(i-1).getFarmID();      
      double percent = list.get(i-1).getMilkWeight() / (double)total * 100;
      matrix[i][1] = df.format(percent) + "%";
    }
    
    return matrix;
  }

  
  
}
