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
//import java.util.logging.Level;
//import java.util.logging.Logger;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;


public class NewMain {

    /**
     * @param args the command line arguments
     */
             private static final String COMMA_DELIMITER = ",";

    public static void main(String[] args) {
        // TODO code application logic here

        Wazzaf_DAO Wazzaf = new Wazzaf_DAO();
        
        DataFrame trainData = Wazzaf.readCSV ("D:\\ITI\\Java\\Project\\Wuzzuf_Jobs.csv");
        DataFrame d1 = DataFrame.of(trainData.stream().distinct());
        
        
        List<Wazzaf_Data> titlesAndcompany = Wazzaf.readDatatoListFromCSV("D:\\ITI\\Java\\Project\\Wuzzuf_Jobs.csv");
        
        Map<String ,Long> CompanyandTitles = Wazzaf.MapOfCompanieswithNumberOfJobs(d1, titlesAndcompany);
        
        Map<String , Long> TitlesNumber = Wazzaf.MapOfMostTitles(d1, titlesAndcompany);
        
        Map<String , List<Wazzaf_Data>> MapsOfData = Wazzaf.MapOfCompanieswithJobs(d1, titlesAndcompany);
        //System.out.println(MapsOfData);
        
        Map<String , Long> Locations = Wazzaf.MapOfMostAreas(d1, titlesAndcompany);
        
        Wazzaf.graphJobs(TitlesNumber);
        
        Wazzaf.FirstGraph(CompanyandTitles);
        Wazzaf.graphAreas(Locations);
        Wazzaf_DAO.Skills("D:\\ITI\\Java\\Project\\Wuzzuf_Jobs.csv");
        
        
        

        
        
        
        
        
//       
    }
       
        
}       
  
