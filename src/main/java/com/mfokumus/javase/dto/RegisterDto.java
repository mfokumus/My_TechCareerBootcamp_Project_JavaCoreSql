package com.mfokumus.javase.dto;

import java.io.Serializable;

//Register
public class RegisterDto extends BaseDto implements Serializable {

    //Serileştirme
    public static final Long serialVersionUID=1L;

    //Variable
    private String uNickName;
    private String uEmailAddress;
    private String uPassword;




    @Override
    public String nowDateAbstract() {
        return null;
    }
}
