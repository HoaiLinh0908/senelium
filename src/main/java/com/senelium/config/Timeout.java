package com.senelium.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Timeout {
    int pageLoad;
    int elementWait;
}
