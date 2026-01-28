package com.rev.util;

import java.util.Random;

public class VerificationUtil {

    public static int generateCode() {
        return 100000 + new Random().nextInt(900000);
    }
}