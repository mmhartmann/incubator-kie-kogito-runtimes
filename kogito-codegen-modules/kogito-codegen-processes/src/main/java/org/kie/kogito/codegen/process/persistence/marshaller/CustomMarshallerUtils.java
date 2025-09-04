package org.kie.kogito.codegen.process.persistence.marshaller;

import java.util.Collection;
import java.util.ServiceLoader;

public class CustomMarshallerUtils {

    @SuppressWarnings({"unchecked", "rawtypes"})
    static public Collection<AbstractCustomMarshaller<?>> serviceLoadMarshallers() {
        ServiceLoader loader = ServiceLoader.load(AbstractCustomMarshaller.class);
        return loader.stream().map(p -> ((ServiceLoader.Provider<AbstractCustomMarshaller>) p).get()).toList();
    }

}