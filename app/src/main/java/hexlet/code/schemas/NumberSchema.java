package hexlet.code.schemas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        setRequired(true);
        return this;
    }

    public NumberSchema positive() {
        addCondition(NumberConditionNames.POSITIVE.getName(), value -> value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCondition(NumberConditionNames.RANGE.getName(), value -> value >= min && value <= max);
        return this;
    }

    @RequiredArgsConstructor
    @Getter
    private enum NumberConditionNames {
        RANGE("range"),
        POSITIVE("positive");
        private final String name;

    }
}
