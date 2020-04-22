package application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Farm {
   private String farmID;
   private Map<String, Integer> data;
   
   public Farm(String farmID) {
      this.farmID = farmID;
      this.data = new HashMap<>(); // TODO Do we want to use the built-in HashMap, or write our own implementation?
   }

   public String getFarmID() {
      return farmID;
   }

   /**
    * Adds a milk weight record to this farm's records.
    */
   public void add(String date, int milkWeight) {
      data.put(date, milkWeight);
   }
   
   /**
    * adds a milk weight record to this farm's records.
    */
   public void edit(String date, int milkWeight) {
   
   }
   
   /**
    * adds a milk weight record to this farm's records.
    */
   public void remove(String date) {
   
   }

   /**
    * Get the recorded milk weight for the given date from this farm's records. Returns null if there wasn't any data
    * for this date.
    */
   public int getRecord(String date) {
      return data.get(date);
   }

   /**
    * Minimum by month. 
    * 
    */
   public List<String> minByMonth(String month){
     //parameters of list could be changed
     return null;
   }
   
   /**
    * Minimum by year. 
    * 
    */
   public List<String> minByYear(String year){
     //parameters of list could be changed
     return null;
   }
   
   /**
    * Maximum by month. 
    * 
    */
   public List<String> maxByMonth(String month){
     //parameters of list could be changed
     return null;
   }
   
   /**
    * Maximum by year. 
    * 
    */
   public List<String> maxByYear(String year){
     //parameters of list could be changed
     return null;
   }
   
   /**
    * Avg by month. 
    * 
    */
   public List<String> avgByMonth(String month){
     //parameters of list could be changed
     return null;
   }
   
   /**
    * Avg by year. 
    * 
    */
   public List<String> avgByYear(String year){
     //parameters of list could be changed
     return null;
   }
   
    
}
