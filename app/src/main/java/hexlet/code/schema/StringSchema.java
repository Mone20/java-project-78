package hexlet.code.schema;

public class StringSchema extends Schema<String> {

    public StringSchema required() {
        this.required = true;
        return this;
    }


    @Override
    protected boolean requiredCheck(String value) {
        return super.requiredCheck(value) && !value.isEmpty();
    }

    public StringSchema contains(String containsValue) {
        addCondition(value -> value.contains(containsValue));
        return this;
    }

    public StringSchema minLength(int minLength) {
        addCondition(value -> value.length() >= minLength);
        return this;
    }
}
