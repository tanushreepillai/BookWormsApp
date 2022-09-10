package com.kenzie.appserver.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PanelizationSummary {
    @JsonProperty("containsImageBubbles")
    private boolean containsImageBubbles;
    @JsonProperty("containsEpubBubbles")
    private boolean containsEpubBubbles;

    public boolean getContainsImageBubbles() {
        return containsImageBubbles;
    }

    public void setContainsImageBubbles(boolean containsImageBubbles) {
        this.containsImageBubbles = containsImageBubbles;
    }

    public boolean getContainsEpubBubbles() {
        return containsEpubBubbles;
    }

    public void setContainsEpubBubbles(boolean containsEpubBubbles) {
        this.containsEpubBubbles = containsEpubBubbles;
    }
}
