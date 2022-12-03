package com.varketplace.category.adapter.in.api;

import com.varketplace.category.CreateCategoryUseCase;
import com.varketplace.category.CreateCategoryUseCase.CreateCategoryCommand;
import com.varketplace.category.FindCategoryUseCase;
import com.varketplace.category.FindCategoryUseCase.CategoryDto;
import com.varketplace.category.FindCategoryUseCase.FindCategoryByIdCommand;
import com.varketplace.category.UpdateCategoryUseCase;
import com.varketplace.category.UpdateCategoryUseCase.UpdateCategoryCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final UpdateCategoryUseCase updateCategory;
    private final FindCategoryUseCase findCategory;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateCategoryCommand command) {
        var id = createCategory.handle(command);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> update(@Valid @RequestBody UpdateCategoryCommand command) {
        command.setId(command.getId());
        updateCategory.handle(command);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CategoryDto> getById(@RequestParam UUID id) {
        var category = findCategory.handle(FindCategoryByIdCommand.of(id));

        return ResponseEntity.ok(category);
    }

    @GetMapping
    public ResponseEntity<Page<CategoryDto>> getAll(@PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        var products = findCategory.handle(FindCategoryUseCase.FindAllCategoryCommand.of(pageable));

        return ResponseEntity.ok(products);
    }

}
