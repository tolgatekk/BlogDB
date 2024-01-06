package com.blog.controller;


import com.blog.dto.request.LoginRequestDto;
import com.blog.dto.request.RegisterRequestDto;
import com.blog.dto.request.UserSaveRequestDto;
import com.blog.dto.response.LoginResponseDto;
import com.blog.dto.response.RegisterResponseDto;
import com.blog.entity.User;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.blog.constant.EndPoints.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ROOT+USER)
public class UserController {

    private final UserService userService;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Validated RegisterRequestDto dto){
        return new ResponseEntity(userService.register(dto), HttpStatus.CREATED);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){

        return ResponseEntity.status(HttpStatus.OK).body(userService.login(dto));
    }
    @GetMapping(GETALL)
    public ResponseEntity<List<User>> getAll(){

        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping(FINDBYID)
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id){

        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<String> update(@RequestBody UserSaveRequestDto dto){
        userService.update(dto);
        return ResponseEntity.ok("Kayıt Basarılı") ;
    }
    @DeleteMapping(DELETE)
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
           userService.deleteById(id);
    return ResponseEntity.ok("Silme İşlemi Başarılı");
    }
    @GetMapping(FINDALLBYNAME)
    public ResponseEntity<List<User>>findAllByName(@PathVariable String name){

        return ResponseEntity.ok(userService.findAllByName(name));
    }

    @GetMapping(FINDALLBYSTARTWİTH)
    public ResponseEntity<List<User>>findAllByNameStartingWith(@PathVariable String word){

        return ResponseEntity.ok(userService.findAllByNameStartingWith(word));
    }


}
