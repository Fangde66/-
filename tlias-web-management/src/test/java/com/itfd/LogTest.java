package com.itfd;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {

    // 定义日志记录对象
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);
    @Test
    public void testLog(){
        log.debug("start caculate:");

        int a = 520;

        log.info("result:" + a);
        log.debug("End");
    }
}
