package hexlet.code.schemas;

import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    @Setter
    private boolean required;
    private final Map<String, Predicate<T>> conditions = new LinkedHashMap<>();

    protected final void addCondition(String conditionName, Predicate<T> condition) {
        if (condition == null) {
            return;
        }
        conditions.put(conditionName, condition);
    }

    public final boolean isValid(T value) {
        if (!requiredCheck(value)) {
            return !required;
        }
        for (Predicate<T> condition : conditions.values()) {
            if (!condition.test(value)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Common realization of the required check.
     *
     * @param value - value for check.
     * @return result of the check.
     */
    protected boolean requiredCheck(T value) {
        return value != null;
    }

}
