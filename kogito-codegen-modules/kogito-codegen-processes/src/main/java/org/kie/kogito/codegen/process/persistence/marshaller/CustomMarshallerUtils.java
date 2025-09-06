package org.kie.kogito.codegen.process.persistence.marshaller;

import java.util.Collection;
import java.util.ServiceLoader;

public class CustomMarshallerUtils {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    static public Collection<CustomMarshaller<?>> serviceLoadMarshallers() {
        ServiceLoader loader = ServiceLoader.load(CustomMarshaller.class);
        return loader.stream().map(p -> ((ServiceLoader.Provider<CustomMarshaller>) p).get()).toList();
    }

}