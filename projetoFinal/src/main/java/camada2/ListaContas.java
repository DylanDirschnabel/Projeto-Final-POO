/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camada2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Dylan Dirschnabel, Mateus Machado, Nícolas Belesck, Vitória Knihs
 */
public class ListaContas {
    /**
     * Classe concreta que representa uma lista de contas
     */
    private ArrayList<Conta> contas = new ArrayList();
    File lista = new File("lista.csv");
    
    /**
     * Método para incluir conta na lista de contas
     * @param conta : conta a incluir - Conta
     * não permite contas que estejam nulas
     */
    public void incluirConta(Conta conta) {
        if(conta == null) {
            throw new IllegalArgumentException("Conta não cadastrada!");
        }
        contas.add(conta);
    }
    
    /**
     * Método sem retorno de valor para importar contas para a lista a partir do arquivo 'lista.csv'
     * ";" como separador de linha
     * Exceção FileNotFound: caso ainda não tenha sido criado o arquivo (ele é criado quando uma conta é cadastrada)
     */
    public void importarContas() {
        
        try {
            Scanner scanner = new Scanner(lista);
            if(scanner.hasNext()) {
                scanner.nextLine();
                
                while(scanner.hasNext()) {
                    String[] valores = scanner.nextLine().split(";");
                    Conta conta = new Conta();
                    conta.setNumeroConta(Integer.parseInt(valores[0]));
                    conta.setTitular(valores[1]);
                    incluirConta(conta);
                }
                
                
            }       
            
        } catch (FileNotFoundException ex) {
            // não tem problema caso não seja encontrada a lista, ela será criada assim que um conta seja cadastrada
        }      
        
    }
    
    
    /**
     * Método getter da lista de contas
     * @return contas : lista de contas - ArrayList>Conta>
     */
    public ArrayList<Conta> getContas() {
        return contas;
    }
    
    /**
     * Método usado para obter o tamanho da lista de contas
     * @return tamanho da ArrayList contas - int
     */
    public int getSize() {
        return contas.size();
    }
    
    
}
