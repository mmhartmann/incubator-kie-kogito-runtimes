package org.kie.kogito.codegen.process.persistence.proto;

import org.jbpm.bpmn2.core.CorrelationProperty;

public interface CustomProtoGenerator<T> {

    Class<?> getJavaClass();

    String getTypeName();

    String getFullName();

    ProtoMessage generateProto(Proto proto);

    boolean canProcess(Class<?> clazz);

}
