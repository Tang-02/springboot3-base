package com.tjl.example.utils;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.tjl.example.constant.UserConstant;
import org.springframework.stereotype.Component;

/**
 * @author Tang
 * 用户密码工具类
 */
public class PwdUtil {


    public static String toMD5_SALT(String initPassword) {
        return DigestUtil.md5Hex(UserConstant.SALT + initPassword);
    }
}
