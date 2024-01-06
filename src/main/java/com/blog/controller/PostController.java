package com.blog.controller;

import com.blog.dto.request.PostRequestDto;
import com.blog.dto.request.PostSaveRequestDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.repository.CategoryRepository;
import com.blog.service.PostService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.blog.constant.EndPoints.*;

@RestController
@RequestMapping(ROOT+POST)
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private CategoryRepository categoryRepository;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
        this.categoryRepository=categoryRepository;
    }
    @PostMapping(CREATE)
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(postService.createPost(dto, id));
    }
    @GetMapping(FINDALL)
    public ResponseEntity<List<Post>> findAllPost() {
        return ResponseEntity.ok(postService.findAllPost());
    }
    @GetMapping(FINDALLPOSTBYUSERID)
    public ResponseEntity<List<Post>> findAllPostByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findAllPostByUserId(id));
    }
    @GetMapping(FINDBYID)
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }
    @DeleteMapping(DELETEBYID)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.deleteById(id));
    }
    @PutMapping(UPDATE)
    public ResponseEntity<String> update(@RequestBody PostSaveRequestDto dto) {

        postService.update(dto);
        return ResponseEntity.ok("Kayıt Basarılı");
    }
    @GetMapping(FINDBYTITLE)
    public ResponseEntity<List<Post>> findByTitleIgnoreCase(@PathVariable String title) {
        return ResponseEntity.ok(postService.findByTitleIgnoreCase(title));
    }
    @GetMapping(FINDALLBYORDERBYRELEASEDATE)
    public ResponseEntity<List<Post>> findAllByOrderByReleaseDate() {
        return ResponseEntity.ok(postService.findAllByOrderByReleaseDate());
    }
    @GetMapping(FINDBYCATEGORYLISTID)
    public ResponseEntity<List<Post>> findByCategoryList_Id(Long categoryId){
        return ResponseEntity.ok(postService.findByCategoryList_Id(categoryId));
    }
}

