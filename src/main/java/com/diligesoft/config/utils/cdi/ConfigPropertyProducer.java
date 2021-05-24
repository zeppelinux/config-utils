package com.diligesoft.config.utils.cdi;

import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class ConfigPropertyProducer {

    @Produces
    @ConfigPropertyList(name = "unused")
    List<Object> produceList(InjectionPoint injectionPoint) {
        // resolve the annotation
        ConfigPropertyList configPropertyMetaData = extractAnnotation(injectionPoint.getAnnotated(), ConfigPropertyList.class);

        List<Object> value = ConfigProvider.getConfig().getValue(configPropertyMetaData.name(), List.class);
        if (value == null || value.size() < configPropertyMetaData.minSize()) {
            throw new RuntimeException(String.format("'%s' configuration property is not set or size is less than minSize value (%d) configured at the injection point", configPropertyMetaData.name(), configPropertyMetaData.minSize()));
        }

        return Collections.unmodifiableList(value);
    }

    @Produces
    @ConfigPropertyMap(name = "unused")
    Map<String, Object> produceMap(InjectionPoint injectionPoint) {
        // resolve the annotation
        ConfigPropertyMap configPropertyMetaData = extractAnnotation(injectionPoint.getAnnotated(), ConfigPropertyMap.class);

        Map<String, Object> value = ConfigProvider.getConfig().getValue(configPropertyMetaData.name(), Map.class);
        if (value == null || value.size() < configPropertyMetaData.minSize()) {
            throw new RuntimeException(String.format("'%s' configuration property is not set or size is less than minSize value (%d) configured at the injection point", configPropertyMetaData.name(), configPropertyMetaData.minSize()));
        }

        return Collections.unmodifiableMap(value);
    }

    public static <T extends Annotation> T extractAnnotation(Annotated annotated, Class<T> targetType) {
        T result = annotated.getAnnotation(targetType);
        if (result == null) {
            Iterator var3 = annotated.getAnnotations().iterator();
            while (var3.hasNext()) {
                Annotation annotation = (Annotation) var3.next();
                result = annotation.annotationType().getAnnotation(targetType);
                if (result != null) {
                    break;
                }
                return result;
            }
        }
        return result;
    }

}
