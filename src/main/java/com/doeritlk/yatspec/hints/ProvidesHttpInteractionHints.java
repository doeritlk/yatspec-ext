package com.doeritlk.yatspec.hints;

public interface ProvidesHttpInteractionHints {

    default String aRequest(String what, String from, String to) {
        return String.format("%s request from %s to %s", what, from, to);
    }

    default String aResponse(String what, String from, String to) {
        return String.format("%s response from %s to %s", what, from, to);
    }

    default String anAcknowledgement(String what, String from, String to) {
        return String.format("Acknowledgement for %s from %s to %s", what, from, to);
    }

}
