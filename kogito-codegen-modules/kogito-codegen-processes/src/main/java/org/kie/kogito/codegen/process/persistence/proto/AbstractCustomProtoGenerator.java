package org.kie.kogito.codegen.process.persistence.proto;

public abstract class AbstractCustomProtoGenerator<T> implements CustomProtoGenerator<T> {

    protected final Class<T> clazz;
    protected final String namespace;

    public AbstractCustomProtoGenerator(Class<T> clazz, String namespace) {
        this.clazz = clazz;
        this.namespace = namespace;
    }

    @Override
    public Class<?> getJavaClass() {
        return clazz;
    }

    @Override
    public String getTypeName() {
        return clazz.getSimpleName();
    }

    @Override
    public String getFullName() {
        return namespace + "." + getTypeName();
    }

    @Override
    public boolean canProcess(Class<?> clazz) {
        return clazz.equals(getJavaClass());
    }

    @Override
    public ProtoMessage generateProto(Proto proto) {
        ProtoMessage protoMessage = new ProtoMessage(getTypeName(), getJavaClass().getPackage().getName());
        return generateProto(proto, protoMessage);
    }

    public abstract ProtoMessage generateProto(Proto proto, ProtoMessage message);

}
