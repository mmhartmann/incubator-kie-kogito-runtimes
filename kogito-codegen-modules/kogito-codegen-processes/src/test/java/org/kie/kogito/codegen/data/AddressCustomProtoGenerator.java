package org.kie.kogito.codegen.data;

import org.kie.kogito.codegen.process.persistence.proto.AbstractCustomProtoGenerator;
import org.kie.kogito.codegen.process.persistence.proto.Proto;
import org.kie.kogito.codegen.process.persistence.proto.ProtoMessage;

public class AddressCustomProtoGenerator extends AbstractCustomProtoGenerator<Address> {

    final static protected String ADDRESS_STRING_FIELD = "addressString";

    public AddressCustomProtoGenerator() {
        super(Address.class);
    }

    @Override
    public ProtoMessage generateProto(Proto proto, ProtoMessage message) {
        message.addField("optional", "string", ADDRESS_STRING_FIELD);
        return message;
    }
}
