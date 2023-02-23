package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PublicInterfaces {

    private static Map<String, Map<String, Method>> caches;

    static {
        if (PublicInterfaces.class.getClassLoader() != ProxyCL.instance) {
            caches = new HashMap<>();
        }
    }

    @SuppressWarnings("unchecked")
    protected static <T> T checkClassLoader(Supplier<T> func, Object... args) {
        if (caches == null) {
            return func.get();
        }

        return (T) invokeSelf(args);
    }

    protected static void checkClassLoader(Runnable func, Object... args) {
        if (caches == null) {
            func.run();
        } else {
            invokeSelf(args);
        }
    }

    private static Object invokeSelf(Object... args) {
        StackTraceElement element = new Exception().getStackTrace()[2];
        String clazz = element.getClassName();
        String method = element.getMethodName();

        if (!caches.containsKey(clazz)) {
            Map<String, Method> methodMap = new HashMap<>();

            try {
                Method[] methods = Class.forName(clazz, true, ProxyCL.instance).getMethods();

                for (Method m : methods) {
                    methodMap.put(m.getName(), m);
                }
            } catch (ClassNotFoundException e) {}

            caches.put(clazz, methodMap);
        }

        try {
            return caches.get(clazz).get(method).invoke(null, args);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {}

        return null; // HOW DARE YOU
    }
}
