package hexlet.code.schema;

public class NumberSchema extends Schema<Integer> {

    public NumberSchema required() {
        this.required = true;
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