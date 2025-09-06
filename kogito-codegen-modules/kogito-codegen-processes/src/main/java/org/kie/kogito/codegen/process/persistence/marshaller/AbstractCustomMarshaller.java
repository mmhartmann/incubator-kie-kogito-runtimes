package org.kie.kogito.codegen.process.persistence.marshaller;

import org.kie.kogito.codegen.process.persistence.proto.AbstractCustomProtoGenerator;
import org.kie.kogito.codegen.process.persistence.proto.CustomProtoGenerator;

public abstract class AbstractCustomMarshaller<T> implements CustomMarshaller<T> {

    /// Implementation of a {@link AbstractCustomProtoGenerator} class to use
    /// in the {@link AbstractCustomProtoGenerator}
    final protected AbstractCustomProtoGenerator<T> protoGenerator;

    final protected Class<T> clazz;

    public AbstractCustomMarshaller(Class<T> clazz, AbstractCustomProtoGenerator<T> protoGenerator) {
        this.clazz = clazz;
        this.protoGenerator = protoGenerator;
    }

    @Override
    public Class<? extends T> getJavaClass() {
        return clazz;
    }

    @Override
    public String getTypeName() {
        return protoGenerator.getFullName();
    }

    @Override
    public AbstractCustomProtoGenerator<T> getProtoGenerator() {
        return protoGenerator;
    }
}
