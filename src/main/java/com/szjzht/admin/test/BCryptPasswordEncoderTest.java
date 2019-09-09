package com.szjzht.admin.test;

import com.szjzht.admin.utils.MD5Util;



/**
 * @Auther: mayn
 * @Date: 2019/9/9 11:50
 * @Description:
 */
public class BCryptPasswordEncoderTest {

    public static void main(String[] args) {
        String szjzhtadmin = MD5Util.md5("123456");
        System.out.println(szjzhtadmin);
    }
}
