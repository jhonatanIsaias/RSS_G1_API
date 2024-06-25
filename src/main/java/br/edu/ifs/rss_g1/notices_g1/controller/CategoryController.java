package br.edu.ifs.rss_g1.notices_g1.controller;


import br.edu.ifs.rss_g1.notices_g1.entity.Category;
import br.edu.ifs.rss_g1.notices_g1.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category categoryCreate = categoryService.saveCategory(category);
        return ResponseEntity.ok().body(categoryCreate);
    }
}
