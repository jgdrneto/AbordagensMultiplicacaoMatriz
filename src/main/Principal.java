package main;

import algoritmos.Algoritmo;
import concorrente.Concorrente;
import manipularArquivo.EscritorMatriz;
import manipularArquivo.LeitorMatriz;
import sequencial.Sequencial;
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
            
            Matriz matrizA = leitor.converterLexemasParaMatriz("instancias/A"+args[0]+"x"+args[0]+".txt");
            Matriz matrizB = leitor.converterLexemasParaMatriz("instancias/B"+args[0]+"x"+args[0]+".txt");
            
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
            
            escritor.EscreverMatrizEmArquivo(algoritmo.MultiplicarMatrizes(matrizA, matrizB));
            
        }else{
            System.out.println("Falta de argumentos para início do algoritmo");
        }
        
        
    }

}
