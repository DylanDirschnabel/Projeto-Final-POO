/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package camada2;

import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class ProjetoFinalTestes {
    
    public ProjetoFinalTestes() {
    }

    // Métodos de teste do projeto final
    // Vale ressaltar que muitos desses métodos trabalham com arquivo
    // Portanto caso os arquivos.csv sejam alterados, o resultado também pode mudar!
    
    
    // Método de teste da função "incluirReceita" da classe "Conta"
    // Adicionalmente, testa o método de "arquivarInformacoes"
    @Test
    public void testeIncluirRececita() {
        ListaContas lista = new ListaContas();
        lista.importarContas();
        
        LocalDate date = LocalDate.now();
        CategoriaReceitas categoria = CategoriaReceitas.DECIMO_TERCEIRO;
        Receita receitaTeste = new Receita(1200, date, categoria);
        
        lista.getContas().get(0).incluirReceita(receitaTeste);
        Double resultado = lista.getContas().get(0).getMovimentos().getLast().getValor();
        Double esperado = 1200.0;
        assertEquals(resultado, esperado);
    }
    
    // Método de teste da função "incluirDespesa" da classe "Conta"
    // Adicionalmente, testa o método de "arquivarInformacoes"
    @Test
    public void testeIncluirDespesa() {
        ListaContas lista = new ListaContas();
        lista.importarContas();
        
        LocalDate date = LocalDate.now();
        CategoriaDespesas categoria = CategoriaDespesas.SAUDE;
        Despesa despesaTeste = new Despesa(300, date, categoria);
        
        lista.getContas().get(1).incluirDespesa(despesaTeste);
        Double resultado = lista.getContas().get(1).getMovimentos().getLast().getValor();
        Double esperado = 300.0;
        assertEquals(resultado, esperado);
    }
    
    // Método de teste das funções "getSaldo" e "getSaldoTotal" da classe "Conta"
    @Test
    public void testeGetSaldo() {
        ListaContas lista = new ListaContas();
        lista.importarContas();
        
        lista.getContas().get(3).recuperarInformacoes();
        
        Double resultado1 = lista.getContas().get(3).getSaldo();
        Double esperado1 = -255.43;
        
        Double resultado2 = lista.getContas().get(3).getSaldoTotal();
        Double esperado2 = 3744.57;
        
        assertEquals(resultado1, esperado1);
        assertEquals(resultado2, esperado2);
    }
    
    // Método de teste da função "getNomeArquivo" da classe "Conta"
    @Test
    public void testeGetNomeArquivo() {
        ListaContas lista = new ListaContas();
        lista.importarContas();
        
        lista.getContas().get(0).recuperarInformacoes();
        
        String resultado = lista.getContas().get(0).getNomeArquivo();
        String esperado = "111.csv";
        
        assertEquals(resultado, esperado);
    }
    
    // Método de teste da função "getSize" da classe "Conta"
    @Test
    public void testeGetSizeConta() {
        ListaContas lista = new ListaContas();
        lista.importarContas();
        
        lista.getContas().get(0).recuperarInformacoes();
        
        int contagem = 0;
        for(Movimento movimento : lista.getContas().get(0).getMovimentos()) {
            contagem++;
        }
        
        int resultado = lista.getContas().get(0).getSize();
        int esperado = contagem;
        
        assertEquals(resultado, esperado);
    }
    
    // Método de teste da função "arquivarConta" da classe "Conta"
    // Método testa juntamente a função "recuperarInformacoes"
    @Test
    public void testeArquivarConta() {
        
        Conta conta = new Conta();
        conta.setNumeroConta(007);
        conta.setTitular("Testando");
        conta.arquivarConta();
        
        ListaContas lista = new ListaContas();
        lista.importarContas();
        
        lista.getContas().getLast().recuperarInformacoes();
        
        String esperado = "Testando";
        String resultado = lista.getContas().getLast().getTitular();
        
        assertEquals(esperado, resultado);

    }
    
    // Teste das funções da classe "ListaContas"
    // função "incluirConta" inclusa na função "importarContas"
    @Test
    public void testeListaContas() {
        
        ListaContas lista = new ListaContas();
        lista.importarContas();
        
        int contador  = 0;
        for(Conta conta : lista.getContas()) {
            contador++;
        }
        
        int resultado = lista.getSize();
        
        assertEquals(contador, resultado);

    }
    
    
   
    
    
    
    
    
    
    
}
