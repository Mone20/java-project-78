package hexlet.code;

import hexlet.code.schema.MapBaseSchema;
import hexlet.code.schema.NumberBaseSchema;
import hexlet.code.schema.StringBaseSchema;

public final class Validator {

    public StringBaseSchema string() {
        return new StringBaseSchema();
    }

    public NumberBaseSchema number() {
        return new NumberBaseSchema();
    }

    public MapBaseSchema map() {
        return new MapBaseSchema();
    }
}
