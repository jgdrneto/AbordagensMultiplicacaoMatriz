package algoritmos.concorrente;

import util.Matriz;

public class Calcular implements Runnable{
    
    Matriz matrizA,matrizB,matrizR;
    int lMin,lMax;
    
    /**
     * Construtor da classe
     * 
     * @param nMatrizA Matriz mais a esquerda da multiplicação 
     * @param nMatrizB Matriz mais a direita da multiplicação
     * @param nMatrizR Matriz resultante da multiplicação
     * @param nI       Número da linha mínima que irá ser calculada
     * @param nM       Número da linha máxima que irá ser calculada
     */
    Calcular(Matriz nMatrizA, Matriz nMatrizB, Matriz nMatrizR,int nI,int nM){
        this.matrizA = nMatrizA;
        this.matrizB = nMatrizB;
        this.matrizR = nMatrizR;
        this.lMin = nI;
        this.lMax = nM;
    }
        
    @Override
    public void run() {
        for(;this.lMin<this.lMax;this.lMin++){
            for(int j=0;j<this.matrizB.getColuna();j++){
                for (int x = 0; x<this.matrizA.getColuna(); x++) {
                    this.matrizR.getMatriz()[this.lMin][j] += this.matrizA.getMatriz()[this.lMin][x]*this.matrizB.getMatriz()[x][j];
                }
            }
        }  
    }

}
