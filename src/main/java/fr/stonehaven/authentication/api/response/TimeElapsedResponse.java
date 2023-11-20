package fr.stonehaven.authentication.api.response;

import lombok.Getter;

@Getter
public class TimeElapsedResponse {

    private final long time;

    public TimeElapsedResponse(long start) {
        this.time = System.currentTimeMillis() - start;
    }
}
