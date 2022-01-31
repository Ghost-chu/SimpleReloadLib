package com.ghostchu.simplereloadlib;

/**
 * Interface that allow class register to ReloadManager
 */
public interface Reloadable {
    /**
     * Callback for reloading
     *
     * @return Reloading success
     * @throws Exception Throws error if module failed to process reloading
     */
    default ReloadResult reloadModule() throws Exception {
        return ReloadResult.builder().status(ReloadStatus.SUCCESS).build();
    }


}
