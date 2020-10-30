package br.com.projeto.commons;

import java.lang.reflect.Method;

public class StepExecutor {

    public static void execute(Method method, Object instance, Object... args) {
        try {
            method.invoke(instance, args);
        } catch (Exception ex) {

        } catch (AssertionError ae) {
            
        } finally {

        }
    }
}
