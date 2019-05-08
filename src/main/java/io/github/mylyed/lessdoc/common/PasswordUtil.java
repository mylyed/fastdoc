package io.github.mylyed.lessdoc.common;


import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lilei
 * created at 2019/5/3
 */
public class PasswordUtil {
    static int saltSize = 16;
    static String delmiter = "\\$";
    static int stretching_password = 500;
    static String salt_local_secret = "ahfw*&TGdsfnbi*^Wt";


    public static String hash(String pass, String salt_secret, String salt, int interation) {
        String passSalt = salt_secret + pass + salt + salt_secret + pass + salt + pass + pass + salt;

        String hashPass = salt_local_secret;
        MessageDigest hashStart = DigestUtils.getSha512Digest();
        MessageDigest hashCenter = DigestUtils.getSha256Digest();
        MessageDigest hashOutput = DigestUtils.getSha3_224Digest();

        int i = 0;
        while (i <= stretching_password) {
            i = i + 1;
            byte[] bs = hashStart.digest(StringUtils.getBytesUtf8(passSalt + hashPass));
            hashPass = Hex.encodeHexString(bs);
        }

        i = 0;
        while (i <= interation) {
            i = i + 1;
            hashPass = hashPass + hashPass;
        }

        i = 0;
        while (i <= stretching_password) {
            i = i + 1;
            byte[] bs = hashCenter.digest(StringUtils.getBytesUtf8(passSalt + hashPass));

            hashPass = Hex.encodeHexString(bs);
        }
        byte[] bs = hashOutput.digest(StringUtils.getBytesUtf8(hashPass + salt_local_secret));

        hashPass = Hex.encodeHexString(bs);

        return hashPass;
    }


    //校验密码是否有效
    public static boolean passwordVerify(String hashing, String pass) {
        Map<String, String> data = trimSaltHash(hashing);

        int interation = Integer.parseInt(data.get("interation_string"));

        String has = hash(pass, data.get("salt_secret"), data.get("salt"), (interation));


        if (hashing.equals(data.get("salt_secret") + delmiter + data.get("interation_string") + delmiter + has + delmiter + data.get("salt"))) {
            return true;
        } else {
            return false;
        }

    }

    public static Map<String, String> trimSaltHash(String hash) {

        String[] str = hash.split(delmiter);
        System.out.println(Arrays.toString(str));
        Map<String, String> map = new HashMap<>();

        map.put("salt_secret", str[0]);
        map.put("interation_string", str[1]);
        map.put("hash", str[2]);
        map.put("salt", str[3]);

        return map;
    }


    public static void main(String[] args) {
        boolean b = passwordVerify("yu0l_CANT2PmYac1Knawaych93cRoFwrd-h-aILZNOu3nxz7UXI_QBYMS9JY-Klr5UGh$15$b9485d5f71ab478fb7a3151ad4a623e1af1f5fe0520876c42f4bff05$eda18962bef909f9847041ad78359bc9652fb5048e1551fa0342009a4d75e421", "123456");
        System.out.println(b);

    }
}
