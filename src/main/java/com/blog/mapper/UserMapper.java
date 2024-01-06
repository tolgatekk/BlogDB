package com.blog.mapper;

import com.blog.dto.request.*;
import com.blog.dto.response.LoginResponseDto;
import com.blog.dto.response.RegisterResponseDto;
import com.blog.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    User dtoToUser(RegisterRequestDto dto);

    RegisterResponseDto userToDto(User user);
    LoginResponseDto userToLoginDto(User user);

    User saveRequestDtoToUser(UserSaveRequestDto dto);
    UserSaveRequestDto userToSaveRequestDto(User user);


}
