package io.acepirit.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundExceptions extends RuntimeException {
    public ProjectNotFoundExceptions(String s) {
        super(s);
    }
}
