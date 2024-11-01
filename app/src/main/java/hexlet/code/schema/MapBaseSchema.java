package hexlet.code.schema;

import java.util.Map;

public final class MapBaseSchema extends BaseSchema<Map<?, ?>> {

    public MapBaseSchema required() {
        setRequired(true);
        return this;
    }

    public <T> MapBaseSchema shape(Map<?, BaseSchema<T>> schemasMap) {
        addCondition(map -> schemasMap.entrySet().stream().allMatch(schema -> {
            T value = (T) map.get(schema.getKey());
            var condition = schema.getValue();
            return condition.isValid(value);
        }));
        return this;
    }

    public MapBaseSchema sizeof(int size) {
        addCondition(value -> value.size() == size);
        return this;
    }
}
