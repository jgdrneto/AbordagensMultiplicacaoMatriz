package manipularArquivo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import util.Matriz;

public class LeitorMatriz {
    /**
     * Ler o conteúdo do arquivo transformando-o em uma lista de inteiros. 
     * 
     * @param   nome    Nome do arquivo a ser aberto.
     * @return          Lista de inteiros presentes no arquivo.
     */
    private List<Integer> lerArquivo(String nome){
        
        List<Integer> lexemas = new ArrayList<Integer>();
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(nome));
            while(br.ready()){
                
                //Pegando cada palavra de uma linha que esteja separadas por " ".
                for(String l : br.readLine().split(" ")){
                    lexemas.add(Integer.parseInt(l));
                }
                
            }
            br.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return lexemas;
    }
    
    /**
     * Converte o conteúdo do arquivo em um objeto Matriz
     * 
     * @param   nome    Nome do arquivo
     * @return          Matriz com os valores presentes no arquivo
     */
    public Matriz converterArquivoParaMatriz(String nome){
        
        Matriz matriz = null;
        
        List<Integer> lexemas = this.lerArquivo(nome);
        
        //Considera-se apenas matrizes que tenham no minimo a especificação de quantidade de linhas e quantidade de colunas
        if(lexemas.size()>=2){
            
            matriz = new Matriz(lexemas.get(0),lexemas.get(1));
            
            for(int i=0,cont=2;i<lexemas.get(0);i++){
                for(int j =0;j<lexemas.get(1);j++){
                    matriz.getMatriz()[i][j] = lexemas.get(cont++);
                }
            }
            
        }
        
        return matriz;
    }
    
}
