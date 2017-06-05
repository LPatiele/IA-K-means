/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

/**
 *
 * @author lorraine
 */
public class IA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        KMeans kmeans = new KMeans();
    	kmeans.init();
    	kmeans.calculate();
    }
    
}
