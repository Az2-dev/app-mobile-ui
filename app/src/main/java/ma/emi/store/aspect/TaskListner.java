package ma.emi.store.aspect;

import org.json.JSONException;

@FunctionalInterface
public interface TaskListner<T> {

    void runTask(T t) throws JSONException;

}
