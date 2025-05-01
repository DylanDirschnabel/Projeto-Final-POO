/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package camada2;

import java.util.Comparator;

/**
 * Classe concreta que implementa interface Comparator de Movimento
 * Usada na camada1 para ordenar os movimentos de uma conta a partir da data dos lançamentos
 * @author Dylan Dirschnabel, Mateus Machado, Nícolas Belesck, Vitória Knihs
 */
public class OrdenarPorData implements Comparator<Movimento>{
     
    @Override
    public int compare(Movimento m1, Movimento m2) {
            if(m1.getData().isBefore(m2.getData())) {
                return -1;
            } else if(m2.getData().isAfter(m2.getData())) {
                return 1;
            } else {
                return 0;
            }
    }
}
