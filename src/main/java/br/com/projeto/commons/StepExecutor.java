package br.com.projeto.commons;

import java.util.*;
import java.lang.reflect.Method;

public class StepExecutor extends BaseTest {

    public void execute(Object instance, Method method, Object... args) {

        try {
            if (Objects.nonNull(args) && args.length > 0) {
                method.invoke(instance, args);
            } else {
                method.invoke(instance);
            }
        } catch (Exception ex) {
            /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
             * Caso ocorra alguma exceção do método chamado ou da reflexão *
             * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
            setError(ex, webDriver);
        } catch (AssertionError ae) {
            /* * * * * * * * * * * * * * * * * *
             * Caso falhe a validação do step. *
             * * * * * * * * * * * * * * * * * */
            setError(ae, webDriver);
        }
    }

    /**
     * This method gets the specified method of an object.
     *
     * @param name     - The name of the method.
     * @param instance - The instance of object that contains the method.
     * @param args     - The arguments of the method (if there is).
     * @return the corresponding method or null if, and only if, method has not found.
     */
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
