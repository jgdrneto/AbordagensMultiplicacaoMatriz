package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import algoritmos.Algoritmo;
import algoritmos.concorrente.Concorrente;
import algoritmos.sequencial.Sequencial;
import manipularArquivo.LeitorMatriz;
import util.Matriz;

public class GerarEstatisticas {

    List<Matriz> matrizes;
    Algoritmo sequencial;
    Algoritmo concorrente;
    
    /**
     * Construtor da classe
     */
    public GerarEstatisticas(){

        this.sequencial = new Sequencial();
        this.concorrente = new Concorrente(Runtime.getRuntime().availableProcessors());
        
        this.matrizes = obterMatrizes(obterNomesInstancias());

    }
    
    /**
     * Obter as medições de tempo da matriz
     * 
     * @param numeroDeInteracao Número de interações a serem realizadas  
     * @return                  Lista com as medições;
     */
    public void obterMedicoes(int numeroDeInteracao) {
       
        
        for(int j=0;j<matrizes.size();j=j+2){
            
            List<Long> medicoesSequencial = new ArrayList<Long>();
            List<Long> medicoesConcorrente = new ArrayList<Long>();
            
            for(int i=0;i<numeroDeInteracao;i++){
                
                System.out.println("Iteração "+ (i+1) + " da matriz de ordem " + matrizes.get(j).getLinha());
     
                medicoesSequencial.add(obterTempoDeExecucao(this.sequencial,this.matrizes.get(j),this.matrizes.get(j+1)));
                medicoesConcorrente.add(obterTempoDeExecucao(this.concorrente,this.matrizes.get(j),this.matrizes.get(j+1)));
                
            }
            
            System.out.println("===============================================");
            System.out.println("Medições");
            System.out.println("===============================================");
            long tempoMedioSequecial = medicoesSequencial.stream().mapToLong(Long::longValue).sum()/medicoesSequencial.size();
            long tempoMedioConcorrente = medicoesConcorrente.stream().mapToLong(Long::longValue).sum()/medicoesConcorrente.size();
            System.out.println("Tempo minimo sequencial: " + Collections.min(medicoesSequencial));
            System.out.println("Tempo minimo concorrente: " + Collections.min(medicoesConcorrente));
            System.out.println("Tempo médio sequencial: " + tempoMedioSequecial);
            System.out.println("Tempo médio concorrente: " + tempoMedioConcorrente);
            System.out.println("Tempo máximo sequencial: " + Collections.max(medicoesSequencial));
            System.out.println("Tempo máximo concorrente: " + Collections.max(medicoesConcorrente));
            System.out.println("Desvio padrão Sequencial: " + obterDesvioPadrao(medicoesSequencial));
            System.out.println("Desvio padrão Concorrente: " + obterDesvioPadrao(medicoesConcorrente));
            System.out.println("Speed-Up: "+ ((double)tempoMedioSequecial)/tempoMedioConcorrente);
            System.out.println("===============================================");
            
        }
    }
    
    /**
     * Obtem as matrizes a partir dos nomes dos arquivos
     * 
     * @param arquivos  Nomes dos arquivos com as matrizes
     * @return          Lista com as matrizes dos arquivos
     */
     
    private List<Matriz> obterMatrizes(List<String> arquivos) {
        
        List<Matriz> matrizes = new ArrayList<Matriz>();
        
        LeitorMatriz leitor = new LeitorMatriz();
        
        for(String nome : arquivos){
            matrizes.add(leitor.converterArquivoParaMatriz(nome));
        }
        
        return matrizes;
    }

    /**
     * Método que serve para obter o nome das instâncias
     * 
     * @return Lista com os nomes das instâncias a serem usadas nas estatísticas
     */
    private List<String> obterNomesInstancias(){
        
        List<String> nomes = new ArrayList<String>();
        
        for(int i=4;i<=1024;i=2*i){
    
            nomes.add("instancias/A"+i+"x"+i+".txt");
            nomes.add("instancias/B"+i+"x"+i+".txt");
            
        }
        
        return nomes;
    }
    
    /**
     * Calcula o tempo de uma execução em nanosegundos
     * 
     * @param algoritmo Algoritmo a ser usado.
     * @param matrizA   Matriz a esquerda a ser usada na multiplicação.
     * @param matrizB   Matriz a direita a ser usada na multiplicação.
     * 
     * @return          Tempo de uma execução em nanosegundos
     */
    private long obterTempoDeExecucao(Algoritmo algoritmo,Matriz matrizA, Matriz matrizB){
        
        if(matrizA.getLinha()<=128){
        
            long tempoInicial = System.nanoTime();
        
            algoritmo.multiplicarMatrizes(matrizA, matrizB);
        
            return System.nanoTime() - tempoInicial;
        }else{
            
            long tempoInicial = System.currentTimeMillis();
            
            algoritmo.multiplicarMatrizes(matrizA, matrizB);
        
            return System.currentTimeMillis() - tempoInicial;
        }
    }
    
    /**
     * Calcula o desvio padrão de uma lista passada por parâmetro
     * 
     * @param valores   Lista com os valores a serem calculado o desvio padrão
     * @return          Valor do desvio padrão
     */
    private double obterDesvioPadrao(List<Long> valores){

        long tempoMedio = valores.stream().mapToLong(Long::longValue).sum()/valores.size();
        
        long somatorio = 0;
        for(Long l : valores){
            somatorio += Math.pow((l - tempoMedio),2);
        }
        
        return Math.sqrt(somatorio/valores.size());
    }
    
    /**
     * Método principal
     * 
     * @param args Argumentos a serem inseridos no terminal
     */
    public static void main(String[] args) {
        
        GerarEstatisticas gerarE =  new GerarEstatisticas();
        
        gerarE.obterMedicoes(20);
        
    }
}