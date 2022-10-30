package com.varketplace.category.adapter.in.api;

import com.varketplace.category.CreateCategoryUseCase;
import com.varketplace.category.CreateCategoryUseCase.CreateCategoryCommand;
import com.varketplace.category.FindCategoryUseCase;
import com.varketplace.category.FindCategoryUseCase.FindCategoryByIdCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(path = CategoryController.PATH)
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {

    public static final String PATH = "/categories";

    private final CreateCategoryUseCase createCategory;
    private final FindCategoryUseCase findCategory;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateCategoryCommand command) {
        var id = createCategory.handle(command);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<FindCategoryUseCase.CategoryDto> getById(@RequestParam UUID id) {
        var category = findCategory.handle(FindCategoryByIdCommand.of(id));

        return ResponseEntity.ok(category);
    }

}
