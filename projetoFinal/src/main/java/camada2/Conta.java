/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camada2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dylan Dirschnabel, Mateus Machado, Nícolas Belesck, Vitória Knihs
 */
public class Conta {

    /**
     * Classe concreta representa conta com lista de despesas e receitas
     */
    
    private ArrayList<Movimento> movimentos = new ArrayList();
    private int numeroConta;
    private String titular;
    private File file = new File("lista.csv");
    
    /**
     * Método usado para obter o saldo da conta até a data atual
     * @return saldo da conta até a data atual - double
     */
    public double getSaldo() {
        double saldo = 0;
        LocalDate dataLimite = LocalDate.now();
        for(Movimento m : movimentos) {
            if(!m.getData().isAfter(dataLimite)) {
                if(m instanceof Despesa) {
                    saldo -= ((Despesa)m).getValor();
                } else if(m instanceof Receita) {
                    saldo += ((Receita)m).getValor();
                }
            }
        }
        return saldo;
    }
    
    /**
     * Método usado para obter o saldo total da conta, incluindo lançamentos futuros
     * @return saldo total da conta - double
     */
    public double getSaldoTotal() {
        double saldo = 0;
        for (Movimento m : movimentos) {
 
                if (m instanceof Despesa) {
                    saldo -= ((Despesa) m).getValor();
                } else if (m instanceof Receita) {
                    saldo += ((Receita) m).getValor();
                }
            
        }
        return saldo;
    }


    /**
     * Método getter do número da conta
     * @return número da conta - int
     */
    public int getNumeroConta() {
        return numeroConta;
    }

    /**
     * Método setter do número da conta
     * @param numeroConta : número da conta - int
     * converte números negativos para positivos
     * define como 8 o máximo de caracteres que o número da conta pode conter
     */
    public void setNumeroConta(int numeroConta) {
        if(numeroConta < 0) {
            numeroConta *= -1;
        }
        if(numeroConta >= 100000000) {
            throw new IllegalArgumentException("Número da conta não pode ter mais que 8 caracteres!");
        }
        this.numeroConta = numeroConta;
    }

    /**
     * Método getter do titular da conta
     * @return titular da conta - String
     */
    public String getTitular() {
        return titular;
    }

    /**
     * Método setter do titular da conta
     * @param titular : titular da conta - String
     * não permite o caracter ';' no nome do titular
     */
    public void setTitular(String titular) {
        if(titular.contains(";")) {
            throw new IllegalArgumentException("Titular não pode haver este caracter: ';'");
        }
        this.titular = titular;
    }     
    
    /**
     * Método adiciona receita para a lista de movimentos da conta
     * @param receita : receita a ser adicionada - Receita 
     * não permite adicionar receitas nulas
     */
    public void incluirReceita(Receita receita) {
        if(receita == null) {
            throw new IllegalArgumentException("Receita não cadastrada");
        }
        String data = receita.getData().getDayOfMonth() + "a" + receita.getData().getMonthValue() + "a" + receita.getData().getYear();
        arquivarInformacoes(receita.getValor(), data, receita.getCategoria().toString(), 1);
        movimentos.add(receita);
        
    }
    
    /**
     * Método adiciona despesa para a lista de movimentos da conta
     * @param despesa : despesa a ser adicionada - Despesa 
     * não permite adicionar despesas nulas
     */
    public void incluirDespesa(Despesa despesa) {
        if(despesa == null) {
            throw new IllegalArgumentException("Despesa não cadastrada");
        }
        
        String data = despesa.getData().getDayOfMonth() + "a" + despesa.getData().getMonthValue() + "a" + despesa.getData().getYear();
        arquivarInformacoes(despesa.getValor(), data, despesa.getCategoria().toString(), 2);
        movimentos.add(despesa);
    }
    
    /**
     * Método getter da lista de movimentos
     * @return movimentos : a lista de movimentos da conta - ArrayList>Movimento>
     */
    public ArrayList<Movimento> getMovimentos() {
        return movimentos;
    }
    
    /**
     * Método usado para obter o tamanho da lista de movimentos
     * @return tamanho da ArrayList movimentos - int
     */
    public int getSize() {
        return movimentos.size();
    }
    
    
    /**
     * Método usado para obter o nome do arquivo em que as informações da conta serão armazenadas
     * @return nomeArquivo : nome do arquivo com as informações armazenadas - String do número da conta com ".csv" acoplado ao final
     */
    public String getNomeArquivo() {
        String nomeArquivo = String.valueOf(getNumeroConta()) + ".csv";
        return nomeArquivo;
    }
    
    /**
     * Método usado para armazenar as informações da conta em arquivo
     * Escreve o cabeçalho no arquivo de lista de contas, caso ainda não tenha, e o numero, titular e nome do arquivo da conta
     * No arquivo próprio da conta, escreve o cabeçalho csv
     * Exceção IOException: caso tenha falha na gravação
     */
    public void arquivarConta() {

        try {
            PrintWriter writer = new PrintWriter(new FileWriter("lista.csv", true), true);
            
            if(file.length() == 0) {
                writer.println("numero;titular;endereco");
            }
            
            writer.println(numeroConta + ";" + titular + ";" + getNomeArquivo());
            
        } catch (IOException ex) {
            Logger.getLogger(Conta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            PrintWriter writer = new PrintWriter(new FileWriter(getNomeArquivo(), true), true);

            writer.println("valor;data;categoria;tipo");
            
            
        } catch (IOException ex) {
            Logger.getLogger(Conta.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Método usado dentro das funções 'incluirReceita' e 'incluirDespesa', para armazenar movimentação na lista da própria conta
     * @param valor : valor da movimentação - double
     * @param data : data da movimentação - double
     * @param categoria : categoria da movimentação - Categoria
     * @param tipo : tipo da movimentação (1 para receita, 2 para despesa)  - int
     * Exceção IOException: caso tenha falha na gravação
     */
    public void arquivarInformacoes(Double valor, String data, String categoria, int tipo) {
        try {
            
            PrintWriter writer = new PrintWriter(new FileWriter(getNomeArquivo(), true), true);
            writer.println(valor + ";" + data + ";" + categoria + ";" + tipo);
        } catch (IOException ex) {
            Logger.getLogger(Conta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Método usado para recuperar a lista de movimentações de uma conta
     * Exceção FileNotFound
     */
    public void recuperarInformacoes() {
        try {
            File arquivo = new File(getNomeArquivo());
            Scanner scanner = new Scanner(arquivo);
            
            scanner.nextLine();
            
            while(scanner.hasNext()) {
                String[] valores = scanner.nextLine().split(";");
                
                
                if(valores[3].equals("2")) {
                    
                    CategoriaDespesas categoria = null;
                    String c = valores[2];
                    
                    switch(c) {
                        case "ALIMENTACAO":
                            categoria = CategoriaDespesas.ALIMENTACAO;
                            break;
                        case "TRANSPORTE":
                            categoria  = CategoriaDespesas.TRANSPORTE;
                            break;
                        case "RESIDENCIA":
                            categoria = CategoriaDespesas.RESIDENCIA;
                            break;
                        case "SAUDE":
                            categoria = CategoriaDespesas.SAUDE;
                            break;
                        case "ENTRETERIMENTO":
                            categoria = CategoriaDespesas.ENTRETERIMENTO;
                            break;
                        case "EDUCACAO":
                            categoria = CategoriaDespesas.EDUCACAO;
                            break;
                        case "OUTRAS":
                            categoria = CategoriaDespesas.OUTRAS;
                            break;
                    }
                    
                    String[] data = valores[1].split("a");
                    LocalDate dia = LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0]));
                    
                    Movimento despesa = new Despesa(Double.parseDouble(valores[0]), dia, categoria);
                    movimentos.add(despesa);
                } else {
                    
                    CategoriaReceitas categoria = null;
                    String c = valores[2];
                    
                    switch(c) {
                        case "SALARIO":
                            categoria = CategoriaReceitas.SALARIO;
                            break;
                        case "DECIMO_TERCEIRO":
                            categoria = CategoriaReceitas.DECIMO_TERCEIRO;
                            break;
                        case "FERIAS":
                            categoria = CategoriaReceitas.FERIAS;
                            break;
                        case "TIGRINHO":
                            categoria = CategoriaReceitas.TIGRINHO;
                            break;
                        case "OUTRAS":
                            categoria = CategoriaReceitas.OUTRAS;
                            break;
                    }
                    
                    String[] data = valores[1].split("a");
                    LocalDate dia = LocalDate.of(Integer.parseInt(data[2]),Integer.parseInt(data[1]),Integer.parseInt(data[0]));
                    
                    Movimento receita = new Receita(Double.parseDouble(valores[0]), dia, categoria);
                    movimentos.add(receita);
                    
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
