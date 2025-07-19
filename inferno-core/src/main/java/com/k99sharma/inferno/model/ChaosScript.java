package com.k99sharma.inferno.model;

import lombok.Data;

import java.util.List;

@Data
public class ChaosScript {
    private List<ChaosStep> sequence;
}
