package org.kie.kogito.codegen.process.persistence.marshaller;

import java.util.Collection;

public interface CustomMarshallerProvider {

    Collection<CustomMarshaller<?>> getCustomMarshallers(String namespace);

}
