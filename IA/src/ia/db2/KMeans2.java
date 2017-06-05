/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.db2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lorraine
 */
public class KMeans2 {

    //Number of Clusters. This metric should be related to the number of points
    private int NUM_CLUSTERS = 3;

    private ArrayList<Amostra> points;
    private ArrayList<Cluster2> clusters;

    public KMeans2() {
        this.points = new ArrayList();
        this.clusters = new ArrayList();
    }

//    public static void main(String[] args) {
//    	
//    	KMeans2 kmeans = new KMeans2();
//    	kmeans.init();
//    	kmeans.calculate();
//    }
    //Initializes the process
    public void init() {
        //Create Points
        points = Amostra.createPoints();

        //Create Clusters
        //Set Random Centroids
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster2 cluster = new Cluster2(i);
            Amostra centroid = new Amostra();
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        //Print Initial state
        plotClusters();
    }

    private void plotClusters() {
        for (int i = 0; i < NUM_CLUSTERS; i++) {
            Cluster2 c = clusters.get(i);
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

            ArrayList<Amostra> lastCentroids = getCentroids();
            System.out.println("antigos:" + lastCentroids);

            //Assign points to the closer cluster
            assignCluster();

            //Calculate new centroids.
            calculateCentroids();

            iteration++;

            ArrayList<Amostra> currentCentroids = getCentroids();
            System.out.println("novos: " + currentCentroids);

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
        for (Cluster2 cluster : clusters) {
            cluster.clear();
        }
    }

    private ArrayList<Amostra> getCentroids() {
        ArrayList<Amostra> centroids = new ArrayList(NUM_CLUSTERS);
        for (Cluster2 cluster : clusters) {
            Amostra aux = cluster.getCentroid();
            Amostra aux2 = new Amostra();
            aux2.a = aux.a;
            aux2.p = aux.p;
            aux2.c = aux.c;
            aux2.lk = aux.lk;
            aux2.wk = aux.wk;
            aux2.ac = aux.ac;
            aux2.lkg = aux.lkg;
            centroids.add(aux2);
        }
        return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for (Amostra point : points) {
            min = max;
            for (int i = 0; i < NUM_CLUSTERS; i++) {
                Cluster2 c = clusters.get(i);
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
        for (Cluster2 cluster : clusters) {
            double a = 0;
            double p = 0;
            double c = 0;
            double lk = 0;
            double wk = 0;
            double ac = 0;
            double lkg = 0;

            ArrayList<Amostra> list = cluster.getPoints();
            int n_points = list.size();

            for (Amostra point : list) {
                a += point.a;
                p += point.p;
                c += point.c;
                lk += point.lk;
                wk += point.wk;
                ac += point.ac;
                lkg += point.lkg;

            }

            Amostra centroid = cluster.getCentroid();
            if (n_points < 0) {
                centroid.a = a / n_points;
                centroid.p = p / n_points;
                centroid.c = c / n_points;
                centroid.lk = lk / n_points;
                centroid.wk = wk / n_points;
                centroid.ac = ac / n_points;
                centroid.lkg = lkg / n_points;

            }
        }
    }
}
