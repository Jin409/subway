package subway.view;

import java.util.Arrays;

public enum FinishSign {
    FINISH("Q"),
    NOT_FINISH("1");

    private final String sign;

    FinishSign(String sign) {
        this.sign = sign;
    }

    public static FinishSign findBySign(String comparedSign) {
        return Arrays.stream(FinishSign.values())
                .filter(f -> f.sign.equals(comparedSign))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사인입니다."));
    }

    public boolean meansFinish() {
        return this.equals(FINISH);
    }
}
