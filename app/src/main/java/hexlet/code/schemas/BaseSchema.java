package hexlet.code.schemas;

import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    @Setter
    private boolean required;
    private final List<Predicate<T>> conditions = new LinkedList<>();

    protected final void addCondition(Predicate<T> condition) {
        if (condition == null) {
            return;
        }
        conditions.add(condition);
    }

    public final boolean isValid(T value) {
        if (!requiredCheck(value)) {
            return !required;
        }
        for (Predicate<T> condition : conditions) {
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
