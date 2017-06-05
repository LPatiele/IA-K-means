/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
	
	//Creates a new Cluster1
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
		System.out.println("[Cluster: " + id+"]");
		System.out.println("[Centroid: " + centroid + "]");
		System.out.println("[Points: \n");
                points.stream().forEach((p) -> {
                    System.out.println(p);
        });
		System.out.println("]");
	}
}
