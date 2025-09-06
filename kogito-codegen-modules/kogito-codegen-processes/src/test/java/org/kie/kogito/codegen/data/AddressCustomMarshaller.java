package org.kie.kogito.codegen.data;

import java.io.IOException;

import org.infinispan.protostream.MessageMarshaller;
import org.kie.kogito.codegen.process.persistence.marshaller.AbstractCustomMarshaller;

import static org.kie.kogito.codegen.data.AddressCustomProtoGenerator.*;

public class AddressCustomMarshaller extends AbstractCustomMarshaller<Address> {

    public AddressCustomMarshaller(String namespace) {
        super(Address.class, new AddressCustomProtoGenerator(namespace));
    }

    @Override
    public Address readFrom(MessageMarshaller.ProtoStreamReader reader) throws IOException {
        String[] address = reader.readString(ADDRESS_STRING_FIELD).split("\\|");
        return new Address(address[0], address[1], address[2], address[3]);
    }

    @Override
    public void writeTo(MessageMarshaller.ProtoStreamWriter writer, Address address) throws IOException {
        writer.writeString(ADDRESS_STRING_FIELD, address.getStreet() + "|" + address.getCity() + "|" +
                address.getZipCode() + "|" + address.getCountry());
    }

}
