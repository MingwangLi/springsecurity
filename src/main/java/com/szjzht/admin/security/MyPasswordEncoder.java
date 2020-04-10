package com.szjzht.admin.security;

import com.szjzht.admin.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
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
        if (StringUtils.isEmpty(rawPassword)) {
            return false;
        }
        String encode = MD5Util.md5(rawPassword.toString());
        if (encode.equals(encodedPassword)) {
            return true;
        }else {
            return false;
        }
    }
}
