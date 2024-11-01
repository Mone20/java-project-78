package hexlet.code.schemas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        setRequired(true);
        return this;
    }

    @Override
    protected boolean requiredCheck(String value) {
        return super.requiredCheck(value) && !value.isEmpty();
    }

    public StringSchema contains(String containsValue) {
        addCondition(StringConditionNames.CONTAINS.getName(), value -> value.contains(containsValue));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCondition(StringConditionNames.MIN_LENGTH.getName(), value -> value.length() >= minLength);
        return this;
    }

    @RequiredArgsConstructor
    @Getter
    private enum StringConditionNames  {
        MIN_LENGTH("minLength"),
        CONTAINS("contains");
        private final String name;

    }
}
