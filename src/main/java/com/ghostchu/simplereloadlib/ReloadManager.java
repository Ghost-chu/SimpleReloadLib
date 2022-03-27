package com.ghostchu.simplereloadlib;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.*;

/**
 * ReloadManager controls modules to reloading while needed
 * <p>
 * Register order is reloading order preventing unexpected behavior.
 */
public class ReloadManager {
    private final List<ReloadableContainer> registry = Collections.synchronizedList(new LinkedList<>());

    /**
     * Register a reloadable module into reloading registery
     *
     * @param reloadable Reloadable module
     */
    public synchronized void register(@NotNull Reloadable reloadable) {
        unregister(reloadable);
        this.registry.add(new ReloadableContainer(new WeakReference<>(reloadable), null));
    }

    /**
     * Register a reloadable module into reloading registery
     *
     * @param reloadMethod Reloadable module
     */
    public synchronized void register(@NotNull Method reloadMethod) {
        unregister(reloadMethod);
        this.registry.add(new ReloadableContainer(null, reloadMethod));
    }

    /**
     * Register a reloadable module into reloading registery
     *
     * @param reloadMethod Reloadable module
     */
    public synchronized void unregister(@NotNull Method reloadMethod) {
        this.registry.removeIf(reloadableContainer -> {
            if (reloadableContainer.getReloadableMethod() != null) {
                Method method = reloadableContainer.getReloadableMethod();
                return reloadMethod.equals(method);
            } else {
                return false;
            }
        });
    }


    /**
     * Unregister a reloadable module from reloading registry
     *
     * @param reloadable Reloadable module
     */
    public synchronized void unregister(@NotNull Reloadable reloadable) {
        this.registry.removeIf(reloadableContainer -> {
            if (reloadableContainer != null) {
                if (reloadableContainer.getReloadable() != null) {
                    return Objects.equals(reloadableContainer.getReloadable().get(), reloadable);
                }
            }
            return false;
        });
    }

    /**
     * Unregister all reloadable modules that same with specific class from reloading registry
     *
     * @param clazz Class that impl reloadable
     */
    public synchronized void unregister(@NotNull Class<Reloadable> clazz) {
        this.registry.removeIf(reloadable -> {
            if (reloadable.getReloadable() != null) {
                Reloadable rable = reloadable.getReloadable().get();
                if (rable != null) {
                    return clazz.equals(rable.getClass());
                }
                return false;
            }
            if (reloadable.getReloadableMethod() != null) {
                Method method = reloadable.getReloadableMethod();
                if (method != null) {
                    return clazz.equals(method.getDeclaringClass());
                }
            }
            return false;
        });
    }

    /**
     * Reload all reloadable modules
     *
     * @return Reloading results
     */
    @NotNull
    public synchronized Map<ReloadableContainer, ReloadResult> reload() {
        return reload(null);
    }

    /**
     * Reload all reloadable modules that equals specific class
     *
     * @param clazz The class that impl reloadable
     * @return Reloading results
     */
    @NotNull
    public synchronized Map<ReloadableContainer, ReloadResult> reload(@Nullable Class<Reloadable> clazz) {
        Map<ReloadableContainer, ReloadResult> reloadResultMap = new LinkedHashMap<>();
        Iterator<ReloadableContainer> iterator = this.registry.iterator();
        while (iterator.hasNext()) {
            ReloadableContainer reloadContainer = iterator.next();
            Reloadable reloadable = null;
            Method reloadMethod = null;
            if (reloadContainer.getReloadable() != null)
                reloadable = reloadContainer.getReloadable().get();
            if (reloadContainer.getReloadableMethod() != null)
                reloadMethod = reloadContainer.getReloadableMethod();
            if (reloadable == null && reloadMethod == null) {
                reloadResultMap.put(reloadContainer, new ReloadResult(ReloadStatus.OUTDATED, "Container object has been outdated", null));
                iterator.remove();
                continue;
            }
            ReloadResult reloadResult = new ReloadResult(ReloadStatus.EXCEPTION, "Incorrect reload object", null);
            try {
                if (reloadable != null) {
                    if (clazz != null && !clazz.equals(reloadable.getClass())) {
                        continue;
                    }
                    reloadResult = reloadable.reloadModule();
                }
                if (reloadMethod != null) {
                    if (clazz != null && !clazz.equals(reloadMethod.getDeclaringClass())) {
                        continue;
                    }
                    reloadResult = (ReloadResult) reloadMethod.invoke(null);
                }
                reloadResultMap.put(reloadContainer, reloadResult);
            } catch (Exception e) {
                reloadResultMap.put(reloadContainer, new ReloadResult(ReloadStatus.EXCEPTION, "Reloading failed", e));
            }
        }
        return reloadResultMap;
    }
}
