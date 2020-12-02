package br.com.projeto.commons;

import java.util.*;
import org.junit.Assert;
import java.lang.reflect.Method;

/**
 * Classe executora de Steps.
 *
 * Tem o objetivo de interceptar a execução dos steps para capturar quaisquer
 * exceções que possam ser geradas.
 *
 * @author F. Jesus
 * @version 1.3.0_2020
 */
public class StepExecutor extends BaseTest {

    /**
     * This method executes the specified method of the object instance.
     *
     * @param instance - the instance of class.
     * @param method - the method of class instance.
     * @param args - the arguments of the method.
     * @return the return value of method.
     */
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

    /**
     * This method finds the method specified in the specified object.
     *
     * @param instance - the object to search for.
     * @param name - the method name.
     * @param args - the arguments of the method.
     * @return the Method's class instance.
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

    /**
     * This method finds the method specified in the specified class (use for static methods).
     *
     * @param clazz - the class that contains the methods.
     * @param name - the method name.
     * @param args - the arguments of the method.
     * @return the Method's class instance.
     */
    public Method findMethod(Class clazz, String name, Object... args) {
        Method m = null;
        List<Method> methodList = new ArrayList<>();

        if (Objects.isNull(clazz)) throw new IllegalArgumentException("The object instance cannot be null.");
        if (Objects.isNull(name) || Objects.equals(name.isEmpty(), true))
            throw new IllegalArgumentException("The name cannot be null or empty.");

        for (Method method : clazz.getMethods()) {
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

    /**
     * This method reflects the assertion methods of JUnit's Assert class.
     *
     * @param assertMethod - the name of assert method of JUnit's Assert class.
     * @param params - the args of the specified method.
     * @throws AssertionError - when assertion fail.
     */
    public void assertBy(String assertMethod, Object... params) {
        execute(null, findMethod(Assert.class, assertMethod, params), params);
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
