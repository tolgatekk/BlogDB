package com.blog.mapper;

import com.blog.dto.request.CommentSaveRequestDto;
import com.blog.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentMapper INSTANCE= Mappers.getMapper(CommentMapper.class);

    Comment dtoToComment(CommentSaveRequestDto dto);


}
