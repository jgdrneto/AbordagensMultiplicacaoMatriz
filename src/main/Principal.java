package main;

import algoritmos.Algoritmo;
import algoritmos.concorrente.Concorrente;
import algoritmos.sequencial.Sequencial;
import manipularArquivo.EscritorMatriz;
import manipularArquivo.LeitorMatriz;
import util.Matriz;

public class Principal {
    
    /**
     * Método principal
     * 
     * @param args Argumentos a serem inseridos no terminal
     */
    public static void main(String[] args) {
        
        if(args.length>=1){
                       
            LeitorMatriz leitor = new LeitorMatriz();
            
            Matriz matrizA = leitor.converterArquivoParaMatriz("instancias/A"+args[0]+"x"+args[0]+".txt");
            Matriz matrizB = leitor.converterArquivoParaMatriz("instancias/B"+args[0]+"x"+args[0]+".txt");
            
            Algoritmo algoritmo = null;
            
            switch(args.length){
                case 1:
                    algoritmo = new Sequencial();
                break;
                default:
                    int numThreads = Integer.parseInt(args[1]);
                    
                    if(numThreads==0){
                        //Obtendo a quantidade máxima de processadores lógicos disponíveis da JVM
                        numThreads = Runtime.getRuntime().availableProcessors();
                    }
                    
                    algoritmo = new Concorrente(numThreads);
                break;    
            }
            
            EscritorMatriz escritor = new EscritorMatriz();
            
            escritor.escreverMatrizEmArquivo(algoritmo.multiplicarMatrizes(matrizA, matrizB));
            
        }else{
            System.out.println("Falta de argumentos para início do algoritmo");
        }
        
        
    }

}
