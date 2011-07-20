package spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: ע��Ϊ���񷽷���ע��</p> 
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Zhaoli
 * @version 1.0 Oct 11, 2010
 */

@Retention(RetentionPolicy.RUNTIME)  
@Target({ElementType.METHOD}) //��ʾ���Annotationֻ������ע�� �����Ӻͷ���  
public @interface Service {
  String serviceName();
}
