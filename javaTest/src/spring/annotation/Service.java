package spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: 注册为服务方法的注释</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Oct 11, 2010
 */

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD}) //表示这个Annotation只能用于注释 构造子和方法  
public @interface Service {
  String serviceName();
}
