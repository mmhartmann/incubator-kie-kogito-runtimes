package org.kie.kogito.codegen.process.persistence.proto;

import java.util.Collection;

import org.kie.kogito.codegen.process.persistence.marshaller.AbstractCustomMarshaller;
import org.kie.kogito.codegen.process.persistence.marshaller.CustomMarshaller;
import org.kie.kogito.codegen.process.persistence.marshaller.CustomMarshallerUtils;

public class CustomProtoGeneratorUtils {

    static public Collection<CustomProtoGenerator<?>> serviceLoadProtoGenerators() {
        Collection<CustomMarshaller<?>> marshallers = CustomMarshallerUtils.serviceLoadMarshallers();
        return marshallers.stream().map(CustomMarshaller::getProtoGenerator).collect(java.util.stream.Collectors.toList());
    }
}
