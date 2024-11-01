package hexlet.code.schema;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        setRequired(true);
        return this;
    }

    public NumberSchema positive() {
        addCondition(value -> value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addCondition(value -> value >= min && value <= max);
        return this;
    }
}
