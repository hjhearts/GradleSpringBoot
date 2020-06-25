package com.mygradle.commons.importSelect;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(EnableAutoMyModule.class.getName(), false)
        );
        String value = attributes.getString("value");
        if("someValue".equals(value)){
            return new String[]{AConfig.class.getName()};
        }else{
            return new String[]{BConfig.class.getName()};
        }
    }
}
