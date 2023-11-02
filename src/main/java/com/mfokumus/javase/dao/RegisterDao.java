package com.mfokumus.javase.dao;

import com.mfokumus.javase.dto.RegisterDto;

import java.io.Serializable;
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
    @Override
    public RegisterDto create(RegisterDto registerDto) {
        try(){


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
        return null;
    }

    // DELETE
    @Override
    public RegisterDto deleteById(RegisterDto registerDto) {
        return null;
    }
}// end class RegisterDao
