package com.ghostchu.simplereloadlib;

/**
 * The reloading status.
 */
public enum ReloadStatus {
    /**
     * Reload successes
     */
    SUCCESS,
    /**
     * The object that registered now no reference and has been GC by Java.
     */
    OUTDATED,
    /**
     * Reload require the application restart
     */
    REQUIRE_RESTART,
    /**
     * Reload has been scheduled, waiting next call to affect
     */
    SCHEDULED,
    /**
     * Oof, reloading exploded, wtf
     */
    EXCEPTION,

}
