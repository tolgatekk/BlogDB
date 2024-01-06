package com.blog.repository;

import com.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllPostByUserId(Long id);

    List<Post> findByTitleIgnoreCase(String title);
    List<Post> findAllByOrderByReleaseDate();

    List<Post>findByCategoryList_Id(Long categoryId);

}
