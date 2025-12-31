package com.dnikitin.jdbc.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Status {
    ARRIVED("ARRIVED"),
    DEPARTED("DEPARTED"),
    SCHEDULED("SCHEDULED"),
    CANCELLED("CANCELLED");
    private final String label;

    public static Status fromLabel(String label) {
        if (label == null) {
            return null;
        }
        // Reverse Lookup
        return Arrays.stream(Status.values())
                .filter(status -> status.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown status label: " + label));
    }
}