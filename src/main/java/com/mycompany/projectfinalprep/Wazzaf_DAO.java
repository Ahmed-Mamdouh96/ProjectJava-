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
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
public class Wazzaf_DAO {
    
        List<Wazzaf_Data> titlesAndcompany;
        

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
        
          public void FirstGraph(Map<String , Long> Company) {
    
            PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass().getSimpleName()).build ();
            Color[] sliceColors= new Color[]{new Color (180, 68, 50), new Color (130, 105, 120),new Color (80, 110, 45), new Color (80, 143, 160)};chart.getStyler().setSeriesColors(sliceColors);
            chart.getStyler().setSeriesColors(sliceColors);
            
            for (String key : Company.keySet()) {
            
             chart.addSeries(key,Company.get(key));

}
            
                
           
          
            new SwingWrapper(chart).displayChart();
}
    
}
