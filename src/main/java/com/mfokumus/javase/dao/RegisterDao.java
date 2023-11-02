package com.mfokumus.javase.dao;

import com.mfokumus.javase.dto.RegisterDto;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/*
   Transaction: Create, Delete, Update
   connection.setAutoCommit(false) => Default:True
   connection.commit(); => basarili
   connection.rollback(); => basarisiz
*/

public class RegisterDao implements IDaoGenerics<RegisterDto> , Serializable {
    @Override
    public String speedData(Long id) {
        return null;
    }

    @Override
    public String allDelete() {
        return null;
    }

    ///////////////////////////////////////////////////////
    // CREATE
    //INSERT INTO `cars`.`register` (`nick_name`,`email_address`,`password`,`roles`,`remaining_number`,`is_passive`)
    // VALUES ('computer', 'hamitmizrak@gmail.com', '123456','admin','5','1');
    @Override
    public RegisterDto create(RegisterDto registerDto) {
        try(Connection connection = getInterfaceConnection()){
            //TRANSACTION YAPISINI YAZALIM
            connection.setAutoCommit(false); // Default:true
            String sql ="INSERT INTO `cars`.`register` (`nick_name`,`email_address`,`password`,`roles`,`remaining_number`,`is_passive`)\n" +
                    " VALUES (?, ?, ?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,registerDto.getuNickName());
            preparedStatement.setString(2,registerDto.getuEmailAddress());
            preparedStatement.setString(3,registerDto.getuPassword());
            preparedStatement.setString(4,registerDto.getRolles());
            preparedStatement.setInt(5,registerDto.getRemainingNumber());
            preparedStatement.setBoolean(6,registerDto.getPassive());
            // executeUpdate: create, delete, update için kullanılır.
            int rowsEffected = preparedStatement.executeUpdate();
            // eğer ekleme yapılmamışsa -1 değerini döner
            if (rowsEffected > 0) {
                System.out.println(RegisterDao.class+ " Basarili Ekleme Tamamdir.");
                connection.commit();
            }else {
                System.err.println(RegisterDao.class+ "!!! Basarisiz Ekleme Tamamdir");
                connection.rollback();
            }
            return registerDto; // eğer başarılı ise return registerDto
        }catch (SQLException sql){
            sql.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // FIND
    @Override
    public RegisterDto findById(Long id) {
        return null;
    }

    @Override
    public RegisterDto findByEmail(String email) {
        return null;
    }

    // LIST
    @Override
    public ArrayList<RegisterDto> list() {
        return null;
    }

    // UPDATE
    @Override
    public RegisterDto update(Long id, RegisterDto registerDto) {
        try(Connection connection = getInterfaceConnection()){
            //TRANSACTION YAPISINI YAZALIM
            connection.setAutoCommit(false); // Default:true
            String sql ="UPDATE `cars`.`register` SET `nick_name`=?, `email_address`=?, `password`=?, `roles`=?, `remaining_number`=?, `is_passive`=?" +
                    " WHERE `id` = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,registerDto.getuNickName());
            preparedStatement.setString(2,registerDto.getuEmailAddress());
            preparedStatement.setString(3,registerDto.getuPassword());
            preparedStatement.setString(4,registerDto.getRolles());
            preparedStatement.setInt(5,registerDto.getRemainingNumber());
            preparedStatement.setBoolean(6,registerDto.getPassive());
            preparedStatement.setLong(6,registerDto.getId());
            // executeUpdate: create, delete, update için kullanılır.
            int rowsEffected = preparedStatement.executeUpdate();
            // eğer güncelleme yapılmamışsa -1 değerini döner
            if (rowsEffected > 0) {
                System.out.println(RegisterDao.class+ " Basarili Guncelleme Tamamdir.");
                connection.commit();
            }else {
                System.err.println(RegisterDao.class+ "!!! Basarisiz Guncelleme Tamamdir");
                connection.rollback();
            }
            return registerDto; // eğer başarılı ise return registerDto
        }catch (SQLException sql){
            sql.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    // DELETE
    @Override
    public RegisterDto deleteById(RegisterDto registerDto) {
        try(Connection connection = getInterfaceConnection()){
            //TRANSACTION YAPISINI YAZALIM
            connection.setAutoCommit(false); // Default:true
            String sql ="DELETE FROM `cars`.`register` WHERE (`id` = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,registerDto.getId());
            // executeUpdate: create, delete, update için kullanılır.
            int rowsEffected = preparedStatement.executeUpdate();
            // eğer güncelleme yapılmamışsa -1 değerini döner
            if (rowsEffected > 0) {
                System.out.println(RegisterDao.class+ " Basarili Guncelleme Tamamdir.");
                connection.commit();
            }else {
                System.err.println(RegisterDao.class+ "!!! Basarisiz Guncelleme Tamamdir");
                connection.rollback();
            }
            return registerDto; // eğer başarılı ise return registerDto
        }catch (SQLException sql){
            sql.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}// end class RegisterDao
