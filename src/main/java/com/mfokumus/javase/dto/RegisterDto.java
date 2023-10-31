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
    private int remainingNumber; // Kullanıcı kalan hak
    private Boolean isPassive;
    private String rolles;
    private int counter;

    // Constructor (parametresiz)
    public RegisterDto() {
    }

    // Constructor (parametreli)
    public RegisterDto(String uNickName, String uEmailAddress, String uPassword, int remainingNumber, Boolean isPassive, String rolles) {
        this.uNickName = uNickName;
        this.uEmailAddress = uEmailAddress;
        this.uPassword = uPassword;
        this.remainingNumber = remainingNumber;
        this.isPassive = isPassive;
        this.rolles = rolles;
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "uNickName='" + uNickName + '\'' +
                ", uEmailAddress='" + uEmailAddress + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", remainingNumber=" + remainingNumber +
                ", isPassive=" + isPassive +
                ", rolles='" + rolles + '\'' +
                ", counter=" + counter +
                "} " + super.toString();
    }

    @Override
    public String nowDateAbstract() {
        return null;
    }

    // GETTER AND SETTER
    public String getuNickName() {
        return uNickName;
    }

    public void setuNickName(String uNickName) {
        this.uNickName = uNickName;
    }

    public String getuEmailAddress() {
        return uEmailAddress;
    }

    public void setuEmailAddress(String uEmailAddress) {
        this.uEmailAddress = uEmailAddress;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public int getRemainingNumber() {
        return remainingNumber;
    }

    public void setRemainingNumber(int remainingNumber) {
        this.remainingNumber = remainingNumber;
    }

    public Boolean getPassive() {
        return isPassive;
    }

    public void setPassive(Boolean passive) {
        isPassive = passive;
    }

    public String getRolles() {
        return rolles;
    }

    public void setRolles(String rolles) {
        this.rolles = rolles;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
