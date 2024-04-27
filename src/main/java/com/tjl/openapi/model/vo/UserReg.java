package com.tjl.openapi.model.vo;

import lombok.Data;

/**
 * @author Tang
 * @createDate 2024/4/27
 * 用户注册
 */
@Data
public class UserReg {

    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
