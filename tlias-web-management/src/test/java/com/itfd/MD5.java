package com.itfd;

import ch.qos.logback.core.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.util.DigestUtils;

public class MD5 {
    @Test
    public void testMD5(){
        String password = "123456";
        DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
