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
import com.sun.xml.internal.messaging.saaj.packaging.mime.Header;
import java.awt.Color;
import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.data.measure.NominalScale;
import smile.data.vector.IntVector;
//import joinery.DataFrame;

//import smile.plot.swing.Histogram;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
import org.apache.commons.math3.dfp.Dfp;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import smile.plot.swing.Histogram;



public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Wazzaf_DAO Wazzaf = new Wazzaf_DAO();
        DataFrame trainData = Wazzaf.readCSV ("D:\\ITI\\Java\\Project\\Wuzzuf_Jobs.csv");
        DataFrame d1 = DataFrame.of(trainData.stream().distinct());
        System.out.println("d1");
        
        List<Wazzaf_Data> titlesAndcompany = Wazzaf.readDatatoListFromCSV("D:\\ITI\\Java\\Project\\Wuzzuf_Jobs.csv");
        
        List<Wazzaf_Data> newList = titlesAndcompany.stream()
                                      .distinct()
                                      .collect(Collectors.toList());
        System.out.println(titlesAndcompany);
        
        int sizebefore = titlesAndcompany.size();
        int sizeafter = newList.size();
        System.out.println(sizebefore);
        System.out.println(sizeafter);
//         Create Array to hold company names 
                 
                 
        String[] Companies = d1.stringVector ("Company").distinct ().toArray (new String[]{});
        
        
        Map<String , List<Wazzaf_Data>> CompanyandTitles = new HashMap<>();
        Map<String , Long> CompanyandTitlesNumber = new HashMap<>();
        Map<String , Long> CompanyandTitlesNumberArranged = new HashMap<>();
        
        for (String company : Companies) 
        {
            
            List <Wazzaf_Data> data = titlesAndcompany.stream().filter(c ->c.getCompany().equals(company)).collect(Collectors.toList());
            Long data1 = titlesAndcompany.stream().filter(c ->c.getCompany().equals(company)).count();  
            
                    
            CompanyandTitles.put(company, data);
            CompanyandTitlesNumber.put(company,data1);
        }
        //System.out.println(CompanyandTitlesNumber);
        CompanyandTitlesNumberArranged = CompanyandTitlesNumber 
                .entrySet() 
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect( toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new)); 
          System.out.println("Most Demanding Companies: " + CompanyandTitlesNumberArranged);

//        System.out.println(CompanyandTitlesNumber);    
    
        Wazzaf.FirstGraph(CompanyandTitlesNumberArranged);

//        Map<String, Long> result =passengerList.stream().filter(p -> p.getSurvived().equals("1")).collect (Collectors.groupingBy(TitanicPassengers::getSex, Collectors.counting() ) );
        
           
}       
  
}