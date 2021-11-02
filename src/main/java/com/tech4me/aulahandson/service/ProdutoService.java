package com.tech4me.aulahandson.service;

import java.util.List;
import java.util.Optional;

import com.tech4me.aulahandson.model.Produto;
import com.tech4me.aulahandson.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository repositorioProduto;

    public Produto adicionarProduto(Produto produto){
        return repositorioProduto.save(produto);
    }

    public List<Produto> obterTodos(){
        return repositorioProduto.findAll();
    }    

    public Optional<Produto> obterPorId(Integer id){
        return repositorioProduto.findById(id);
    }    

    public Produto atualizarProduto (Integer id, Produto produto){
        Optional<Produto> produtoEncontrado = repositorioProduto.findById(id);
        produtoEncontrado.get().setId(id);
        return repositorioProduto.save(produtoEncontrado.get());
    }

    public void deletar(Integer id){
        repositorioProduto.deleteById(id);
    }

    
    
}
