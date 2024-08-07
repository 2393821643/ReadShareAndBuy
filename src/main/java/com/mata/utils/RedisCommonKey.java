package com.mata.utils;

public class RedisCommonKey {
    // 登录验证码前缀
    public final static String LOGIN_CODE_PER_KEY = "login:code:";

    // 登录验证码存活时间
    public final static long LOGIN_CODE_TIME = 2L;

    // 修改密码验证码前缀
    public final static String CHANGE_PASSWORD_CODE_PER_KEY = "change:code:";

    // 修改密码验证码存活时间
    public final static long CHANGE_PASSWORD_CODE_TIME = 2L;

    // 用户id缓存 前缀
    public final static String USER_ID_PRE_KEY = "user:id:";

    // 用户id缓存 存活时间
    public final static long USER_ID_TIME = 30L;


    // 创建用户缓存 锁前缀
    public final static String USER_ID_LOCK_PRE_KEY = "lock:user:id:";

    // 创建用户缓存 锁存活时间
    public final static long USER_ID_LOCK_TIME = 5L;

}
