package org.kie.kogito.codegen.process.persistence.proto;

public interface CustomProtoGenerator<T> {

    String getTypeName();

    ProtoMessage generateProto(Proto proto);

}
