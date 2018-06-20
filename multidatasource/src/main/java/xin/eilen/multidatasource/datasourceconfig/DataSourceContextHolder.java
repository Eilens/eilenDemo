/**
 * Copyright (C), 2015-2018, 中商惠民科技有限公司
 * FileName: DataSourceContextHolder
 * FileName: DataSourceContextHolder
 * Author:   Eilen
 * Date:     2018/6/19 15:32
 * Description: 配置动态数据源
 * History:
 * <author>          <time>          <version>          <desc>
 **/
package xin.eilen.multidatasource.datasourceconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈describe〉<br>
 * 〈配置动态数据源〉
 *
 * @author Eilen
 * @create 2018/6/19
 * @since 1.0.0
 */
public class DataSourceContextHolder {
    public static final Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);
    /**
     * 设置默认数据源
     */
    public static final String DEFAULT_DS = "one-db";
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源名字
     *
     * @param dbType
     */
    static void setDB(String dbType) {
        LOGGER.debug("切换数据源", dbType);
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源名字
     *
     * @return
     */
    static String getDB() {
        return contextHolder.get();
    }

    /**
     * 清楚数据源名称
     */
    static void clearDB() {
        LOGGER.debug("开始清除数据源");
        contextHolder.remove();
    }
}
