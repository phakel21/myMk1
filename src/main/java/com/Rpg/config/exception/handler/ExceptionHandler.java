package com.Rpg.config.exception.handler;

import com.Rpg.config.exception.*;
import com.Rpg.config.exception.hero.HeroDontHaveMyCharacterException;
import com.Rpg.config.exception.image.ImageNotFoundException;
import com.Rpg.config.exception.location.LocationExistException;
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

    @org.springframework.web.bind.annotation.ExceptionHandler(value = MyUserDontHaveThisHeroException.class)
    public ResponseEntity handle(MyUserDontHaveThisHeroException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ImageNotFoundException.class)
    public ResponseEntity handle(ImageNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExistException.class)
    public ResponseEntity handle(ExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DontHaveHpException.class)
    public ResponseEntity handle(DontHaveHpException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DontHaveMpException.class)
    public ResponseEntity handle(DontHaveMpException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DontHavePowerException.class)
    public ResponseEntity handle(DontHavePowerException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = DontHaveLocationException.class)
    public ResponseEntity handle(DontHaveLocationException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }



}
