package com.ghostchu.simplereloadlib;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ReloadResult that contains the result of reloading a plugin.
 */
@Data
@Builder
@AllArgsConstructor
public class ReloadResult {
    /**
     * Reload status that module returned.
     */
    @NotNull
    private ReloadStatus status;
    /**
     * The reason of reloading. (Optional)
     */
    @Nullable
    private String reason;
    /**
     * The exception that occurred during reloading. (Optional)
     */
    @Nullable
    private Throwable exception;

}
