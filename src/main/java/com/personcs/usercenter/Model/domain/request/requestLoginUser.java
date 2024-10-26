package com.personcs.usercenter.Model.domain.request;

import lombok.Data;

import java.io.Serializable;
@Data
public class requestLoginUser implements Serializable {
    private String userAccount;
    private String userPassword;
}
