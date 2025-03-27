package org.delivery.api.common.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service //spring에서 감지를 시키기 위해서 사용하는 어노테이션
public @interface Business {

    @AliasFor(annotation = Service.class)
    String value() default "";
}
