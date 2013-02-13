package org.foundation.framework.mcl;



import org.goodgod.controller.Message;

/**
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
 * Created 1/30/13 4:42 AM
 */
public interface Controller
{
    /**
     * Scan a class for {@link org.goodgod.controller.MessageHandler} annotations and register them as callbacks in the message
     * controller.
     *
     * @param clazz   The class to register callbacks for.
     * @param context The execution context to run class listeners in.
     */
    public void registerListenersOfClass(Class clazz, Object context);

    /**
     * Unregister the methods of a class from the message controller.
     *
     * @param clazz The class to unregister callback methods for.
     */
    public void unregisterListenersOfClass(Class clazz);

    /**
     * Send a message to a message handler.
     *
     * @param handlerSet The handler set to invoke.
     */
    public void sendMessage(String handlerSet, Message message);

    /**
     * The group is auto reflected off of the message classes data.
     *
     * @param message
     */
    public void sendMessage(Message message);
}
