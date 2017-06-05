/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.db1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author lorraine
 */
public class Balanca {

    String classe;  //Class Name: 3 (L, B, R)
    double lw; //Left-Weight: 5 (1, 2, 3, 4, 5)
    double ld; //Left-Distance: 5 (1, 2, 3, 4, 5)
    double rw; //Right-Weight: 5 (1, 2, 3, 4, 5)
    double rd; //Right-Distance: 5 (1, 2, 3, 4, 5)

    private int cluster_number = 0;

    //inicia os centroides
    public Balanca() {
        Random random = new Random();
        this.lw = random.nextInt(5 - 1 + 1) + 1;
        this.ld = random.nextInt(5 - 1 + 1) + 1;
        this.rw = random.nextInt(5 - 1 + 1) + 1;
        this.rd = random.nextInt(5 - 1 + 1) + 1;

    }

    //inicia os pontos
    public Balanca(double lw, double ld, double rw, double rd, String name) {
        this.lw = lw;
        this.ld = ld;
        this.rw = rw;
        this.rd = rd;

        this.classe = name;

    }

    public void setCluster(int n) {
        this.cluster_number = n;
    }

    public int getCluster() {
        return this.cluster_number;
    }

    //Calcula a distancia entre o ponto e o centroide.
    protected double distance(Balanca centroide) {
        double val;
        val = Math.sqrt(Math.pow((this.rd - centroide.rd), 2) + Math.pow((this.lw - centroide.lw), 2) + Math.pow((this.ld - centroide.ld), 2) + Math.pow((centroide.rw - this.rw), 2));
        //System.out.println("val: "+val);
        return val;
    }

    //Cria lista de pontos
    protected static ArrayList<Balanca> createPoints() {
        ArrayList<Balanca> pontos = new ArrayList();

        try {
            FileReader arq = new FileReader("src/balance-scale.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê a primeira linha
            while (linha != null) {
                String array[] = new String[5];
                array = linha.split(",");
                pontos.add(new Balanca(Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), array[0]));
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
        // System.out.println();

        return pontos;
    }

    public String toString() {
        return ("(" + this.classe + "," + (int) this.lw + "," + (int) this.ld + "," + (int) this.rw + "," + (int) this.rd + ")");
    }
}
