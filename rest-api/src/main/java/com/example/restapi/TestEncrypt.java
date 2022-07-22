package com.example.restapi;

import org.mindrot.jbcrypt.BCrypt;

public class TestEncrypt {

    /*BCrypt.hashpw("ryan", BCrypt.gensalt(10));*/
    public static void main(String[] args) {
        String hasil = BCrypt.hashpw("ryan", BCrypt.gensalt(10));
        System.out.println(hasil);
    }

}
