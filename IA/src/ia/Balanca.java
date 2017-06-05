/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

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

    String name;  //Class Name: 3 (L, B, R)
    double valName;  // Index Name: (1 = L,2 = B, 3 = R) p/ facilitar os calculos
    double lw; //Left-Weight: 5 (1, 2, 3, 4, 5)
    double ld; //Left-Distance: 5 (1, 2, 3, 4, 5)
    double rw; //Right-Weight: 5 (1, 2, 3, 4, 5)
    double rd; //Right-Distance: 5 (1, 2, 3, 4, 5)

    private int cluster_number = 0;
    
    //inicia os centroides
    public Balanca(){
        Random r = new Random();
        this.lw = 1 + (5 - 1) * r.nextDouble();
        this.ld = 1 + (5 - 1) * r.nextDouble();
        this.rw = 1 + (5 - 1) * r.nextDouble();
        this.rd = 1 + (5 - 1) * r.nextDouble();
        this.valName = 1 + (3 - 1) * r.nextDouble();
    	
    }

    //inicia os pontos
    public Balanca(double lw, double ld, double rw, double rd, String name) {
        this.lw = lw;
        this.ld = ld;
        this.rw = rw;
        this.rd = rd;
        
        this.name = name;
        
       
        
        if(name.contains("L")){
            this.valName = 1;
        }else if(name.contains("B")){
            this.valName = 2;
        }else if(name.contains("R")){
            this.valName = 3;
        }else{
            System.err.println("Houve erro na leitura de nome da balança : " + name );
            this.valName = 0;
        }
       
    }

    public void setCluster(int n) {
        this.cluster_number = n;
    }

    public int getCluster() {
        return this.cluster_number;
    }

    //Calcula a distancia entre o ponto e o centroide.
    protected double distance(Balanca centroide) {
        return Math.sqrt(Math.pow((this.rd - centroide.rd), 2) + Math.pow((this.valName - centroide.valName), 2) + Math.pow((this.lw - centroide.lw), 2) + Math.pow((this.ld - centroide.ld), 2) + Math.pow((centroide.rw - this.rw), 2));
    } 
     
    //Cria lista de pontos
    protected static ArrayList<Balanca> createPoints() {
        ArrayList<Balanca> pontos = new ArrayList();
        
        //ler o arquivo
        //System.out.printf("\nConteúdo do arquivo texto:\n");      
        try {
            FileReader arq = new FileReader("/home/lorraine/Documentos/IA/balance-scale.txt");
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine(); // lê a primeira linha
            // a variável "linha" recebe o valor "null" quando o processo
            // de repetição atingir o final do arquivo texto
            while (linha != null) {
                String array[] = new String[5];
                array = linha.split(",");
                pontos.add(new Balanca(Integer.parseInt(array[1]) , Integer.parseInt(array[2]) , Integer.parseInt(array[3]), Integer.parseInt(array[4]), array[0]));
               // System.out.printf("%s\n", linha);

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
        return ("(" + this.name + ","+ (int)this.lw + "," + (int)this.ld + "," + (int)this.rw + "," + (int)this.rd +")");
    }
}
