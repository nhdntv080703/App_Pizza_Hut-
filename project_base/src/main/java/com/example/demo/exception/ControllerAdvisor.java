package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> handlerArithmeticException(ArithmeticException ae, WebRequest webRequest){
        ErrorResponseBean responseBean = new ErrorResponseBean();
        responseBean.setError(ae.getMessage());
        List<String> details = new ArrayList<>();
        details.add("anh gì ơi lỗi rồi, sao chia cho 0 được");
        responseBean.setDetails(details);
        return new ResponseEntity<>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<?> handlerFieldRequiredException(FieldRequiredException ae, WebRequest webRequest){
        ErrorResponseBean responseBean = new ErrorResponseBean();
        responseBean.setError(ae.getMessage());
        List<String> details = new ArrayList<>();
        responseBean.setDetails(details);
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(NotFoundException ae, WebRequest webRequest){
        ErrorResponseBean responseBean = new ErrorResponseBean();
        responseBean.setError(ae.getMessage());
        List<String> details = new ArrayList<>();
        details.add("không có dữ liệu trong cơ sở dữ liệu của bạn");
        responseBean.setDetails(details);
        return new ResponseEntity<>(responseBean, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> handlerAlreadyExistsException(AlreadyExistsException ae, WebRequest webRequest){
        ErrorResponseBean responseBean = new ErrorResponseBean();
        responseBean.setError(ae.getMessage());
        List<String> details = new ArrayList<>();
        details.add(" tài khoản đã tồn tại, không thể thêm");
        responseBean.setDetails(details);
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForeignKeyConstraintException.class)
    public ResponseEntity<?> handlerForeignKeyConstraintException(ForeignKeyConstraintException ae, WebRequest webRequest){
        ErrorResponseBean responseBean = new ErrorResponseBean();
        responseBean.setError(ae.getMessage());
        List<String> details = new ArrayList<>();
        details.add("Đang dính khóa ngoại bảng được tham chiếu, không thể thao tác");
        responseBean.setDetails(details);
        return new ResponseEntity<>(responseBean, HttpStatus.BAD_REQUEST);
    }
}
