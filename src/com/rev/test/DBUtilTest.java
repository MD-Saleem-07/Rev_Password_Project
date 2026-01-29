package com.rev.test;

import static org.junit.Assert.*;
import org.junit.Test;
import java.sql.Connection;

import com.rev.util.DBUtil;

public class DBUtilTest {

    @Test
    public void testDBConnection() {
        Connection con = DBUtil.getConnection();
        assertNotNull(con);
    }
}