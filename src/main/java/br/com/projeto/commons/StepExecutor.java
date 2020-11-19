package br.com.projeto.commons;

import java.util.*;
import java.lang.reflect.Method;

public class StepExecutor extends BaseTest {

    public Object execute(Object instance, Method method, Object... args) {

        Object ret = null;
        try {
            if (Objects.nonNull(args) && args.length > 0) {
                ret = method.invoke(instance, args);
            } else {
                ret = method.invoke(instance);
            }
        } catch (Exception ex) {
            /* * * * * * * * * * * * * *
             * Caso ocorra uma exceção *
             * * * * * * * * * * * * * */
            setError(ex.getCause(), webDriver);
        } catch (AssertionError ae) {
            /* * * * * * * * * * * * * * * * * *
             * Caso falhe a validação do step. *
             * * * * * * * * * * * * * * * * * */
            setError(ae, webDriver);
        }
        return ret;
    }

    public Method findMethod(Object instance, String name, Object... args) {
        Method m = null;
        List<Method> methodList = new ArrayList<>();

        if (Objects.isNull(instance)) throw new IllegalArgumentException("The object instance cannot be null.");
        if (Objects.isNull(name) || Objects.equals(name.isEmpty(), true))
            throw new IllegalArgumentException("The name cannot be null or empty.");

        for (Method method : instance.getClass().getMethods()) {
            if (method.getName().equals(name)) {
                methodList.add(method);
            }
        }

        if (Objects.equals(methodList.size(), 0)) throw new RuntimeException("Method not found.");

        if (Objects.nonNull(args) && args.length > 0) {
            for (Method method : methodList) {
                if (Objects.equals(method.getParameterCount(), args.length)) {
                    if (Arrays.equals(getListOfObjectType(args), method.getParameterTypes())) {
                        m = method;
                        break;
                    }
                }
            }
        } else {
            m = methodList.get(0);
        }
        return m;
    }

    private Object[] getListOfObjectType(Object[] array) {
        Object[] r;
        if (Objects.isNull(array) || Objects.equals(array.length, 0))
            throw new RuntimeException("The array cannot be empty or null.");
        r = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            r[i] = array[i].getClass();
        }
        return r;
    }
}
