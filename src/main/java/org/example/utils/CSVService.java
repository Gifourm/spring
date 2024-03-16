package org.example.utils;

import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.*;

@Service
public class CSVService {
    public List<String> objectToList(Object obj) {
        List<String> values = new ArrayList<>();
        Method[] methods = obj.getClass().getDeclaredMethods();
        Arrays.sort(methods, Comparator.comparing(Method::getName));

        for (Method method : methods) {
            if (method.getName().startsWith("get") || method.getName().startsWith("is")) {
                try {
                    Object value = method.invoke(obj);
                    if (value instanceof Date) {
                        values.add(value.toString());
                    } else {
                        values.add((String) value);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return values;
    }
}
