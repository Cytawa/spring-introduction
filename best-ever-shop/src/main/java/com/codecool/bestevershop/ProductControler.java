package com.codecool.bestevershop;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControler {


    private final List<Product> products = Arrays.asList(
            new Product("But", 1,BigDecimal.valueOf(122)),
            new Product("Skarpeta", 2, BigDecimal.valueOf(10))
    );


    @GetMapping("/{name}")
    public ResponseEntity<Product> findProduct(@PathVariable String name) {

        return ResponseEntity.ok(products.stream()
                .filter(product -> product.getName().equals(name) )
                .findFirst().orElseThrow());
    }

    @PostMapping("/")
    public ResponseEntity<Product> save(@RequestBody Product product) {

        products.add(new Product(product.getName(), product.getId(), product.getPrice())) ;

        return ResponseEntity.ok(product);
    }

    @PatchMapping("/{name}/{id}")
    public ResponseEntity<String> setNameProduct(@PathVariable String name, @PathVariable long id) {


        products.stream()
                .filter(product -> product.getId() == id)
                .findFirst().orElseThrow().setName(name);


        return ResponseEntity.ok("Product name changed");
    }
    @DeleteMapping("/{id}")
    public <List>Product delete(@PathVariable long id) {
        products.remove(products.stream()
                .filter(product -> product.getId()==id)
                .findFirst().orElseThrow());

        return products;
    }




}
