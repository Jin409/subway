package subway.domain;

import java.util.Arrays;

public enum Option {
    LEAST_DISTANCE("1"),
    MIN_TIME("2"),
    GO_BACK("B");

    private final String sign;

    Option(String sign) {
        this.sign = sign;
    }

    public static Option findBySign(String comparedSign) {
        return Arrays.stream(Option.values())
                .filter(o -> o.sign.equals(comparedSign))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사인입니다."));
    }

    public boolean meansLeastDistance() {
        return this == LEAST_DISTANCE;
    }

    public boolean meansMinTime() {
        return this == MIN_TIME;
    }

    public boolean meansGoBack() {
        return this == GO_BACK;
    }
}
