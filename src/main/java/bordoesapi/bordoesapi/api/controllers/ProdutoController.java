package bordoesapi.bordoesapi.api.controllers;

import bordoesapi.bordoesapi.aplicacao.dto.ClienteDto;
import bordoesapi.bordoesapi.aplicacao.dto.ProdutoDto;
import bordoesapi.bordoesapi.aplicacao.servicos.ClienteService;
import bordoesapi.bordoesapi.aplicacao.servicos.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getAll(){
        List<ProdutoDto> produtos = produtoService.getAll();
        return ResponseEntity.ok(produtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> getById(@PathVariable int id){
        ProdutoDto produto = produtoService.getById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> save(@RequestBody ProdutoDto produtoDto, UriComponentsBuilder uriBuilder){
        ProdutoDto produto = produtoService.save(produtoDto);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(produto.id()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @PutMapping
    public ResponseEntity<ProdutoDto> update(@RequestBody ProdutoDto produtoDto){
        ProdutoDto produto = produtoService.update(produtoDto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
