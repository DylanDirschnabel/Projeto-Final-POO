/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camada2;

import java.time.LocalDate;

/**
 * Interface Movimento 
 * Representa o movimento de uma conta bancária
 * @author Dylan Dirschnabel, Mateus Machado, Nícolas Belesck, Vitória Knihs
 */
public interface Movimento {
    public Categoria getCategoria();
    public LocalDate getData();
    public void setData(LocalDate data);
    public void setValor(double valor);
    public double getValor();
}
