package com.digital.signage.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author -Ravi Kumar created on 12/14/2022 3:49 PM
 * @project - Digital Sign-edge
 */
public interface BaseService {

    ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex);

}
