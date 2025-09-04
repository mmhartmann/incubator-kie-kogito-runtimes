package org.kie.kogito.codegen.process.persistence.proto;

import org.kie.kogito.codegen.process.persistence.marshaller.AbstractCustomMarshaller;
import org.kie.kogito.codegen.process.persistence.marshaller.CustomMarshallerUtils;

import java.util.Collection;

public class CustomProtoGeneratorUtils {

    static public Collection<AbstractCustomProtoGenerator<?>> serviceLoadProtoGenerators() {
        Collection<AbstractCustomMarshaller<?>> marshallers = CustomMarshallerUtils.serviceLoadMarshallers();
        return marshallers.stream().map(AbstractCustomMarshaller::getProtoGenerator).collect(java.util.stream.Collectors.toList());
    }
}
