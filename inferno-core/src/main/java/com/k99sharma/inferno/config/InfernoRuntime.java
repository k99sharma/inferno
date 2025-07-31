package com.k99sharma.inferno.config;

import lombok.Getter;
import lombok.Setter;

public class InfernoRuntime {
    private static boolean activatedByAnnotation = false;

    @Getter
    @Setter
    private static String[] profiles = new String[0];

    public static void markAnnotationEnabled() {
        activatedByAnnotation = true;
    }

    public static boolean isAnnotationEnabled() {
        return activatedByAnnotation;
    }
}
