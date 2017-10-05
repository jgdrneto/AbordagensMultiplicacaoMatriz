package sequencial;

import algoritmos.Algoritmo;
import util.Matriz;

public class Sequencial implements Algoritmo {
    
    @Override
    public Matriz MultiplicarMatrizes(Matriz matrizA, Matriz matrizB) {
        
        Matriz matrizR = null;
        
        //Verificando condição obrigatória para multiplicação das matrizes
        if(matrizA.getColuna()==matrizB.getLinha()){
            
            matrizR = new Matriz(matrizA.getLinha(),matrizB.getColuna());
            
            for(int i=0;i<matrizA.getLinha();i++){
                for(int j=0;j<matrizB.getColuna();j++){
                    for (int x = 0; x<matrizA.getColuna(); x++) {
                        matrizR.getMatriz()[i][j] += matrizA.getMatriz()[i][x]*matrizB.getMatriz()[x][j];
                    }
                }
            }
            
        }
        
        return matrizR;
    }
    
}
