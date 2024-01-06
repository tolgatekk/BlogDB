package com.blog.mapper;


import com.blog.dto.request.PostRequestDto;
import com.blog.dto.request.PostSaveRequestDto;
import com.blog.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "userId", target = "user.id")
    Post postDTOToPost(PostRequestDto postDTO);

    @Mapping(source = "user.id", target = "userId")
    PostRequestDto postToPostDTO(Post post);
    @Mapping(source = "user.id", target = "userId")
    PostSaveRequestDto postToSaveRequestDto(Post post);
    @Mapping(source = "userId", target = "user.id")
    Post saveRequestDtoToPost(PostSaveRequestDto dto);

}
