package com.blog.controller;


import com.blog.dto.request.CategoryDto;
import com.blog.dto.request.CategorySaveRequestDto;
import com.blog.entity.Category;;
import com.blog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.blog.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+CATEGORY)
public class CategoryController {


    private final CategoryService categoryService;

    @PostMapping(CREATE)
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto dto){
        categoryService.createCategory(dto);
        return ResponseEntity.ok("Kayıt Başarılı");
    }

    @GetMapping(GETALL)
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }
    @GetMapping(FINDBYNAME)
    public ResponseEntity<List<Category>> findByNameIgnoreCase(String name){
        return ResponseEntity.ok(categoryService.findByNameIgnoreCase(name));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<Category>> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(CategorySaveRequestDto dto){
      categoryService.update(dto);
     return ResponseEntity.ok("Kayıt Başarılı");
    }

    @DeleteMapping(DELETEBYID)
    public ResponseEntity<String> deleteById(@PathVariable Long id){
      categoryService.deleteById(id);
      return ResponseEntity.ok("Silme işlemi başarılı.");
    }



}
