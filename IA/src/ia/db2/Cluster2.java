package ia.db2;

import java.util.ArrayList;

/**
 *
 * @author lorraine
 */
public class Cluster2 {

    public ArrayList<Amostra> points;
    public Amostra centroid;
    public int id;

    //Cria novo cluster
    public Cluster2(int id) {
        this.id = id;
        this.points = new ArrayList();
        this.centroid = null;
    }

    public ArrayList<Amostra> getPoints() {
        return points;
    }

    public void addPoint(Amostra point) {
        points.add(point);
    }

    public void setPoints(ArrayList<Amostra> points) {
        this.points = points;
    }

    public Amostra getCentroid() {
        return centroid;
    }

    public void setCentroid(Amostra centroid) {
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
