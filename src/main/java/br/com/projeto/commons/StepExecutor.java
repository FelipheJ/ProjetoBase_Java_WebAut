package br.com.projeto.commons;

import java.lang.reflect.Method;
import java.util.Objects;

public class StepExecutor {

    public static void execute(Object instance, Method method, Object... args) {
        try {
            if (Objects.nonNull(args) && args.length > 0) {
                method.invoke(instance, args);
            } else {
                method.invoke(instance);
            }
        } catch (Exception ex) {
            // Caso ocorra alguma exceção do método chamado ou da reflexão
        } catch (AssertionError ae) {
            // Caso falhe a validação do step
        } finally {
            // Gerar evidencias
        }
    }

    public static Method findMethod(String name, Object instance,  Object... args) {
        Method m;

        return m;
    }

    @Deprecated
    public static void execute(Object instance, String methodName, Object... args) {
        if (Objects.isNull(instance)) throw new NullPointerException("The instance cannot be null");
        if (Objects.isNull(methodName) || Objects.equals(true, methodName.isEmpty())) throw new NullPointerException("The method name cannot be empty.");
        int i = 0, c = 0, p;
        try {
            for (Method method : instance.getClass().getDeclaredMethods()) {
                if (method.getName().equals(methodName)) {
                    if (Objects.equals((p = method.getParameterCount()), args.length)) {
                        for (Class clazz : method.getParameterTypes()) {
                            if (Objects.equals(args[i].getClass().getName(), clazz.getName())) {
                                c++;
                            }
                            i++;
                            instance.getClass().getDeclaredMethod(methodName,)
                        }
                        if (Objects.equals(c, p)) {

                        }
                    }
                }
            }

        } catch (Exception ex) {
            // Caso ocorra alguma exceção do método chamado ou da reflexão
        } catch (AssertionError ae) {
            // Caso falhe a validação do step
        } finally {
            // Gerar evidencias
        }
    }
}
