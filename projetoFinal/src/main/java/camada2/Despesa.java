/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camada2;

import java.time.LocalDate;

/**
 *
 * @author Dylan Dirschnabel, Mateus Machado, Nícolas Belesck, Vitória Knihs
 */
public class Despesa implements Movimento{

    /**
     * Classe concreta que implementa inferface 'Movimento' para representar despesas
     */
    
    private double valor;
    private LocalDate data;
    private CategoriaDespesas categoria;
    
    /**
     * Override Movimento
     * Método getter de valor da despesa
     * @return valor : o valor da despesa - double
     */
    @Override
    public double getValor() {
        return valor;
    }

    /**
     * Override Movimento
     * Método setter de valor da despesa
     * @param valor : o valor da despesa - double
     * Valor deve ser positivo!
     */
    @Override
    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da despesa deve ser positivo!");
        }
        this.valor = valor;
    }

    /**
     * Override Movimento
     * Método getter da data da despesa
     * @return data : a data da operação - LocalDate
     */
    @Override
    public LocalDate getData() {
        return data;
    }

    /**
     * Override Movimento
     * Método setter da data da despesa
     * @param data : a data da operação - LocalDate
     */
    @Override
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Override Movimento
     * Método getter da categoria da despesa
     * @return categoria : a categoria da despesa - Categoria
     */
    @Override
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Método setter da categoria da despesa
     * @param categoria : a categoria da despesa - CategoriaDespesas
     */
    public void setCategoria(CategoriaDespesas categoria) {
        this.categoria = categoria;
    }
    
    /**
     * Construtor da classe Despesa
     * @param valor : valor da despesa - double
     * @param data : data da operação - LocalDate
     * @param categoria : categoria da despesa - CategoriaDespesas
     */
    public Despesa(double valor, LocalDate data, CategoriaDespesas categoria) {
        setValor(valor);
        setData(data);
        setCategoria(categoria);
    }
}
