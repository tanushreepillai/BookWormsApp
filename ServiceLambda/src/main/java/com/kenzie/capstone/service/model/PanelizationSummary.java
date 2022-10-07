package com.kenzie.capstone.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PanelizationSummary {
    @JsonProperty("containsImageBubbles")
    public boolean containsImageBubbles;
    @JsonProperty("containsEpubBubbles")
    public boolean containsEpubBubbles;
}
