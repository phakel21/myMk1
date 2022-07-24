package com.Rpg.config.exception.handler;

import com.Rpg.config.exception.DontHaveNameException;
import com.Rpg.config.exception.NotFoundException;
import com.Rpg.config.exception.hero.HeroDontHaveAllDataException;
import com.Rpg.config.exception.hero.HeroDontHaveMyCharacterException;
import com.Rpg.config.exception.myUser.MyUserDontHaveThisHeroException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity handle(NotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DontHaveNameException.class)
    public ResponseEntity handle(DontHaveNameException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = HeroDontHaveMyCharacterException.class)
    public ResponseEntity handle(HeroDontHaveMyCharacterException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = HeroDontHaveAllDataException.class)
    public ResponseEntity handle(HeroDontHaveAllDataException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = MyUserDontHaveThisHeroException.class)
    public ResponseEntity handle(MyUserDontHaveThisHeroException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
