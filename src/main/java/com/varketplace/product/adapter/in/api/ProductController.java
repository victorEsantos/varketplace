package com.varketplace.product.adapter.in.api;

import com.varketplace.product.CreateProductUseCase;
import com.varketplace.product.CreateProductUseCase.CreateProductCommand;
import com.varketplace.product.FindProductUseCase;
import com.varketplace.product.FindProductUseCase.FindProductByIdCommand;
import com.varketplace.product.FindProductUseCase.ProductDto;
import com.varketplace.product.InsertCaregoryInProductUseCase;
import com.varketplace.product.InsertCaregoryInProductUseCase.InsertCaregoryOnProductCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final FindProductUseCase findProduct;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateProductCommand command) {
        var id = createProduct.handle(command);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping(path = "/{id}/addCategory")
    public ResponseEntity<Void> addCategory(@PathVariable UUID id,
                                      @Valid @RequestBody InsertCaregoryOnProductCommand command) {

        insertCaregoryInProduct.handle(command, id);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getById(@RequestParam UUID id) {
        var category = findProduct.handle(FindProductByIdCommand.of(id));

        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getAll(@PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        var products = findProduct.handle(FindProductUseCase.FindAllProductCommand.of(pageable));

        return ResponseEntity.ok(products);
    }
}
