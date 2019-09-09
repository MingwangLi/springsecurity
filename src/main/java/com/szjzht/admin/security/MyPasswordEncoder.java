package com.szjzht.admin.security;

import com.mysql.jdbc.StringUtils;
import com.szjzht.admin.utils.MD5Util;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Auther: mayn
 * @Date: 2019/9/9 12:49
 * @Description:
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {



    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.md5(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // TODO: 2019/9/9 空判断
        String encode = MD5Util.md5(rawPassword.toString());
        if (encode.equals(encodedPassword)) {
            return true;
        }else {
            return false;
        }
    }
}
