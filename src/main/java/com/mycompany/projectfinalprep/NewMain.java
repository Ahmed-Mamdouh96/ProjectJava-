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

//import joinery.DataFrame;

//import smile.plot.swing.Histogram;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import smile.clustering.BBDTree;
import smile.clustering.KMeans;
import smile.clustering.PartitionClustering;
import smile.data.DataFrame;
import smile.data.vector.DoubleVector;
import smile.data.vector.IntVector;
import smile.plot.swing.Palette;
import smile.plot.swing.ScatterPlot;
import static spire.math.Polynomial.x;



public class NewMain {

    /**
     * @param args the command line arguments
     */
           

    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Wazzaf_DAO Wazzaf = new Wazzaf_DAO();
        
        DataFrame trainData = Wazzaf.readCSV ("D:\\ITI\\Java\\Project\\Wazzaf.csv");
        DataFrame d1 = DataFrame.of(trainData.stream().distinct());
        DataFrame newData= d1.merge(DoubleVector.of("TitleEncoded",Wazzaf.encodeCategory(d1, "Title")));
        newData= newData.merge(DoubleVector.of("CompaniesEncoded",Wazzaf.encodeCategory(newData, "Company")));

        
        List<Wazzaf_Data> titlesAndcompany = Wazzaf.readDatatoListFromCSV("D:\\ITI\\Java\\Project\\Wazzaf.csv");
        
        Map<String ,Long> CompanyandTitles = Wazzaf.MapOfCompanieswithNumberOfJobs(d1, titlesAndcompany);
        
        Map<String , Long> TitlesNumber = Wazzaf.MapOfMostTitles(d1, titlesAndcompany);
        
        Map<String , List<Wazzaf_Data>> MapsOfData = Wazzaf.MapOfCompanieswithJobs(d1, titlesAndcompany);
        
        
        Map<String , Long> Locations = Wazzaf.MapOfMostAreas(d1, titlesAndcompany);
        
        Wazzaf.graphJobs(TitlesNumber);
        
        Wazzaf.FirstGraph(CompanyandTitles);
        Wazzaf.graphAreas(Locations);
        Wazzaf_DAO.Skills("D:\\ITI\\Java\\Project\\Wazzaf.csv");
        System.out.println(newData);
        
        
        double [] Title1 = Wazzaf.encodeCategory(d1, "Title");
        double [] Company1 = Wazzaf.encodeCategory(newData, "Company");
        double [][] myDataFinal = new double[Title1.length][2];
        
        for(int i=0; i<Title1.length;i++){
            for(int j=0;j<2;j++){
                if (j==0)
                {
                myDataFinal[i][j] = Title1[i];
                }
                else {
                  myDataFinal[i][j] = Company1[i];
                      }}
        }
        
        


        KMeans.fit(myDataFinal, 15,100, 1E-4);
        
        
        
        
        
        
//       
    }
       
        
}       
  
