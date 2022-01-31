package com.ghostchu.simplereloadlib;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

/**
 * This class is used to store the information of a reloadable class.
 */
@Data
@Builder
@AllArgsConstructor
public class ReloadableContainer {
    /**
     * The class that is reloadable. (Optional)
     * While this is null, reloadableMethod must be set.
     */
    @Nullable
    private WeakReference<Reloadable> reloadable;
    /**
     * The static method with no args and returns a ReloadResult value. (Optional)
     * While this is null, reloadable must be set.
     */
    @Nullable
    private Method reloadableMethod;

    /**
     * The reloadable is an object
     * @return true = reloadable shouldn't be null, false = reloadableMethod shouldn't be null
     */
    public boolean isObject() {
        return reloadable != null;
    }
}
