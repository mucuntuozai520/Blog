package com.clf.blog.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5SaltEncode {

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String encBase64(String str){
        return Base64.encodeToString(str.getBytes());
    }

    /**
     * base64解密
     * @param str
     * @return
     */
    public static String decBase64(String str){
        return Base64.decodeToString(str);
    }

    /**
     * md5加密
     *
     * @param str  密码
     * @param salt 盐值（佐料），建议值是唯一的
     * @return
     */
    public static String md5Hash(String str, String salt) {
        return new Md5Hash(str, salt).toString();
    }

    /**
     * md5加密
     *
     * @param str   密码
     * @param salt  佐料
     * @param count 加密次数
     * @return
     */
    public static String md5Hash(String str, String salt, int count) {
        return new Md5Hash(str, salt, count).toString();
    }


    public static void main(String[] args) {
        String pwd = "123456";
        System.out.println("Base64加密："+encBase64(pwd));
        System.out.println("Base64解密："+decBase64(encBase64(pwd)));
        System.out.println("md5加密1：" + md5Hash(pwd, "admin"));
        System.out.println("md5加密2：" + md5Hash(pwd, "admin", 3));
    }

}
