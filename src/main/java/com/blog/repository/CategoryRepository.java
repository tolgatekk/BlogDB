package com.blog.repository;

import com.blog.entity.Category;
import com.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByNameIgnoreCase(String name);

    Boolean existsByName(String name);


}
