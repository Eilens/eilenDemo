/**
 * Copyright (C), 2015-2018, 中商惠民科技有限公司
 * FileName: DynamicDataSource
 * FileName: DynamicDataSource
 * Author:   Eilen
 * Date:     2018/6/19 15:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 **/
package xin.eilen.multidatasource.datasourceconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈describe〉<br>
 * 〈增强datasource〉
 *
 * @author Eilen
 * @create 2018/6/19
 * @since 1.0.0
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        LOGGER.debug("数据源为",DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }

}
