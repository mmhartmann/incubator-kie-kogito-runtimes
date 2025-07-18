package org.kie.kogito.codegen.process.persistence;

import org.junit.jupiter.api.Nested;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.data.FallbackSerializableClass;
import org.kie.kogito.codegen.data.NestedFallbackSerializableClass;
import org.kie.kogito.codegen.data.RegularSerializableClass;
import org.kie.kogito.codegen.process.persistence.proto.AbstractProtoGenerator;
import org.kie.kogito.codegen.process.persistence.proto.ProtoGenerator;
import org.kie.kogito.codegen.process.persistence.proto.ReflectionProtoGenerator;

import java.util.Arrays;

public class ReflectionSerializationFallbackTest extends AbstractSerializationFallbackTest<Class<?>> {

    @Override
    protected ProtoGenerator.Builder<Class<?>, ? extends AbstractProtoGenerator<Class<?>>> protoGeneratorBuilder(KogitoBuildContext context) {
        return ReflectionProtoGenerator.builder(context).withDataClasses(Arrays.asList(FallbackSerializableClass.class, NestedFallbackSerializableClass.class, RegularSerializableClass.class));
    }

    @Override
    protected Class<?> convertToType(Class<?> clazz) {
        return clazz;
    }
}
