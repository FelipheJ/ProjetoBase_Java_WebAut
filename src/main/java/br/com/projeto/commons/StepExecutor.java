package br.com.projeto.commons;

import java.lang.reflect.Method;

public class StepExecutor {

    public static void execute(Method method, Object instance, Object... args) {
        try {
            method.invoke(instance, args);
        } catch (Exception ex) {
            // Caso ocorra alguma exceção do método chamado ou da reflexão
        } catch (AssertionError ae) {
            // Caso falhe a validação do step
        } finally {
            // Gerar evidencias
        }
    }
}
