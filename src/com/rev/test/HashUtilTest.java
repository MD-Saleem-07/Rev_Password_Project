package com.rev.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.rev.util.HashUtil;

public class HashUtilTest {

    @Test
    public void testHash() {
        String hash = HashUtil.hash("123");
        assertNotNull(hash);
        assertNotEquals("123", hash);
    }
}