package com.cachexic.apple.common.constant;

/**
 * 系统常量
 *
 * @author tangmin
 * @date 2016年2月16日
 */
public class AppConstant {

    public static final String PAGE_SIZE = "20";
    public static final Long MAX_PAGE_SIZE = 1000000l;
    /**
     * true = 1
     */
    public static final Integer TRUE_INT = 1;
    /**
     * false = 0
     */
    public static final Integer FALSE_INT = 0;
    /**
     * 解决sonar中提到的坏味道，如果都是静态资源，构造方法私有化
     */
    private AppConstant() {
        throw new IllegalAccessError("Utility class");
    }
}
