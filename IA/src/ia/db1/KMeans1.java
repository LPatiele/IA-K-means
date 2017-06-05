/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.db1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lorraine
 */
public class KMeans1 {

    //Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 3;

    private ArrayList<Balanca> points;
    private ArrayList<Cluster1> clusters;
    

    public KMeans1() {
        this.points = new ArrayList();
        this.clusters = new ArrayList();
    }

//    public static void main(String[] args) {
//    	
//    	KMeans1 kmeans = new KMeans1();
//    	kmeans.init();
//    	kmeans.calculate();
//    }
    //Initializes the process
    public void init() {
        //Create Points
        points = Balanca.createPoints();

        //Create Clusters
        //Set Random Centroids
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster1 cluster = new Cluster1(i);
            Balanca centroid = new Balanca();
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        //Print Initial state
        plotClusters();
    }

    private void plotClusters() {
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster1 c = clusters.get(i);
            c.plotCluster();
        }
    }

    //The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;

        // Add in new data, one at a time, recalculating centroids with each new one. 
        while (!finish) {
            //Clear cluster state
            clearClusters();

            ArrayList<Balanca> lastCentroids = getCentroids();
            System.out.println("antigos:"+ lastCentroids);

            //Assign points to the closer cluster
            assignCluster();

            //Calculate new centroids.
            calculateCentroids();

            iteration++;

            ArrayList<Balanca> currentCentroids = getCentroids();
            System.out.println("novos: " +currentCentroids);

            //Calculates total distance between new and old Centroids
            double distance = 0;
            for (int i = 0; i < lastCentroids.size(); i++) {
                distance += lastCentroids.get(i).distance(currentCentroids.get(i));
                System.out.println("calcula");
            }
            System.out.println("#################");
            System.out.println("Iteração: " + iteration);
            System.out.println("Distancia dos centroides: " + distance);
            plotClusters();

            if (distance == 0) {
                finish = true;
            }
        }
    }

    private void clearClusters() {
        for (Cluster1 cluster : clusters) {
            cluster.clear();
        }
    }

    private ArrayList<Balanca> getCentroids() {
        ArrayList<Balanca> centroids = new ArrayList(NUM_CLUSTERS);
        for (Cluster1 cluster : clusters) {
            Balanca aux = cluster.getCentroid();
            Balanca aux2 = new Balanca();
            aux2.ld = aux.ld;
            aux2.lw = aux.lw;
            aux2.classe = aux.classe;
            aux2.rd = aux.rd;
            aux2.rw = aux.rw;
            centroids.add(aux2);
        }
        return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for (Balanca point : points) {
            min = max;
            for (int i = 0; i < NUM_CLUSTERS; i++) {
                Cluster1 c = clusters.get(i);
                distance = point.distance(c.getCentroid());
                if (distance < min) {
                    min = distance;
                    cluster = i;
                }
            }
            point.setCluster(cluster);
            clusters.get(cluster).addPoint(point);
        }
    }

    private void calculateCentroids() {
        for (Cluster1 cluster : clusters) {
            double lw = 0;
            double ld = 0;
            double rw = 0;
            double rd = 0;
           

            ArrayList<Balanca> list = cluster.getPoints();
            int n_points = list.size();

            for (Balanca point : list) {
                lw += point.lw;
                ld += point.ld;
                rw += point.rw;
                rd += point.rd;
               
            }

            Balanca centroid = cluster.getCentroid();
            if (n_points < 0) {
                centroid.lw = lw / n_points;
                centroid.ld = ld / n_points;
                centroid.rw = rw / n_points;
                centroid.rd = rd / n_points;
                

            }
        }
    }
}
