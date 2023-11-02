package com.mfokumus.javase.service;

import com.mfokumus.javase.controller.RegisterController;
import com.mfokumus.javase.dao.RegisterDao;
import com.mfokumus.javase.dto.RegisterDto;
import com.mfokumus.javase.files.FilePathData;
import com.mfokumus.javase.roles.ERoles;

import java.util.Scanner;

public class RegisterLoginServices {

    // Injection
    private RegisterController registerController = new RegisterController();
    private FilePathData filePathData = new FilePathData();

    // Eğer sistemde ilgili email ile kullanıcı varsa sisteme giriş yapsın
    // Eğer sistemde ilgili email yoksa register olsun

    private String[] allRoles() {
      /*  for ( String temp :ERoles.valueOf() ) {
        }*/
        return null;
    }

    // Register
    private RegisterDto register(){
        Scanner klavye = new Scanner(System.in);
        RegisterDto registerDto = new RegisterDto();
        String uNickname, uEmailAddress, uPassword, rolles;
        Long remainingNumber;
        Boolean isPassive;
        System.out.println("\n###REGISTER SAYSASINA HOSGELDINIZ");
        System.out.println("Takma adınızı giriniz");
        uNickname = klavye.nextLine();
        System.out.println("Emailinizi giriniz");
        uEmailAddress = klavye.nextLine();
        System.out.println("Sifrenizi giriniz");
        uPassword = klavye.nextLine();
        // default rol user olacak
        rolles = ERoles.USER.getValue().toString();
        remainingNumber = 5L;
        isPassive = true;
        ///////////////////
        registerDto.setuNickName(uNickname);
        registerDto.setuEmailAddress(uEmailAddress);
        registerDto.setuPassword(uPassword);
        registerDto.setRolles(rolles);
        registerDto.setRemainingNumber(remainingNumber);
        registerDto.setPassive(isPassive);
        // CREATE
        registerController.create(registerDto);
        return registerDto;
    }

    // LOGIN
    public RegisterDto login() {
        Scanner klavye = new Scanner(System.in);
        RegisterDto registerDto = new RegisterDto();
        String uEmailAddress, uPassword;
        Long remaingNumber = 0L;
        System.out.println("\n###LOGIN SAYSASINA HOSGELDINIZ");
        System.out.println("Emailinizi giriniz");
        uEmailAddress = klavye.nextLine();
        System.out.println("Sifrenizi giriniz");
        uPassword = klavye.nextLine();

        // Email Find
        RegisterDto registerEmailFind = registerController.findByEmail(uEmailAddress);
        // Kullanıcı yoksa kayıt olsun ve login sayfasına geri donsun.
        if (registerEmailFind == null) {
            // eğer kullanıcı yoksa kayıt olsun
            register();
            // Kayıt olduktan sonra Login sayfasına geri dön
            login();
        } else {
            // Eğer Kullanıcı Pasifse giris yapmasın
            if (registerEmailFind.getPassive() == false) {
                System.out.println("Üyeliğiniz Pasif edilmiştir sisteme giriş yapamazsınız");
                System.out.println("Lütfen admin'e başvurunuz.");
                System.exit(0);
            }

            // Database kaydedilmis decode edilmis sifre karsilastirmak
            RegisterDao registerDao=new RegisterDao();
            String fistValue=uPassword;
            String rawPassword=registerDao.generatebCryptPasswordEncoder(fistValue);
            boolean result=registerDao.matchbCryptPassword(fistValue,registerEmailFind.getuPassword());

            // Eğer kullanıcı varsa sisteme giriş yapsın    uPassword.equals(registerEmailFind.getuPassword()
            if (uEmailAddress.equals(registerEmailFind.getuEmailAddress()) && registerDao.matchbCryptPassword(fistValue,registerEmailFind.getuPassword()) ) {
                adminProcess(registerEmailFind);
            } else {
                // Kullanıcının kalan hakkı
                remaingNumber = registerEmailFind.getRemainingNumber();
                remaingNumber--;
                registerEmailFind.setRemainingNumber(remaingNumber);
                System.out.println("Kalan Hakkınız: " + registerEmailFind.getRemainingNumber());
                System.out.println("Sifreniz veya Emailiniz yanlış girdiniz");
                // Kalan Hak Database Eksilt
                registerController.updateRemaing(remaingNumber, registerEmailFind);
                // File Loglama yapsın
                filePathData.logFileWriter(uEmailAddress, uPassword);
                // Sisteme giriş hakkım kalmazsa
                if (remaingNumber == 0) {
                    System.out.println("Giriş hakkınız kalmadı Hesanız Bloke oldu");
                    System.out.println("Admine Başvuru yapınız");
                    System.exit(0);
                } else if (remaingNumber >= 1) {
                    login();
                }
            } //end else
        }
        return registerDto;
    }

    ///////////////////////////////////////////////////////////////////////////////////////

}


