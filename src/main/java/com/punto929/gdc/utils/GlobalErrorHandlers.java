package com.punto929.gdc.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalErrorHandlers {
  @ExceptionHandler
  ProblemDetail handle(IllegalStateException ise, HttpServletRequest request) {
    request.getHeaderNames().asIterator().forEachRemaining(System.out::println);
    var pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST.value());
    pd.setDetail(ise.getLocalizedMessage());
    return pd;
  }

  @ExceptionHandler
  ProblemDetail handle(IllegalArgumentException iae) {
    var pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND.value());
    pd.setDetail(iae.getLocalizedMessage());
    return pd;
  }

  @ExceptionHandler
  ProblemDetail handle(UsernameNotFoundException iae) {
    var pd = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED.value());
    pd.setDetail(iae.getLocalizedMessage());
    return pd;
  }
}
