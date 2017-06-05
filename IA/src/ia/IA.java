/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import ia.db1.KMeans1;
import ia.db2.KMeans2;
import java.util.ArrayList;
import java.util.Random;



/**
 *
 * @author lorraine
 */
public class IA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //execução da primeira base de dados
//        KMeans1 kmeans1 = new KMeans1();
//    	kmeans1.init();
//    	kmeans1.calculate();

        
        //execução da segunda base de dados
        KMeans2 kmeans2 = new KMeans2();
    	kmeans2.init();
    	kmeans2.calculate();
    }
    
}
