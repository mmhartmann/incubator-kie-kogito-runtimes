/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.kie.kogito.codegen.process.persistence;

import java.util.Collections;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.kie.kogito.codegen.api.context.KogitoBuildContext;
import org.kie.kogito.codegen.api.context.impl.JavaKogitoBuildContext;
import org.kie.kogito.codegen.process.persistence.proto.*;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractSerializationFallbackTest<T> {

    protected abstract ProtoGenerator.Builder<T, ? extends AbstractProtoGenerator<T>> protoGeneratorBuilder(KogitoBuildContext context);

    protected abstract T convertToType(Class<?> clazz);

    @Test
    void testSerializationFallbackWithProtoGeneration() {
        Properties props = new Properties();
        props.setProperty(SerializationFallbackUtils.KOGITO_PERSISTANCE_FALLBACK_PATTERN,
                "org\\.kie\\.kogito\\.codegen\\.data\\.[F-N].*");

        KogitoBuildContext context = JavaKogitoBuildContext.builder().withApplicationProperties(props).build();

        AbstractProtoGenerator<T> generator = protoGeneratorBuilder(context).build(Collections.emptyList());

        Proto proto = generator.protoOfDataClasses("org.kie.kogito.test");
        assertThat(proto).isNotNull();
        assertThat(proto.getMessages().size()).isEqualTo(1);
    }

    @Test
    void testRegularSerializationWithProtoGeneration() {
        Properties props = new Properties();
        props.setProperty(SerializationFallbackUtils.KOGITO_PERSISTANCE_FALLBACK_PATTERN,
                "org\\.kie\\.kogito\\.codegen\\.data\\.[F-N].*");

        KogitoBuildContext context = JavaKogitoBuildContext.builder()
                .withApplicationProperties(props)
                .build();

        AbstractProtoGenerator<T> generator = protoGeneratorBuilder(context).build(Collections.emptyList());

        Proto proto = generator.protoOfDataClasses("org.kie.kogito.test");
        assertThat(proto).isNotNull();
        assertThat(proto.getMessages()).hasSize(1);

        ProtoMessage message = proto.getMessages().get(0);
        assertThat(message.getName()).isEqualTo("RegularSerializableClass");
        assertThat(message.getFields()).hasSize(3);

        ProtoField descriptionField = message.getFields().stream()
                .filter(f -> f.getName().equals("description"))
                .findFirst()
                .orElseThrow();
        assertThat(descriptionField.getType()).isEqualTo("string");

        ProtoField countField = message.getFields().stream()
                .filter(f -> f.getName().equals("count"))
                .findFirst()
                .orElseThrow();
        assertThat(countField.getType()).isEqualTo("int32");

        ProtoField enabledField = message.getFields().stream()
                .filter(f -> f.getName().equals("enabled"))
                .findFirst()
                .orElseThrow();
        assertThat(enabledField.getType()).isEqualTo("bool");
    }

    @Test
    void testMixedClassesWithFallbackPattern() {
        Properties props = new Properties();
        props.setProperty(SerializationFallbackUtils.KOGITO_PERSISTANCE_FALLBACK_PATTERN,
                "org\\.kie\\.kogito\\.codegen\\.data\\.NestedFallbackSerializableClass");

        KogitoBuildContext context = JavaKogitoBuildContext.builder()
                .withApplicationProperties(props)
                .build();

        AbstractProtoGenerator<T> generator = protoGeneratorBuilder(context).build(Collections.emptyList());

        Proto proto = generator.protoOfDataClasses("org.kie.kogito.test");
        assertThat(proto).isNotNull();

        assertThat(proto.getMessages()).hasSize(2);

        ProtoMessage fallbackMessage = proto.getMessages().stream()
                .filter(m -> m.getName().equals("FallbackSerializableClass"))
                .findFirst()
                .orElseThrow();
        assertThat(fallbackMessage.getFields()).hasSize(5);

        // Check that the nested field uses kogito.Serializable instead of generating a NestedClass message
        ProtoField nestedField = fallbackMessage.getFields().stream()
                .filter(f -> f.getName().equals("nested"))
                .findFirst()
                .orElseThrow();
        assertThat(nestedField.getType()).isEqualTo("kogito.Serializable");
        assertThat(nestedField.getOption()).contains("kogito_java_class");
        assertThat(nestedField.getOption()).contains("org.kie.kogito.codegen.data.NestedFallbackSerializableClass");

        ProtoMessage regularMessage = proto.getMessages().stream()
                .filter(m -> m.getName().equals("RegularSerializableClass"))
                .findFirst()
                .orElseThrow();
        assertThat(regularMessage.getFields()).hasSize(3);
    }

    @Test
    void testNoFallbackPatternGeneratesNormalProto() {
        KogitoBuildContext context = JavaKogitoBuildContext.builder().build();

        AbstractProtoGenerator<T> generator = protoGeneratorBuilder(context).build(Collections.emptyList());

        Proto proto = generator.protoOfDataClasses("org.kie.kogito.test");
        assertThat(proto).isNotNull();
        assertThat(proto.getMessages()).hasSize(3);

        ProtoMessage fallbackMessage = proto.getMessages().stream()
                .filter(m -> m.getName().equals("FallbackSerializableClass"))
                .findFirst()
                .orElseThrow();
        assertThat(fallbackMessage.getFields()).hasSize(5);

        ProtoMessage nestedMessage = proto.getMessages().stream()
                .filter(m -> m.getName().equals("NestedFallbackSerializableClass"))
                .findFirst()
                .orElseThrow();
        assertThat(nestedMessage.getFields()).hasSize(2);

        ProtoMessage regularMessage = proto.getMessages().stream()
                .filter(m -> m.getName().equals("RegularSerializableClass"))
                .findFirst()
                .orElseThrow();
        assertThat(regularMessage.getFields()).hasSize(3);
    }
}