package hexlet.code.schemas;

import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        setRequired(true);
        return this;
    }

    public <T> MapSchema shape(Map<?, BaseSchema<T>> schemasMap) {
        addCondition(map -> schemasMap.entrySet().stream().allMatch(schema -> {
            T value = (T) map.get(schema.getKey());
            var condition = schema.getValue();
            return condition.isValid(value);
        }));
        return this;
    }

    public MapSchema sizeof(int size) {
        addCondition(value -> value.size() == size);
        return this;
    }
}
