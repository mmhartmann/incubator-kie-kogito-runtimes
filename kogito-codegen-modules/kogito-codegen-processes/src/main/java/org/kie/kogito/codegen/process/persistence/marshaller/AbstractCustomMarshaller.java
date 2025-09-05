package org.kie.kogito.codegen.process.persistence.marshaller;

import org.kie.kogito.codegen.process.persistence.proto.AbstractCustomProtoGenerator;

public abstract class AbstractCustomMarshaller<T> implements CustomMarshaller<T> {

    /// Implementation of a {@link AbstractCustomProtoGenerator} class to use
    /// in the {@link AbstractProtoGenerator}
    final protected AbstractCustomProtoGenerator<T> protoGenerator;

    public AbstractCustomMarshaller(AbstractCustomProtoGenerator<T> protoGenerator) {
        this.protoGenerator = protoGenerator;
    }

    public AbstractCustomProtoGenerator<T> getProtoGenerator() {
        return protoGenerator;
    }

    @Override
    public Class<T> getJavaClass() {
        return protoGenerator.getJavaClass();
    }

    @Override
    public String getTypeName() {
        return protoGenerator.getTypeName();
    }
}
