package ia.db1;

import ia.db1.Balanca;
import java.util.ArrayList;

/**
 *
 * @author lorraine
 */
public class Cluster1 {

    public ArrayList<Balanca> points;
    public Balanca centroid;
    public int id;

    //Cria novo cluster
    public Cluster1(int id) {
        this.id = id;
        this.points = new ArrayList();
        this.centroid = null;
    }

    public ArrayList<Balanca> getPoints() {
        return points;
    }

    public void addPoint(Balanca point) {
        points.add(point);
    }

    public void setPoints(ArrayList<Balanca> points) {
        this.points = points;
    }

    public Balanca getCentroid() {
        return centroid;
    }

    public void setCentroid(Balanca centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

    public void plotCluster() {
        System.out.println("[Cluster: " + id + "]");
        System.out.println("[Centroide: " + centroid + "]");
        System.out.println("[Pontos: \n");
        points.stream().forEach((p) -> {
            System.out.println(p);
        });
        System.out.println("]");
    }
}
