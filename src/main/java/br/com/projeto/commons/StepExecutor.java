package br.com.projeto.commons;

import java.util.*;
import java.lang.reflect.Method;

/**
 * Classe executora de Steps.
 *
 * Intercepta a execução dos steps para capturar qualquer exceção
 * que possa ocorrer durante a execução.
 *
 * Falha o caso de teste e gera a evidência quando ocorre uma exceção.
 *
 * @author F. Jesus
 * @version 1.5.0.0
 */
public class StepExecutor extends BaseTest {

    /**
     * Executes the specified method.
     *
     * @param instance the instance of the object that contains the method.
     * @param clazz the class that contains the method.
     * @param methodName the name of the method.
     * @param args the arguments of the method (if any).
     * @return the value that the method returned (if any).
     */
    public Object execute(Object instance, Class clazz, String methodName, Object... args) {
        Method m = findMethod(clazz, methodName, args);
        return execute(instance, m, args);
    }

    /**
     * Executes the specified method.
     *
     * @param instance the instance of the object that contains the method.
     * @param method   the method to execute.
     * @param args     the arguments of the method (if any).
     * @return the value that the method returned (if any).
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
     * Searches for the specified method.
     *
     * @param instance the instance of the object that contains the method.
     * @param name the name of the method.
     * @param args the arguments of the method (if any).
     * @return the method (if any).
     */
    public Method findMethod(Object instance, String name, Object... args) {
        Method m = null;
        List<Method> methodList = new ArrayList<>();

        checkParameters(instance, name);

        for (Method method : instance.getClass().getMethods()) {
            if (method.getName().equals(name)) {
                methodList.add(method);
            }
        }

        if (Objects.equals(methodList.size(), 0)) throw new RuntimeException("Method not found.");

        if (Objects.nonNull(args) && args.length > 0) {
            for (Method method : methodList) {
                if (Objects.equals(method.getParameterCount(), args.length)) {
                    if (Arrays.equals(getParameterTypes(args), method.getParameterTypes())) {
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
     * Searches for the specified method.
     *
     * @param clazz the class that contains the method.
     * @param name the name of the method.
     * @param args the arguments of the method (if any).
     * @return the method (if any).
     */
    public Method findMethod(Class clazz, String name, Object... args) {
        Method m;

        checkParameters(clazz, name);

        try {
            if (Objects.nonNull(args) && args.length > 0) {
                try {
                    m = clazz.getDeclaredMethod(name, getParameterTypes(args));
                } catch (NoSuchMethodException ex) {
                    m = clazz.getDeclaredMethod(name, getPrimitiveParameterTypes(args));
                }
            } else {
                m = clazz.getDeclaredMethod(name);
            }
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException("Method not found.");
        }

        return m;
    }

    /**
     * Searches for the specified method.
     *
     * @param clazz the class that contains the method.
     * @param name the name of the method.
     * @param argumentTypes the argument types of the method (if any).
     * @return the method (if any).
     */
    public Method findMethod(Class clazz, String name, Class... argumentTypes) {
        Method m;

        checkParameters(clazz, name);

        try {
            if (Objects.nonNull(argumentTypes) && argumentTypes.length > 0) {
                m = clazz.getDeclaredMethod(name, argumentTypes);
            } else {
                m = clazz.getDeclaredMethod(name);
            }
        } catch (NoSuchMethodException ex) {
            throw new RuntimeException("Method not found.");
        }

        return m;
    }

    /**
     * Checks the integrity of the parameters.
     * @param clazz the class.
     * @param name the name of method.
     */
    private void checkParameters(Class clazz, String name) {
        if (Objects.isNull(clazz)) throw new IllegalArgumentException("The object instance cannot be null.");
        if (Objects.isNull(name) || Objects.equals(name.isEmpty(), true))
            throw new IllegalArgumentException("The name cannot be null or empty.");
    }

    /**
     * Checks the integrity of the parameters.
     * @param instance the object of class.
     * @param name the name of method.
     */
    private void checkParameters(Object instance, String name) {
        if (Objects.isNull(instance)) throw new IllegalArgumentException("The object instance cannot be null.");
        if (Objects.isNull(name) || Objects.equals(name.isEmpty(), true))
            throw new IllegalArgumentException("The name cannot be null or empty.");
    }

    /**
     * This method obtains the parameter types of each object in array.
     * @param array the array of objects.
     * @return an array of class types.
     */
    private Class[] getParameterTypes(Object[] array) {
        Class[] r;
        if (Objects.isNull(array) || Objects.equals(array.length, 0))
            throw new RuntimeException("The array cannot be empty or null.");
        r = new Class[array.length];
        for (int i = 0; i < array.length; i++) {
            r[i] = array[i].getClass();
        }
        return r;
    }

    /**
     * This method tries to obtain the primitive types of the classes in the specified vector.
     * @param array the array of objects.
     * @return an array of Class.
     */
    private Class[] getPrimitiveParameterTypes(Object[] array) {
        Class[] r;
        if (Objects.isNull(array) || Objects.equals(array.length, 0))
            throw new RuntimeException("The array cannot be empty or null.");
        r = new Class[array.length];
        for (int i = 0; i < array.length; i++) {
            r[i] = toPrimitiveIfPossible(array[i].getClass());
        }
        return r;
    }

    /**
     * This method tries to obtain the primitive types of the specified class.
     * @param clazz the class object.
     * @return the primitive form or the same type if the class hasn't primitive form.
     */
    private Class toPrimitiveIfPossible(Class clazz) {
        Class c;
        if (Objects.equals(Integer.class, clazz)) {
            c = int.class;
        } else if(Objects.equals(Float.class, clazz)) {
            c = float.class;
        } else if(Objects.equals(Double.class, clazz)) {
            c = double.class;
        } else if(Objects.equals(Boolean.class, clazz)) {
            c = boolean.class;
        } else if(Objects.equals(Byte.class, clazz)) {
            c = byte.class;
        } else if(Objects.equals(Short.class, clazz)) {
            c = short.class;
        } else if(Objects.equals(Character.class, clazz)) {
            c = char.class;
        } else if(Objects.equals(Long.class, clazz)) {
            c = long.class;
        } else {
            c = clazz;
        }
        return c;
    }
}
