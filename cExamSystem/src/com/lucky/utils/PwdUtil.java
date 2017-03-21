package com.lucky.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 将密码进行MD5加密处理
 * @author hong
 * Created by admin on 2015/12/5.
 */
public final class PwdUtil {
    private final static String HEX_NUMS_STR = "0123456789ABCDEF";
    private static final String ALGORITHM_MD5 = "MD5";

    /**
     * 用md5算法加密 密码
     * @param pass the password to encryption
     * @return encryption string
     */
    public static String getPassMD5(String pass) throws NoSuchAlgorithmException,UnsupportedEncodingException {
        String keys;

            MessageDigest md = MessageDigest.getInstance(ALGORITHM_MD5);
            if (pass == null) {
                pass = "";
            }
            byte[] bPass = pass.getBytes("UTF-8");
            md.update(bPass);
            keys = bytesToHexString(md.digest());

        return keys;
    }

    /**
     * 将beye[]转换为十六进制字符串
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2){
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转换成数组
     *
     * @return byte[]
     * @author jacob
     */
    public static byte[] hexStringToByte(String hex) {
        /* len为什么是hex.length() / 2 ?
         * 首先，hex是一个字符串，里面的内容是像16进制那样的char数组
         * 用2个16进制数字可以表示1个byte，所以要求得这些char[]可以转化成什么样的byte[]，首先可以确定的就是长度为这个char[]的一半
         */
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            //第i个字符转换成高位二进制的四位数，再拼接上i+1个字符形成一个8位有效的二进制位
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }
}
