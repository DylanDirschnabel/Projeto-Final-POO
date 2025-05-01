/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camada2;

import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Dylan Dirschnabel, Mateus Machado, Nícolas Belesck, Vitória Knihs
 */
public class Receita implements Movimento {
    /**
     * Classe concreta que implementa inferface 'Movimento' para representar receitas
     */

    private double valor;
    private LocalDate data;
    private CategoriaReceitas categoria;

    /**
     * Override Movimento
     * Método getter de valor da receita
     * @return valor : o valor da receita - double
     */
    @Override
    public double getValor() {
        return valor;
    }

    /**
     * Override Movimento
     * Método setter de valor da receita
     * @param valor : o valor da receita - double
     * Valor deve ser positivo!
     */
    @Override
    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da receita deve ser positivo!");
        }
        this.valor = valor;
    }

    /**
     * Override Movimento
     * Método getter da data da receita
     * @return data : a data da operação - LocalDate
     */
    @Override
    public LocalDate getData() {
        return data;
    }

    /**
     * Override Movimento
     * Método setter da data da receita
     * @param data : a data da operação - LocalDate
     */
    @Override
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Override Movimento
     * Método getter da categoria da receta
     * @return categoria : a categoria da receita - Categoria
     */
    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Método setter da categoria da receita
     * @param categoria : a categoria da receita - CategoriaReceita
     */
    public void setCategoria(CategoriaReceitas categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Construtor da classe Receita
     * @param valor : valor da receita - double
     * @param data : data da operação - LocalDate
     * @param categoria : categoria da receita - CategoriaReceita
     */
    public Receita(double valor, LocalDate data, CategoriaReceitas categoria) {
        setValor(valor);
        setData(data);
        setCategoria(categoria);
    }
}
