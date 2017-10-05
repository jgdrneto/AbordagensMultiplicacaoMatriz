package util;

public class Matriz {
    
    private int[][] valores;
        
    private int linha,coluna;
    
    /**
     * Construtor da classe
     * 
     * @param   nLinha    Número de linhas da matriz.
     * @param   nColuna   Número de colunas da matriz.
     */
    public Matriz(int nLinha,int nColuna){
        
        this.linha = nLinha;
        this.coluna = nColuna;
        
        this.valores = new int[this.linha][this.coluna];
    }
    
    /**
     * Retorna o vetor multidimensional da matriz.
     * 
     * @return  Vetor multidimensional com os valores da matriz. 
     */
    public int[][] getMatriz() {
        return valores;
    }
    
    /**
     * Retorna o número de linhas da matriz.
     * 
     * @return  Quantidade de linhas. 
     */
    public int getLinha() {
        return linha;
    }
    
    /**
     * Retorna o número de colunas da matriz.
     * 
     * @return  Quantidade de colunas. 
     */
    public int getColuna() {
        return coluna;
    }
    
    /**
     * Imprime a matriz na tela.
     */
    public void imprimir(){
        
        for(int i=0;i<this.linha;i++){
            for(int j=0;j<this.coluna;j++){
                System.out.print(this.valores[i][j]+" ");
            }
            System.out.print("\n");
        }

        
    }    
    
}
