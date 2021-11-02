package com.tech4me.aulahandson.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest
public class ProdutoTest {

    @TestConfiguration
    static class ProdutoConfiguração{
        @Bean
        public Produto produto(){
            return new Produto();
        }
    }
    
    @Autowired
    Produto produto;

    
    @Test
    public void deve_retornar_o_valor_total_do_produto_de_1000(){
        Double quantidade = 10.0;
        Double valor = 100.0;
        Double valorTotalEsperado = 1000.0;
        

        Double valorTotalEncontrado = produto.valorTotal(quantidade, valor);

        Assertions.assertEquals(valorTotalEsperado, valorTotalEncontrado);

    }
}
