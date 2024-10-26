package com.personcs.usercenter.Model.domain.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
@Slf4j
@Data
public class requestRegisterUser implements Serializable {

    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
