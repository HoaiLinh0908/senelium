package com.senelium.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Timeout {
    int pageLoad;
    int defaultElementWait;

    public static Timeout getDefault() {
        return new Timeout(60, 5);
    }
}
