package com.blog.service;

import com.blog.dto.request.PostRequestDto;
import com.blog.dto.request.PostSaveRequestDto;
import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.BlogDBException;
import com.blog.exception.ErrorType;
import com.blog.mapper.CategoryMapper;
import com.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private Post post;
    private Category category;

    public PostService(PostRepository postRepository, UserService userService,CategoryService categoryService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.categoryService=categoryService;
        this.post=new Post();
        this.category=new Category();
    }

    public String createPost(PostRequestDto dto,Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
          // Dto dan gelen verileri Post setliyoruz.
            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .categoryList(List.of(category= CategoryMapper.INSTANCE.categoryDtoToCategory(dto.getCategoryDto())))
                    .user(user)
                    .build();

            user.getPostList().add(post);
            Post createdPost = postRepository.save(post);

            return "Post atma işlemi tamamlandı.Post ID: " + createdPost.getId();

        } else {
            throw new BlogDBException(ErrorType.USER_NOT_FOUND);
        }
    }

    public List<Post> findAllPost() {

        return postRepository.findAll();
    }

    public List<Post> findAllPostByUserId(Long id) {
        Optional<User>optionalUser=userService.findById(id);
        if (optionalUser.isPresent()){

            return postRepository.findAllPostByUserId(id);
        }else {
            throw new BlogDBException(ErrorType.USER_NOT_FOUND);
        }

    }
    public Post findById(Long id) {
        Optional<Post> optionalPost=postRepository.findById(id);
        if (optionalPost.isPresent()){
            return optionalPost.get();
        }else {
            throw new BlogDBException(ErrorType.USER_NOT_FOUND);
        }

    }
    public String deleteById(Long id) {

        Optional<Post> optionalPost = Optional.ofNullable(findById(id));

        if (optionalPost.isPresent()) {
            postRepository.deleteById(id);
            return "Silme İşlemi tamamlandı";
        } else {
            throw new BlogDBException(ErrorType.POST_NOT_FOUND);
        }
    }
    public void update(PostSaveRequestDto dto){
        Optional<Post> optionalPost = Optional.ofNullable(findById(dto.getId()));
        Optional<User> optionalUser = userService.findById(dto.getUserId());
        if(optionalPost.isPresent()){
            Post post = Post.builder()
                    .id(dto.getId())
                    .user(optionalUser.get())
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .user(optionalUser.get())
                    .build();

            postRepository.save(post);

        }else{
            throw new BlogDBException(ErrorType.POST_NOT_FOUND);
        }

    }
    public List<Post> findByTitleIgnoreCase(String title) {

        return postRepository.findByTitleIgnoreCase(title);
    }
    public List<Post> findAllByOrderByReleaseDate() {

        return postRepository.findAllByOrderByReleaseDate();
    }
    public List<Post> findByCategoryList_Id(Long categoryId) {

        return postRepository.findByCategoryList_Id(categoryId);
    }
}



