/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.db2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lorraine
 */
public class Amostra {

    double a;  //area A
    double p;  // perimeter P
    double c; //compactness C = 4*pi*A/P^2
    double lk; //length of kernel
    double wk; //width of kernel
    double ac; //asymmetry coefficient 
    double lkg;//length of kernel groove
    int tipo; // pode ser dos tipo 1,2 e 3

    private int cluster_number = 0;

    //inicia os centroides
    public Amostra() {
        Random r = new Random();
        this.a = 10 + (22 - 10) * r.nextDouble();
        this.p = 12 + (18 - 12) * r.nextDouble();
        this.c = 0 + (1 - 0) * r.nextDouble();
        this.lk = 0 + (7 - 0) * r.nextDouble();
        this.wk = 2 + (6 - 2) * r.nextDouble();
        this.ac = 0 + (9 - 0) * r.nextDouble();
        this.lkg = 3 + (7 - 3) * r.nextDouble();

    }

    //inicia os pontos
    public Amostra(double a, double p, double c, double lk, double wk, double ac, double lkg, int tipo) {
        this.a = a;
        this.p = p;
        this.c = c;
        this.lk = lk;
        this.wk = wk;
        this.ac = ac;
        this.lkg = lkg;
        this.tipo = tipo;

    }

    public void setCluster(int n) {
        this.cluster_number = n;
    }

    public int getCluster() {
        return this.cluster_number;
    }

    //Calcula a distancia entre o ponto e o centroide.
    protected double distance(Amostra centroide) {
        double val;
        val = Math.sqrt(Math.pow((this.a - centroide.a), 2) + Math.pow((this.p - centroide.p), 2) + Math.pow((this.c - centroide.c), 2) + Math.pow((this.lk - centroide.lk), 2) + Math.pow((this.wk - centroide.wk), 2) + Math.pow((this.ac - centroide.ac), 2) + Math.pow((centroide.lkg - this.lkg), 2));
//       System.out.println("val: " + val);
        return val;
    }

    //Cria lista de pontos
    protected static ArrayList<Amostra> createPoints() {
        ArrayList<Amostra> pontos = new ArrayList();
        try {
            FileReader arq = new FileReader("src/seeds_dataset.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê a primeira linha
            while (linha != null) {
                String array[] = new String[8];
                array = linha.split("\\s");
                try {
                    pontos.add(new Amostra(Double.parseDouble(array[0]), Double.parseDouble(array[1]), Double.parseDouble(array[2]), Double.parseDouble(array[3]), Double.parseDouble(array[4]), Double.parseDouble(array[5]), Double.parseDouble(array[6]), Integer.parseInt(array[7])));
                    // System.out.println(Double.parseDouble(array[0]) + "," + Double.parseDouble(array[1]) + "," + Double.parseDouble(array[2]) + "," + Double.parseDouble(array[3]) + "," + Double.parseDouble(array[4]) + "," + Double.parseDouble(array[5]) + "," + Double.parseDouble(array[6]) + "," + Integer.parseInt(array[7]));
                } catch (NumberFormatException ex) {
                    System.err.println("Erro :");
                    System.err.printf("%s\n", linha);
                }

                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        return pontos;
    }

    public String toString() {
        return ("(" + this.a + "," + this.p + "," + this.c + "," + this.lk + "," + this.wk + "," + this.ac + "," + this.lkg + "," + this.tipo + ")");
    }
}
