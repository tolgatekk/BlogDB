package com.blog.service;

import com.blog.dto.request.LoginRequestDto;
import com.blog.dto.request.RegisterRequestDto;
import com.blog.dto.request.UserSaveRequestDto;
import com.blog.dto.response.LoginResponseDto;
import com.blog.dto.response.RegisterResponseDto;
import com.blog.entity.User;
import com.blog.exception.BlogDBException;
import com.blog.exception.ErrorType;
import com.blog.mapper.UserMapper;
import com.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private  final UserRepository userRepository;

    public RegisterResponseDto register(RegisterRequestDto dto) {
        User user = null;
        if(!userRepository.existsByEmail(dto.getEmail())){
            if(dto.getPassword().equals(dto.getRePassword())){

                user = UserMapper.INSTANCE.dtoToUser(dto);

                userRepository.save(user);
            }else {
                throw new BlogDBException(ErrorType.INVALID_PASSWORD);
            }
        }else {
            throw new BlogDBException(ErrorType.EMAIL_ALREADY_EXSIST);
        }
        return UserMapper.INSTANCE.userToDto(user);
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if(optionalUser.isPresent()){
            return UserMapper.INSTANCE.userToLoginDto(optionalUser.get());
        }else {
            throw new BlogDBException(ErrorType.USER_NOT_FOUND);
        }
    }
    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {

        return userRepository.findById(id);
    }

    public void update(UserSaveRequestDto dto){
        Optional<User> optionalUser = findById(dto.getId());
        if(optionalUser.isPresent()){
            // Dto verileri ile User setlemesi yapıyoruz
            User user = User.builder()
                    .id(dto.getId())
                    .name(dto.getName())
                    .surname(dto.getSurname())
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .build();
            // Dto verisini User çeviriyoruz
            update(UserMapper.INSTANCE.userToSaveRequestDto(user));
            userRepository.save(user);
        }else{
            throw new BlogDBException(ErrorType.USER_NOT_FOUND);
        }

    }

    public void deleteById(Long id) {
        Optional<User> optionalUser = findById(id);

        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new BlogDBException(ErrorType.USER_NOT_FOUND);
        }
    }
    public List<User>  findAllByName(String name){
         Boolean varMi=userRepository.existsByName(name);
        if (varMi==null){
            return   userRepository.findAllByName(name);
        }else {
            throw new BlogDBException(ErrorType.USER_NOT_FOUND);
        }
    }


    public List<User> findAllByNameStartingWith(String word) {

        String word2=word+"%";
        return userRepository.findAllByNameStartingWith(word);

    }
}
