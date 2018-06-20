/**
 * Copyright (C), 2015-2018, 中商惠民科技有限公司
 * FileName: DynamicDataSourceAspect
 * FileName: DynamicDataSourceAspect
 * Author:   Eilen
 * Date:     2018/6/19 15:53
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 **/
package xin.eilen.multidatasource.datasourceconfig;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 〈describe〉<br>
 * 〈〉
 *
 * @author Eilen
 * @create 2018/6/19
 * @since 1.0.0
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(DS)")
    public void beforeSwitchDS(JoinPoint point) {
        Class<?> className = point.getTarget().getClass();
        //获得访问的方法名
        String methodName = point.getSignature().getName();
        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String defaultDs = DataSourceContextHolder.DEFAULT_DS;
        try {
            //得到访问的方法对象
            Method method = className.getMethod(methodName, argClass);
            //判断是否含有DS注解
            if (method.isAnnotationPresent(DS.class)) {
                DS annotation = method.getAnnotation(DS.class);
                //从注解中取出数据源名字
                defaultDs = annotation.value();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        //切换数据源
        DataSourceContextHolder.setDB(defaultDs);
    }

    @After("@annotation(DS)")
    public void afterSwitchDS() {
        DataSourceContextHolder.clearDB();
    }
}
