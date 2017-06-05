/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.util.ArrayList;
import java.util.List;
import ia.Cluster;

/**
 *
 * @author lorraine
 */
public class KMeans {

    //Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 5;

    private ArrayList<Balanca> points;
    private ArrayList<Cluster> clusters;

    public KMeans() {
        this.points = new ArrayList();
        this.clusters = new ArrayList();
    }

//    public static void main(String[] args) {
//    	
//    	KMeans kmeans = new KMeans();
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
            Cluster cluster = new Cluster(i);
            Balanca centroid = new Balanca();
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        //Print Initial state
        plotClusters();
    }

    private void plotClusters() {
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster c = clusters.get(i);
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

            //Assign points to the closer cluster
            assignCluster();

            //Calculate new centroids.
            calculateCentroids();

            iteration++;

            ArrayList<Balanca> currentCentroids = getCentroids();

            //Calculates total distance between new and old Centroids
            double distance = 0;
            for (int i = 0; i < lastCentroids.size(); i++) {
                distance += lastCentroids.get(i).distance(currentCentroids.get(i));
            }
            System.out.println("#################");
            System.out.println("Iteration: " + iteration);
            System.out.println("Centroid distances: " + distance);
            plotClusters();

            if (distance == 0) {
                finish = true;
            }
        }
    }

    private void clearClusters() {
        for (Cluster cluster : clusters) {
            cluster.clear();
        }
    }

    private ArrayList<Balanca> getCentroids() {
        ArrayList<Balanca> centroids = new ArrayList(NUM_CLUSTERS);
        for (Cluster cluster : clusters) {
            Balanca aux = cluster.getCentroid();
            centroids.add(aux);
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
                Cluster c = clusters.get(i);
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
        for (Cluster cluster : clusters) {
            double lw = 0;
            double ld = 0;
            double rw = 0;
            double rd = 0;
            double valName = 0;

            ArrayList<Balanca> list = cluster.getPoints();
            int n_points = list.size();

            for (Balanca point : list) {
                lw += point.lw;
                ld += point.ld;
                rw += point.rw;
                rd += point.rd;
                valName += point.valName;
            }

            Balanca centroid = cluster.getCentroid();
            if (n_points < 0) {
                centroid.lw = lw / n_points;
                centroid.ld = ld / n_points;
                centroid.rw = rw / n_points;
                centroid.rd = rd / n_points;
                centroid.valName = valName / n_points;

            }
        }
    }
}
