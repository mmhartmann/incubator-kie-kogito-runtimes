package org.kie.kogito.codegen.data;

import org.kie.kogito.codegen.process.persistence.marshaller.AbstractCustomMarshaller;

import java.io.IOException;
import java.util.List;

import static org.kie.kogito.codegen.data.AddressCustomProtoGenerator.*;

public class AddressCustomMarshaller extends AbstractCustomMarshaller<Address> {

    public AddressCustomMarshaller() {
        super(new AddressCustomProtoGenerator());
    }

    @Override
    public Address readFrom(ProtoStreamReader reader) throws IOException {
        String[] address = reader.readString(ADDRESS_STRING_FIELD).split("\\|");
        return new Address(address[0], address[1], address[2], address[3]);
    }

    @Override
    public void writeTo(ProtoStreamWriter writer, Address address) throws IOException {
        writer.writeString(ADDRESS_STRING_FIELD, address.getStreet() + "|" + address.getCity() + "|" +
                address.getZipCode() + "|" + address.getCountry()
        );
    }

}
