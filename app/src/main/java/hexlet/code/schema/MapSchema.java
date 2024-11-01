package hexlet.code.schema;

import java.util.Map;

public class MapSchema extends Schema<Map<?,?>> {

    public MapSchema required() {
        this.required = true;
        return this;
    }

    public <T> MapSchema shape(Map<?, Schema<T>> schemasMap) {
        addCondition(map -> schemasMap.entrySet().stream().allMatch(schema -> {
            T value = (T) map.get(schema.getKey());
            var condition = schema.getValue();
            return condition.isValid(value);
        }));
        return this;
    }

    public MapSchema sizeOf(int size) {
        addCondition(value -> value.size() == size);
        return this;
    }
}
