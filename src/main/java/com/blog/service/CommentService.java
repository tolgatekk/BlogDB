package com.blog.service;

import com.blog.dto.request.CommentSaveRequestDto;
import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.BlogDBException;
import com.blog.exception.ErrorType;
import com.blog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private  final CommentRepository commentRepository;
    private  final PostService postService;

    public String createComment(CommentSaveRequestDto dto, Long id){

        Optional<Post>optionalPost= Optional.ofNullable(postService.findById(id));

        if (optionalPost.isPresent()){
            Post post=optionalPost.get();
            Comment comment=Comment.builder()
                    .commenter(dto.getCommenter())
                    .content(dto.getContent())
                    .post(post)
                    .build();

            post.getCommentList().add(comment);
            Comment createdComment=commentRepository.save(comment);

            return "Yorum atma işlemi tamamlandı.Yorum ID: " + createdComment.getId();

        } else {
           throw  new BlogDBException(ErrorType.POST_NOT_FOUND);
        }

        }
    public List<Comment> findAllComment() {

        return commentRepository.findAll();
    }

    public List<Comment> findAllCommentByPostId(Long id) {
        return commentRepository.findAllCommentByPostId(id);
    }

    public Comment findById(Long id) {
        Optional<Comment> optionalComment=commentRepository.findById(id);
        if (optionalComment.isPresent()){
            return optionalComment.get();
        }else {
            throw new BlogDBException(ErrorType.COMMENT_NOT_FOUND);
        }
    }

    public void deleteByPostId(Long id) {
        Optional<Comment> optionalComment = Optional.ofNullable(findById(id));
        if (optionalComment.isPresent()) {
            commentRepository.deleteByPostId(id);
        } else {
            throw new BlogDBException(ErrorType.COMMENT_NOT_FOUND);
        }
    }
}



