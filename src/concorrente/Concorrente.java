package concorrente;

import java.util.ArrayList;
import java.util.List;

import algoritmos.Algoritmo;
import util.Matriz;

public class Concorrente implements Algoritmo{
    
    int numThreads;
    
    /**
     * Construtor da classe
     * 
     * @param nNumThreads Número de threads que serão utilizadas no algoritmo
     */
    
    public Concorrente(int nNumThreads){
        
        this.numThreads = nNumThreads;
    }
    
    public Matriz MultiplicarMatrizes(Matriz matrizA, Matriz matrizB) {
        
        Matriz matrizR=null;
        
        if(matrizA.getColuna()==matrizB.getLinha()){
        
            matrizR = new Matriz(matrizA.getLinha(),matrizB.getColuna());
            
            List<Thread> threads = new ArrayList<Thread>(this.numThreads);
            
            int fator = matrizA.getLinha()/this.numThreads;
            
            if(fator==0){
                fator=1;
            }
            
            for(int i = 0,j=0; i < numThreads;i++,j+=fator){
               
               if(i!=numThreads-1){ 
                
                   threads.add(new Thread(new Calcular(matrizA,matrizB,matrizR,j,j+fator)));
               
               }else{

                   int resto = matrizA.getLinha()%this.numThreads;
                   
                   if(resto!=0){
                       threads.add(new Thread(new Calcular(matrizA,matrizB,matrizR,j,j+fator+resto)));
                       j+=resto;
                   }else{
                       threads.add(new Thread(new Calcular(matrizA,matrizB,matrizR,j,j+fator)));
                   }
                   
               }
              
            }
            
            for(int i = 0; i < this.numThreads; i++){
                threads.get(i).start();
            }
            
            for(int i = 0; i < this.numThreads; i++){
                
                try {
                    threads.get(i).join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
                
            
        }    
        
        return matrizR;
         
    }
}
