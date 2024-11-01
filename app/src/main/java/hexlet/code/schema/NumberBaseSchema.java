package hexlet.code.schema;

public final class NumberBaseSchema extends BaseSchema<Integer> {

    public NumberBaseSchema required() {
        setRequired(true);
        return this;
    }

    public NumberBaseSchema positive() {
        addCondition(value -> value > 0);
        return this;
    }

    public NumberBaseSchema range(int min, int max) {
        addCondition(value -> value >= min && value <= max);
        return this;
    }
}
