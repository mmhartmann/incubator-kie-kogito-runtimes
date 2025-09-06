package org.kie.kogito.codegen.process.persistence.marshaller;

import org.infinispan.protostream.MessageMarshaller;
import org.kie.kogito.codegen.process.persistence.proto.CustomProtoGenerator;

public interface CustomMarshaller<T> extends MessageMarshaller<T> {

    CustomProtoGenerator<T> getProtoGenerator();

}
