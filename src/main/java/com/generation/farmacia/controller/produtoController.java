package com.generation.farmacia.controller;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.produtoRepository;
import com.generation.farmacia.repository.categoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders= "*")
public class produtoController {
    @Autowired
    private produtoRepository ProdutoRepository;
    @Autowired
    private categoriaRepository CategoriaRepository;
    @GetMapping
    public ResponseEntity<List <Produto>> getAll(){
        return ResponseEntity.ok(ProdutoRepository.findAll());
    }
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Produto>> getByTitulo(@PathVariable String nome) {
        ResponseEntity.ok(ProdutoRepository.findAllByNomeContainingIgnoreCase(nome));
        return null;
    }
    @PostMapping
    public  ResponseEntity<Produto> post(@Valid@RequestBody Produto produto){
        if (CategoriaRepository.existsById(produto.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(ProdutoRepository.save(produto));

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!",null);
    }
    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto) {
        if (ProdutoRepository.existsById(produto.getId())) {
            if (CategoriaRepository.existsById(produto.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK).body(ProdutoRepository.save(produto));

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Produto> postagem = ProdutoRepository.findById(id);
        if (postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        ProdutoRepository.deleteById(id);
    }



}
