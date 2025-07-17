package org.kie.kogito.codegen.process.persistence;

import org.kie.kogito.codegen.api.context.KogitoBuildContext;

public class SerializationFallbackUtils {

    public static final String KOGITO_PERSISTANCE_FALLBACK_PATTERN = "kogito.persistence.fallback_pattern";
    private final String exclusionPattern;

    public SerializationFallbackUtils(KogitoBuildContext context) {
        this.exclusionPattern = context.getApplicationProperty(KOGITO_PERSISTANCE_FALLBACK_PATTERN).orElse(null);
    }

    // Whether this field should be excluded from the proto generation and should immediately be serialized.
    public boolean shouldFallback(String fieldName) {
        return exclusionPattern != null && fieldName.matches(exclusionPattern);
    }
}
