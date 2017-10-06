package manipularArquivo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import util.Matriz;

public class EscritorMatriz {
    /**
     * Método para gravar arquivo com os valores da matriz
     * 
     * @param matriz Matriz na qual o valores devem ser gravados
     */
    public void escreverMatrizEmArquivo(Matriz matriz){
        
        String nomeArquivo = "resultados/C"+matriz.getLinha()+"x"+matriz.getColuna()+".txt";
        
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo));
            
            writer.write(matriz.getLinha() + " " + matriz.getColuna());
            writer.newLine();
            
            for(int i=0;i<matriz.getLinha();i++){
                for(int j =0;j<matriz.getColuna();j++){
                    
                    writer.write(""+matriz.getMatriz()[i][j]);
                    
                    if(j!=matriz.getColuna()-1){
                        writer.write(" ");
                    }
                    
                }
                
                writer.newLine();
            }
            
            writer.flush();
            writer.close();
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   
}
