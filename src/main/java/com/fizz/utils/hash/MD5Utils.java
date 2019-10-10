package com.fizz.utils.hash;

import com.fizz.config.shiro.ShiroConfig;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author Fizz
 * @since 2019/10/9 18:04
 */
public class MD5Utils {

    public static String md5(String source, String salt) {
        return new SimpleHash(ShiroConfig.ALGORITHM_NAME, source, ByteSource.Util.bytes(salt)
                , ShiroConfig.HASH_ITERATIONS).toString();
    }
}
