package com.clf.blog.util;


import org.apache.commons.codec.digest.DigestUtils;

public class Md5Encode {

    //md5加密
    public static String md5Encode(String password) {
        byte[] input = password.getBytes();
        String md5Hex = DigestUtils.md5Hex(input);

        StringBuffer sb = new StringBuffer(md5Hex);
        sb.insert(0, "m");
        sb.insert((sb.length()) / 2, "d");
        sb.append("5");
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "2534800363";
        str = md5Encode(str);
        System.out.println("加密后的结果：" + str);
    }

}
