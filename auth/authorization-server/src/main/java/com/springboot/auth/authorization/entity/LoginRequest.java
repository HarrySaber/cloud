package com.springboot.auth.authorization.entity;

import lombok.Data;

/**
 * LoginRequest
 *
 * @author D.jin
 * @date 2019/10/31
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
}
