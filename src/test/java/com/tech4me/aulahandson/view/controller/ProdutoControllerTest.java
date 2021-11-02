package com.tech4me.aulahandson.view.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech4me.aulahandson.model.Produto;
import com.tech4me.aulahandson.service.ProdutoService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@WebMvcTest
public class ProdutoControllerTest {
    
    @Autowired
    private ProdutoController produtoController;

    @MockBean
    private ProdutoService produtoService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(produtoController).build();
    }
    
    
    @Test
    public void deve_retornar_status_200_ao_listar_produtos() throws Exception{
        List<Produto> produtos = new ArrayList<>();


        when(this.produtoService.obterTodos()).thenReturn(produtos);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/produtos"))
        .andExpect(MockMvcResultMatchers.status().isOk());

    }


    @Test
    public void deve_retornar_produto_por_id() throws Exception{
        Produto p1 = new Produto();
        p1.setId(123);
        p1.setNome("tela de notebook");
        
        when(produtoService.obterPorId(anyInt())).thenReturn(Optional.of(p1));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/produtos/123"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("tela de notebook"));

    }
    @Test
    public void deve_criar_um_produto_por_id() throws Exception{
        Produto p2 = new Produto();
        p2.setId(456);
        p2.setNome("teclado");

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(p2);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/produtos")
        .content(json)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
    }    

    @Test
    public void deve_atualizar_produto_por_id() throws Exception{
        Produto p3 = new Produto();
        p3.setId(456);
        p3.setNome("mouse");

        ObjectMapper map = new ObjectMapper();
        String json = map.writeValueAsString(p3);

        when(this.produtoService.atualizarProduto(anyInt(), any())).thenReturn(p3);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/produtos/{id}", 456)
        .content(json)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(456))
        .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("mouse"));

    }

    @Test
    public void deve_apagar_um_produto_pela_id() throws Exception{

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/produtos/{id}", 456))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
    }




    



}
