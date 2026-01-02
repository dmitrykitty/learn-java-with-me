package com.dnikitin.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum FlightStatus {
    ARRIVED("ARRIVED"),
    DEPARTED("DEPARTED"),
    SCHEDULED("SCHEDULED"),
    CANCELLED("CANCELLED");
    private final String label;

    public static FlightStatus fromLabel(String label) {
        if (label == null) {
            return null;
        }
        // Reverse Lookup
        return Arrays.stream(FlightStatus.values())
                .filter(status -> status.getLabel().equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown status label: " + label));
    }
}
