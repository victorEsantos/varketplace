package com.varketplace.product.adapter.in.api;

import com.varketplace.product.CreateProductUseCase;
import com.varketplace.product.CreateProductUseCase.CreateProductCommand;
import com.varketplace.product.InsertCaregoryInProductUseCase;
import com.varketplace.product.InsertCaregoryInProductUseCase.InsertCaregoryOnProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = ProductController.PATH)
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    public static final String PATH = "/products";

    private final CreateProductUseCase createProduct;
    private final InsertCaregoryInProductUseCase insertCaregoryInProduct;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateProductCommand command) {
        var id = createProduct.handle(command);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping(path = "/{id}/addCategory")
    public ResponseEntity addCategory(@PathVariable UUID id,
                                      @Valid @RequestBody InsertCaregoryOnProductCommand command) {

        insertCaregoryInProduct.handle(command, id);

        return ResponseEntity.ok().build();
    }
}
