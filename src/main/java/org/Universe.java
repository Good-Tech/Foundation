package org;



import org.foundation.framework.mcl.ControllableEntityLayer;
import org.goodgod.controller.MessageController;

/**
 *
 * Select lancet for the lab mouse
 * Open the lancet wrapper.
 * Prepare a collection vile.
 *
 *
 *
 * Actor {
 *     Lab Mouse
 * }
 *
 * Actor.get({Lancet}).for({Lab Mouse})
 * Search actor for lab mouse
 *
 * Entity can be aware of a other objects.
 *
 * A researcher who has in a lab is operating on a lab mouse.
 * The relationships are as follows:
 * Lab has 1:N researcher
 * Lab has 1:N lab mouse
 *
 * Researcher has a lab mouse
 * Lab mouse
 *
 * Lancet is a tool
 * Researcher can use a tool
 *
 * Copyright (C) 2013 by Scott Byrns
 * http://github.com/scottbyrns
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p/>
 * Created 1/30/13 4:14 PM
 */
public class Universe extends MessageController
{
    public static <T extends ControllableEntityLayer> T manifest(Class<T> clazz) {
        try {
            return clazz.newInstance();
        }
        catch (IllegalAccessException e) {
            return null;
        }
        catch (InstantiationException e) {
            return null;
        }
    }

    public static <T extends ControllableEntityLayer> T manifest(Class<T> clazz, String uuid) {
        try {
            T out = clazz.newInstance();

            out.setInstanceId(uuid);
            return out;
        }
        catch (IllegalAccessException e) {
            return null;
        }
        catch (InstantiationException e) {
            return null;
        }
    }

}
