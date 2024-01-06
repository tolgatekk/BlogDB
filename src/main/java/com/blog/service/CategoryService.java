package com.blog.service;

import com.blog.dto.request.CategoryDto;
import com.blog.dto.request.CategorySaveRequestDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.exception.BlogDBException;
import com.blog.exception.ErrorType;
import com.blog.mapper.CategoryMapper;
import com.blog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private  final CategoryRepository categoryRepository;


    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public void createCategory(CategoryDto dto){
        Category category = CategoryMapper.INSTANCE.categoryDtoToCategory(dto);
        categoryRepository.save(category);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public void update(CategorySaveRequestDto dto){
        Optional<Category> optionalCategory =findById(dto.getId());
        if(optionalCategory.isPresent()){
            Category category = Category.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .explanation(dto.getExplanation())
                    .build();
            categoryRepository.save(category);
        }else{
            throw new BlogDBException(ErrorType.CATEGORY_NOT_FOUND);
        }
    }
    public void deleteById(Long id){
        Optional<Category> optionalCategory = findById(id);
        if(optionalCategory.isPresent()){
            categoryRepository.deleteById(id);
        } else{
            throw new BlogDBException((ErrorType.CATEGORY_NOT_FOUND));
        }
    }
    public List<Category> findByNameIgnoreCase(String name) {
        Boolean varMi = categoryRepository.existsByName(name);
        if(varMi){
            return categoryRepository.findByNameIgnoreCase(name);
        }else{
            throw new BlogDBException(ErrorType.CATEGORY_NOT_FOUND);
        }

    }

}
