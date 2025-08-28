package com.star.wallet.utilis;

public class RsaDemo {

    public static void main(String[] args) {
        // 加密內容
        String data ="test";
        System.out.println("加密前：" + data);
        // 公鑰
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFLJJgM6B+jcP1kCpyKDUk/0GYfJ5ePP4htmXEcaLbPM8W9CgnCPDIw1lIIcBX+6LM4HhElVNw/mOPeAy1hVRLgsmDJKUFQBPnEOqD9pMeoSyFSaU7dunC65YAW/sRZvdOvFwwDcJJWorG+mLXwUTNpNwrFXqya1CtafYWmrEI0QIDAQAB";
        System.out.println("公鑰：" + publicKey);
        // 私鑰
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMUskmAzoH6Nw/WQKnIoNST/QZh8nl48/iG2ZcRxots8zxb0KCcI8MjDWUghwFf7oszgeESVU3D+Y494DLWFVEuCyYMkpQVAE+cQ6oP2kx6hLIVJpTt26cLrlgBb+xFm9068XDANwklaisb6YtfBRM2k3CsVerJrUK1p9haasQjRAgMBAAECgYANc4/83ijJ2XQP8ajjptiRUJSyCXZoaXLQ6/efYj1BBbH/F+MRXqvGloUlxZ+woOsnXPz3eNR7de9m30LGs3TfnxLkkx4QPD6HZqAIiz0Q5sQsX4MVA/Kf5Vtg976pv+vHsLIDa7uD4AO0CRCL4TtovJSFJFm9YTsPVt1lFDCgLQJBAPqlG60eWT5zvA1gmWJcnK6dVQCEsmEoUsd1yfKCSW1f4EGMs9+f8XFyE1MvCC8L/wZpxW9JnUleb1pLNm7nSaMCQQDJYwHVZHf/opfaLHY3Ikmdhu983LZCfqCGbADead2kazGHteJXw6lNCR/w6s9pfriEm94gjwA8M9PLfRKU7jL7AkAxEW2voIdcIJAqFkUw6myKCqb/aPr65kf+YGLepP72+JQQgRkdqFQ+H1UFLenuO+z7Nm2+caznFiO6i4rmf0T9AkAVZLEjLCMbQviCssp0HO5ZD6aNSx0Jf04qOXr2JaTn/IMseYPr8tsfVeLQwwD5of6UB8LXDpnly1OqgETjEVLVAkEAtUMtoA0oOlqRCG+7VjJORSUJpIpt+MxXeQIuQ4oSEg6tZSFeJy16sLvW2vcsAVITZ1WH6hG393SoF2609OI1OA==";
        System.out.println("私鑰：" + privateKey);
        // 使用公鑰加密
        String s = SecureAesRsaUtil.publicEncryptData(data, publicKey);
        System.out.println("加密後：" + s);
        // 使用私鑰解密
        String s1 = SecureAesRsaUtil.privatDecryptData(s, privateKey);
        System.out.println("解密後：" + s1);

        /** 輸出內容
         加密前：test
         公鑰：MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFLJJgM6B+jcP1kCpyKDUk/0GYfJ5ePP4htmXEcaLbPM8W9CgnCPDIw1lIIcBX+6LM4HhElVNw/mOPeAy1hVRLgsmDJKUFQBPnEOqD9pMeoSyFSaU7dunC65YAW/sRZvdOvFwwDcJJWorG+mLXwUTNpNwrFXqya1CtafYWmrEI0QIDAQAB
         私鑰：MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMUskmAzoH6Nw/WQKnIoNST/QZh8nl48/iG2ZcRxots8zxb0KCcI8MjDWUghwFf7oszgeESVU3D+Y494DLWFVEuCyYMkpQVAE+cQ6oP2kx6hLIVJpTt26cLrlgBb+xFm9068XDANwklaisb6YtfBRM2k3CsVerJrUK1p9haasQjRAgMBAAECgYANc4/83ijJ2XQP8ajjptiRUJSyCXZoaXLQ6/efYj1BBbH/F+MRXqvGloUlxZ+woOsnXPz3eNR7de9m30LGs3TfnxLkkx4QPD6HZqAIiz0Q5sQsX4MVA/Kf5Vtg976pv+vHsLIDa7uD4AO0CRCL4TtovJSFJFm9YTsPVt1lFDCgLQJBAPqlG60eWT5zvA1gmWJcnK6dVQCEsmEoUsd1yfKCSW1f4EGMs9+f8XFyE1MvCC8L/wZpxW9JnUleb1pLNm7nSaMCQQDJYwHVZHf/opfaLHY3Ikmdhu983LZCfqCGbADead2kazGHteJXw6lNCR/w6s9pfriEm94gjwA8M9PLfRKU7jL7AkAxEW2voIdcIJAqFkUw6myKCqb/aPr65kf+YGLepP72+JQQgRkdqFQ+H1UFLenuO+z7Nm2+caznFiO6i4rmf0T9AkAVZLEjLCMbQviCssp0HO5ZD6aNSx0Jf04qOXr2JaTn/IMseYPr8tsfVeLQwwD5of6UB8LXDpnly1OqgETjEVLVAkEAtUMtoA0oOlqRCG+7VjJORSUJpIpt+MxXeQIuQ4oSEg6tZSFeJy16sLvW2vcsAVITZ1WH6hG393SoF2609OI1OA==
         加密後：QJTD3tI5goZzrBN66+1OCogSLQCQzHKv6VOBjo7jbm1lsI8ithOc1i3OWJndzdahVdwtFSJZXneIrbzHXoFO7a+Sy/5WTjpL0v2h6fN6yrb/qSUEyMmBxeOkvNhx3kOSG9l2J8hFIVxOVeX9BxBqR852IfVctC/KvX5iHAdhQaM=
         解密後：test
        **/

    }
}
