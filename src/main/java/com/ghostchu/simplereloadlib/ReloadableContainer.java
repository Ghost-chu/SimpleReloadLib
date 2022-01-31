/*
 * This file is a part of project QuickShop, the name is ReloadableContainer.java
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
