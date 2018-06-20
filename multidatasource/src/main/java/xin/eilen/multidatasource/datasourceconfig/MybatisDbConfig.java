/**
 * Copyright (C), 2015-2018, 中商惠民科技有限公司
 * FileName: MybatisDbConfig
 * FileName: MybatisDbConfig
 * Author:   Eilen
 * Date:     2018/6/19 15:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 **/
package xin.eilen.multidatasource.datasourceconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 〈describe〉<br>
 * 〈mybatis的多数据源配置〉
 *
 * @author Eilen
 * @create 2018/6/19
 * @since 1.0.0
 */
@Configuration
@MapperScan(basePackages = {"onedb.mapper"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisDbConfig {
    @Autowired
    @Qualifier("one-db")
    private DataSource ds1;

    @Autowired
    @Qualifier("two-db")
    private DataSource ds2;

    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds1);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory2() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds2);
        return sqlSessionFactoryBean.getObject();
    }
}
