package io.acepirit.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsrnameAlreadyExistException extends RuntimeException {

//   public UsrnameAlreadyExistException(String message){
//        super(message);
//    }
public UsrnameAlreadyExistException(String message){
    super(message);
}

}
