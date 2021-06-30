/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.projectfinalprep;

/**
 *
 * @author lenovo
 */

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.commons.csv.CSVFormat;
import smile.data.DataFrame;
import smile.data.Tuple;
import smile.io.Read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
public class Wazzaf_DAO {
    
        List<Wazzaf_Data> titlesAndcompany;
        Map<String , List<Wazzaf_Data>> CompanyandTitles = new HashMap<>();
        
        Map<String , Long> CompanyandTitlesNumber = new HashMap<>();
        
        Map<String , Long> TitlesNumber = new HashMap<>();


    public Wazzaf_DAO() {
        titlesAndcompany = new ArrayList<>();
    }
        
        
        
        public DataFrame readCSV(String path) {
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader ();
        DataFrame df = null;
        try {
            df = Read.csv (path, format);
            System.out.println(df.summary ());
            df = df.select ("Title", "Company", "Location");
            df=df.omitNullRows();
           
            
            System.out.println(df);
        } catch (IOException | URISyntaxException e) {
        }
        return df;
    }
    
        public  List<Wazzaf_Data> readDatatoListFromCSV(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String record;
			String[] recordLst;
			
			do {
				record = br.readLine();
				if(record != null) {
					recordLst = record.split(",");
					Wazzaf_Data p = new Wazzaf_Data((recordLst[0]),recordLst[1],recordLst[2].trim());
					titlesAndcompany.add(p);
				}			
			}while(record != null);
			br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return titlesAndcompany;
	}
        
        // Method to return Map of each company and jobs belong to it
        
        public Map<String , List<Wazzaf_Data>> MapOfCompanieswithJobs (DataFrame d , List<Wazzaf_Data> titlesAndcompany) {
            String[] Companies = d.stringVector ("Company").distinct ().toArray (new String[]{});
            for (String company : Companies) 
        {
            
            List <Wazzaf_Data> data = titlesAndcompany.stream().filter(c ->c.getCompany().equals(company)).collect(Collectors.toList());            
                    
            CompanyandTitles.put(company, data);
            
        }
            return CompanyandTitles;
        }
        
        // Map to return the Company and number of jobs inside it 
        
         public Map<String , Long> MapOfCompanieswithNumberOfJobs (DataFrame d , List<Wazzaf_Data> titlesAndcompany)
         {
             String[] Companies = d.stringVector ("Company").distinct ().toArray (new String[]{});
              for (String company : Companies) 
        {
            
            Long data1 = titlesAndcompany.stream().filter(c ->c.getCompany().equals(company)).count();  
            
                    
            CompanyandTitlesNumber.put(company,data1);
        }
              Map<String , Long> CompanyandTitlesNumberArranged = new HashMap<>();
              CompanyandTitlesNumberArranged = CompanyandTitlesNumber 
                .entrySet() 
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)); 
                 System.out.println("Most Demanding Companies: " + CompanyandTitlesNumberArranged);

              return CompanyandTitlesNumberArranged;
         }
        
        
        public Map<String , Long>  MapOfMostTitles (DataFrame d ,List<Wazzaf_Data> titlesAndcompany )   {
            String[] Titles = d.stringVector ("Title").distinct ().toArray (new String[]{});
            for (String title : Titles) 
        {
            
            Long data2 = titlesAndcompany.stream().filter(c ->c.getTitle().equals(title)).count();  
            
                    TitlesNumber.put(title, data2);
        }
                   Map<String , Long> TitlesNumberArranged = new HashMap<>();
                   TitlesNumberArranged = TitlesNumber 
                .entrySet() 
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)); 
                 System.out.println("Most Demanding Job: " + TitlesNumberArranged);
                    return TitlesNumberArranged;
        } 
        
        
        
        
        

          public void FirstGraph(Map<String , Long> Company) {
    
            PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass().getSimpleName()).build ();
            Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120),new Color (80, 110, 45), new Color (80, 143, 160)};chart.getStyler().setSeriesColors(sliceColors);
            chart.getStyler().setSeriesColors(sliceColors);
            
            List<String> keyList = new ArrayList(Company.keySet());
            List<Long> valueList = new ArrayList(Company.values());
             for (int i = 0; i < 4; i++) {

            
             chart.addSeries(keyList.get(i),valueList.get(i));

            }
            
                
           
          
            new SwingWrapper(chart).displayChart();
}
          
          
        public void graphJobs(Map<String , Long> TitlesNumber) {
        CategoryChart chart = new CategoryChartBuilder().width (1024).height (768).title ("JobsDemand").xAxisTitle("Jobs").yAxisTitle("NumberOfJobs").build ();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        chart.getStyler().setStacked(true);
       //chart.addSeries("Passenger's Ages", pNames, pAges);
       

         List<String> keyList = new ArrayList(TitlesNumber.keySet());
         List<Long> valueList = new ArrayList(TitlesNumber.values());
         
         List<String> arrlist1 = keyList.subList(0, 10);
         List<Long>   arrlist2 = valueList.subList(0, 10);

         chart.addSeries("Most Job", arrlist1, arrlist2);
          

             
        new SwingWrapper(chart).displayChart();
}
}