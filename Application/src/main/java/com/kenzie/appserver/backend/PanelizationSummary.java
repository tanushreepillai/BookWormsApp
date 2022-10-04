package com.kenzie.appserver.backend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PanelizationSummary {
    @JsonProperty("containsImageBubbles")
    public boolean containsImageBubbles;
    @JsonProperty("containsEpubBubbles")
    public boolean containsEpubBubbles;
}
