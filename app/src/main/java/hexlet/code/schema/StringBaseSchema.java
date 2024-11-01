package hexlet.code.schema;

public final class StringBaseSchema extends BaseSchema<String> {

    public StringBaseSchema required() {
        setRequired(true);
        return this;
    }


    @Override
    protected boolean requiredCheck(String value) {
        return super.requiredCheck(value) && !value.isEmpty();
    }

    public StringBaseSchema contains(String containsValue) {
        addCondition(value -> value.contains(containsValue));
        return this;
    }

    public StringBaseSchema minLength(int minLength) {
        addCondition(value -> value.length() >= minLength);
        return this;
    }
}
