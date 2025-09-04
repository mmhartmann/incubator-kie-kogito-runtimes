package org.kie.kogito.codegen.process.persistence.proto;

public abstract class AbstractCustomProtoGenerator<T> implements CustomProtoGenerator<T> {

    private final Class<T> javaClass;

    public AbstractCustomProtoGenerator(Class<T> javaClass) {
        this.javaClass = javaClass;
    }

    public Class<T> getJavaClass() {
        return javaClass;
    }

    @Override
    public String getTypeName() {
        return getJavaClass().getSimpleName();
    }

    @Override
    public ProtoMessage generateProto(Proto proto) {
        ProtoMessage protoMessage = new ProtoMessage(getTypeName(), getJavaClass().getPackage().getName());
        return generateProto(proto, protoMessage);
    }

    public abstract ProtoMessage generateProto(Proto proto, ProtoMessage message);

}
