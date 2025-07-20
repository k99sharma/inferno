package com.k99sharma.inferno.config;

public class InfernoRuntime {
    private static boolean activatedByAnnotation = false;

    public static void markAnnotationEnabled() {
        activatedByAnnotation = true;
    }

    public static boolean isAnnotationEnabled() {
        return activatedByAnnotation;
    }
}
