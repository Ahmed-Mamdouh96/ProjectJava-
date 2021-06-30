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
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
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
        
        
        List<Wazzaf_Data> titlesAndcompany = Wazzaf.readDatatoListFromCSV("D:\\ITI\\Java\\Project\\Wuzzuf_Jobs.csv");
        
        Map<String ,Long> CompanyandTitles = Wazzaf.MapOfCompanieswithNumberOfJobs(d1, titlesAndcompany);
        
        Map<String , Long> TitlesNumber = Wazzaf.MapOfMostTitles(d1, titlesAndcompany);
     
        Wazzaf.graphJobs(TitlesNumber);
        Wazzaf.FirstGraph(CompanyandTitles);
        
    }
    
        
        
        
       
        
}       
  
