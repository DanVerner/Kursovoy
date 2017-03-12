package com.educsystem.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Denis on 25.02.2017.
 */
public class Crypt {
    private static String genPass = null;

    public Crypt(String genPass) {
        this.genPass = genPass;
    }

    public String getGenPass() {
        return genPass;
    }

    public void setGenPass(String genPass) {
        this.genPass = genPass;
    }

    public static String crypting(String password){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            genPass = sb.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return genPass;
    }
}
