package com.zerobase.domain.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Aes256UtilTest {

    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("Hello World");
        assertEquals(Aes256Util.decrypt(encrypt), "Hello World");
        //given
        //when
        //then
    }
}
