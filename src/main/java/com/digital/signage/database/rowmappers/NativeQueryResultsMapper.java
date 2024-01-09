package com.digital.signage.database.rowmappers;

import com.digital.signage.annotations.NativeQueryResultColumn;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class NativeQueryResultsMapper {

    private static final Logger logger = LoggerFactory.getLogger(NativeQueryResultsMapper.class);

    public  <T> List<T> map(List<Object[]> objectArrayList, Class<T> genericType) {
        List<T> ret = new ArrayList<>();
        List<Field> mappingFields = getNativeQueryResultColumnAnnotatedFields(genericType);
        try {
            for (Object[] objectArr : objectArrayList) {
                T t = genericType.newInstance();
                for (int i = 0; i < objectArr.length; i++) {
                    if(objectArr[i] !=null && mappingFields.get(i)!=null)
                    {
                        BeanUtils.setProperty(t, mappingFields.get(i).getName(), objectArr[i]);
                    }
                }
                ret.add(t);
            }
        } catch (InstantiationException ie) {
            logger.debug("Cannot instantiate: ", ie);
            ret.clear();
        } catch (IllegalAccessException iae) {
            logger.debug("Illegal access: ", iae);
            ret.clear();
        } catch (InvocationTargetException ite) {
            logger.debug("Cannot invoke method: ", ite);
            ret.clear();
        }
        return ret;
    }

    // Get ordered list of fields
    private static <T> List<Field> getNativeQueryResultColumnAnnotatedFields(Class<T> genericType) {
        Field[] fields = genericType.getDeclaredFields();
        List<Field> orderedFields = Arrays.asList(new Field[fields.length]);
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(NativeQueryResultColumn.class)) {
                NativeQueryResultColumn nqrc = fields[i].getAnnotation(NativeQueryResultColumn.class);
                orderedFields.set(nqrc.index(), fields[i]);
            }
        }
        return orderedFields;
    }
}

