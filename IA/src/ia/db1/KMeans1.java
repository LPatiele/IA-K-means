package ia.db1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lorraine
 */
public class KMeans1 {

    //Numero de clusters
    private int k = 3;

    private ArrayList<Balanca> points;
    private ArrayList<Cluster1> clusters;

    public KMeans1() {
        this.points = new ArrayList();
        this.clusters = new ArrayList();
    }

    //Initializações
    public void init() {
        //Create Points
        points = Balanca.createPoints();

        //Create Clusters
        //Set Random Centroids
        for (int i = 0; i < k; i++) {
            Cluster1 cluster = new Cluster1(i);
            Balanca centroid = new Balanca();
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }

        //Print Initial state
        plotClusters();
    }

    private void plotClusters() {
        for (int i = 0; i < k; i++) {
            Cluster1 c = clusters.get(i);
            c.plotCluster();
        }
    }

    //Calcula o processo k-means
    public void calculate() {
        boolean finish = false;
        int iteration = 0;

        //Adiciona novos dados, um por vez, recalculando centróides
        while (!finish) {
            clearClusters();

            ArrayList<Balanca> lastCentroids = getCentroids();
//            System.out.println("antigos:" + lastCentroids);

            //Atribuir pontos ao cluster mais próximo
            assignCluster();

            //Calcula novos centroides
            calculateCentroids();

            iteration++;

            ArrayList<Balanca> currentCentroids = getCentroids();
//            System.out.println("novos: " + currentCentroids);

            //Calcule a distância total entre os centroides novos e antigos
            double distance = 0;
            for (int i = 0; i < lastCentroids.size(); i++) {
                distance += lastCentroids.get(i).distance(currentCentroids.get(i));
            }

            System.out.println("###################################################");
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
        ArrayList<Balanca> centroids = new ArrayList(k);
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
            for (int i = 0; i < k; i++) {
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
            if (n_points > 0) {
                centroid.lw = lw / n_points;
                centroid.ld = ld / n_points;
                centroid.rw = rw / n_points;
                centroid.rd = rd / n_points;

            }
        }
    }
}
