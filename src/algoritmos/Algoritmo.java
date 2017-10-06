package algoritmos;

import util.Matriz;

public interface Algoritmo{
    /**
     * Multiplica a matrizA x matrizB para gerar uma nova matriz com o resultado.
     * 
     * @param   matrizA     Matriz do lado esquerdo a ser multiplicada.
     * @param   matrizB     Matriz do lado direito a ser multiplicada.
     * @return              Matriz resultante da multiplicação.
     */
    public abstract Matriz multiplicarMatrizes(Matriz matrizA, Matriz matrizB);
    
}
