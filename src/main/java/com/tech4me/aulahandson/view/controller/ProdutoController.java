package com.tech4me.aulahandson.view.controller;

import java.util.List;
import java.util.Optional;

import com.tech4me.aulahandson.model.Produto;
import com.tech4me.aulahandson.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

@Autowired
ProdutoService servicoProduto;

    @GetMapping
    public ResponseEntity<List<Produto>> obterTodos(){
        return new ResponseEntity<>(servicoProduto.obterTodos(), HttpStatus.OK);
    }

   
   @GetMapping("/{id}")
    public ResponseEntity<Optional<Produto>> obterPorId(@PathVariable Integer id){
        Optional <Produto> produtoEncontrado = servicoProduto.obterPorId(id);
        return new ResponseEntity<>(produtoEncontrado, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto){
        return new ResponseEntity<>(servicoProduto.adicionarProduto(produto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produto){
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        servicoProduto.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
