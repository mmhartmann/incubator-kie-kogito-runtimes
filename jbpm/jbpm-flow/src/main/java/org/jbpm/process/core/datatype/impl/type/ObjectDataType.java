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
package org.jbpm.process.core.datatype.impl.type;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

import org.jbpm.process.core.datatype.DataType;
import org.jbpm.process.core.datatype.DataTypeUtils;
import org.jbpm.process.core.datatype.impl.coverter.CloneHelper;
import org.jbpm.process.core.datatype.impl.coverter.TypeConverterRegistry;

/**
 * Representation of an object datatype.
 */
public class ObjectDataType implements DataType {

    private static final long serialVersionUID = 510l;
    private static final String DEFAULT_TYPE = "java.lang.Object";

    private String className;

    private transient ClassLoader classLoader;

    private transient Class<?> clazz;

    public ObjectDataType() {
        this(DEFAULT_TYPE);
    }

    public ObjectDataType(String className) {
        this(className, null);
    }

    public ObjectDataType(String className, ClassLoader classLoader) {
        Objects.requireNonNull(className);
        this.className = DataTypeUtils.ensureLangPrefix(className);
        this.classLoader = classLoader;
    }

    public ObjectDataType(Class<?> clazz) {
        this(clazz.getCanonicalName(), clazz.getClassLoader());
        this.clazz = clazz;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        className = (String) in.readObject();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(className);
    }

    public Object clone(Object value) {
        return CloneHelper.get().clone(value);
    }

    @Override
    public boolean verifyDataType(final Object value) {
        if (value == null) {
            return true;
        }
        getObjectClass();
        return clazz.isInstance(value);
    }

    @Override
    public Object readValue(String value) {
        return TypeConverterRegistry.get().forType(getStringType()).apply(value);
    }

    @Override
    public String writeValue(Object value) {
        return value.toString();
    }

    @Override
    public String getStringType() {
        return getClassName();
    }

    @Override
    public Class<?> getObjectClass() {
        if (clazz == null) {
            try {
                clazz = classLoader != null ? Class.forName(className, true, classLoader) : Class.forName(className);
            } catch (ClassNotFoundException ex) {
                throw new IllegalArgumentException(ex);
            }
        }
        return clazz;
    }

    @Override
    public boolean isAssignableFrom(DataType dataType) {
        return DataTypeUtils.isAssignableFrom(this, dataType) || className.equals(DEFAULT_TYPE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ObjectDataType that = (ObjectDataType) o;
        return Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className);
    }
}
