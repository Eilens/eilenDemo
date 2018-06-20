/**
 * Copyright (C), 2015-2018, 中商惠民科技有限公司
 * FileName: DataSourceConfig
 * FileName: DataSourceConfig
 * Author:   Eilen
 * Date:     2018/6/19 15:14
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 **/
package xin.eilen.multidatasource.datasourceconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈describe〉<br>
 * 〈多数据源的配置〉
 *
 * @author Eilen
 * @create 2018/6/19
 * @since 1.0.0
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "one-db")
    @ConfigurationProperties(prefix = "spring.datasource.one-db")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "two-db")
    @ConfigurationProperties(prefix = "spring.datasource.two-db")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     *
     * @return
     */
    @Bean(name = "dynamicDS1")
    public DataSource dataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("one-db", dataSource1());
        dsMap.put("two-db", dataSource2());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }
}
