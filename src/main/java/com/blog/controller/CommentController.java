package com.blog.controller;

import com.blog.dto.request.CommentSaveRequestDto;
import com.blog.entity.Comment;
import com.blog.service.CommentService;
import com.blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static com.blog.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+COMMENT)
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    @PostMapping(CREATE)
    public ResponseEntity<String> createComment(@RequestBody CommentSaveRequestDto dto, @PathVariable Long id){
        commentService.createComment(dto,id);
        return ResponseEntity.ok("Kayıt Başarılı");
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Comment>> findAllComment(){
        return ResponseEntity.ok(commentService.findAllComment());
    }
    @GetMapping(FINDALLCOMMENTBYID)
    public ResponseEntity<List<Comment>> findAllCommentByPostId(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findAllCommentByPostId(id));
    }

    @GetMapping(FINDBYID)
    public ResponseEntity<Comment> findById(@PathVariable Long id){
        return ResponseEntity.ok(commentService.findById(id));
    }
    @DeleteMapping(DELETEBYID)
    public ResponseEntity<String> deleteByPostId(@PathVariable Long id){
        commentService.deleteByPostId(id);
        return ResponseEntity.ok("Silme işlemi Basarılı.");
    }


}
