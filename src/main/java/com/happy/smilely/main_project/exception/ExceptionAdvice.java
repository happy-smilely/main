package com.happy.smilely.main_project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.happy.smilely.main_project.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(BaseRuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto baseRuntimException(BaseRuntimeException e) {
        return ResponseDto.setFail(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}

