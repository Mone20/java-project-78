package hexlet.code.schema;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public abstract class Schema<T> {
    protected boolean required;
    private final List<Predicate<T>> conditions = new LinkedList<>();

    protected void addCondition(Predicate<T> condition) {
        if (condition == null)
            return;
        conditions.add(condition);
    }

    public boolean isValid(T value) {
        if (!requiredCheck(value)) {
            return !required && conditions.isEmpty();
        }
        for (Predicate<T> condition : conditions) {
            if (!condition.test(value)) {
                return false;
            }
        }
        return true;
    }

    protected boolean requiredCheck(T value) {
        return value != null;
    }

}
