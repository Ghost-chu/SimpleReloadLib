/*
 * This file is a part of project QuickShop, the name is ReloadResult.java
 *  Copyright (C) PotatoCraft Studio and contributors
 *
 *  This program is free software: you can redistribute it and/or modify it
 *  under the terms of the GNU General Public License as published by the
 *  Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful, but WITHOUT
 *  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 *  for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

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
