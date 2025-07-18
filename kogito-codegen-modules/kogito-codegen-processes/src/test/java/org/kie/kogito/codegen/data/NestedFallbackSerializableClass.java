package org.kie.kogito.codegen.data;

import java.io.Serializable;

public class NestedFallbackSerializableClass implements Serializable {
    private String value;
    private double score;

    public NestedFallbackSerializableClass() {
    }

    public NestedFallbackSerializableClass(String value, double score) {
        this.value = value;
        this.score = score;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
