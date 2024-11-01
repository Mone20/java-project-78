package hexlet.code.schemas;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        setRequired(true);
        return this;
    }

    public <T> MapSchema shape(Map<?, BaseSchema<T>> schemasMap) {
        addCondition(MapConditionNames.SHAPE.getName(), map -> schemasMap.entrySet().stream().allMatch(schema -> {
            T value = (T) map.get(schema.getKey());
            var condition = schema.getValue();
            return condition.isValid(value);
        }));
        return this;
    }

    public MapSchema sizeof(int size) {
        addCondition(MapConditionNames.SIZEOF.getName(), value -> value.size() == size);
        return this;
    }

    @RequiredArgsConstructor
    @Getter
    private enum MapConditionNames {
        SIZEOF("sizeof"),
        SHAPE("shape");
        private final String name;

    }
}
