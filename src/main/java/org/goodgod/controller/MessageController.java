package org.goodgod.controller;



import org.codehaus.jackson.annotate.JsonIgnore;
import org.foundation.framework.mcl.Controller;
import org.foundation.ml.NLP;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Aspect messaging between disparate class methods through out an application.
 *
 *
 * <p/>
 * Messages that are handled have all execution errors mucked to prevent an unwanted message handler from crashing your
 * software.
 * <p/>
 * <p/>
 * Copyright (C) 2012 by Scott Byrns
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
 * Created 7/17/12 10:54 AM
 */
public class MessageController implements Controller
{

    @JsonIgnore
    private Map<String, List<ScopedMethod>> methods = new HashMap<String, List<ScopedMethod>>();


    private Map<String, List<ScopedMethod>> getMethods()
    {

        return methods;
    }


    private void setMethods(Map<String, List<ScopedMethod>> methods)
    {

        this.methods = methods;
    }


    /**
     * Get a list of the available groups in the message controller.
     *
     * @return A list of available groups.
     */
    public List<String> getAvailableGroups()
    {

        return new ArrayList<String>(methods.keySet());
    }

    public List<String> getAcceptableTypes (String group) {
        List<ScopedMethod> scopedMethods = getAvailableChannelsForGroup(group);

        List<String> strings = new ArrayList<String>();

        try {

            for (ScopedMethod scopedMethod : scopedMethods) {
                System.out.println(scopedMethod.getMethod().getParameterTypes()[0].getSimpleName());
                strings.add(scopedMethod.getMethod().getParameterTypes()[0].getSimpleName());
            }

        }
        catch (NullPointerException e) {

        }

        return strings;

    }

    public List<String> getChannels (String group) {
        List<ScopedMethod> scopedMethods = getAvailableChannelsForGroup(group);

        List<String> strings = new ArrayList<String>();

        try {

            for (ScopedMethod scopedMethod : scopedMethods) {
                System.out.println(scopedMethod.getChannel());
                System.out.println("  " + scopedMethod.getDocumentation() + "\n");

                strings.add(scopedMethod.getChannel());
            }

        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }

        return strings;

    }


    public List<ScopedMethod> getAvailableChannelsForGroup(String group)
    {
        return methods.get(group);
    }


    /**
     * Register a listener.
     *
     * @param messageHandler Annotated description of message registration.
     * @param callback           The method to callback to when a message is sent.
     * @param context            The execution context to execute a method callback in.
     */
    protected void registerListener(MessageHandler messageHandler, Method callback, Object context)
    {

        List<ScopedMethod> methodList;
        ScopedMethod scopedMethod = new ScopedMethod(context,
                                                     callback,
                                                     messageHandler.channel(),
                                                     messageHandler.documentation());

        if (null != getMethods().get(messageHandler.group()))
        {
            methodList = getMethods().get(messageHandler.group());
            methodList.add(scopedMethod);
        }
        else
        {
            methodList = new ArrayList<ScopedMethod>();
            methodList.add(scopedMethod);
            getMethods().put(messageHandler.group(),
                             methodList);
        }

    }


    /**
     * Scan a class for {@link MessageHandler} annotations and register them as callbacks in the message
     * controller.
     *
     * @param clazz   The class to register callbacks for.
     * @param context The execution context to run class listeners in.
     */
    public void registerListenersOfClass(Class clazz, Object context)
    {

        Method[] clazzMethods = clazz.getMethods();
        for (Method method : clazzMethods)
        {
            MessageHandler messageHandler = method.getAnnotation(MessageHandler.class);
            if (null == messageHandler)
            {
                continue;
            }


            registerListener(messageHandler,
                             method,
                             context);
        }
    }


    public void unregisterListenersOfClass(Class clazz)
    {

        Method[] clazzMethods = clazz.getMethods();
        for (Method method : clazzMethods)
        {
            MessageHandler messageHandler = method.getAnnotation(MessageHandler.class);
            if (null == messageHandler)
            {
                continue;
            }


            unregisterListener(messageHandler,
                               method);
        }
    }


    private void unregisterListener(MessageHandler messageHandler, Method method)
    {

        List<ScopedMethod> scopedMethods = getMethods().get(messageHandler.group());
        Iterator<ScopedMethod> iterator = scopedMethods.iterator();

        ScopedMethod scopedMethod;
        ScopedMethod methodToRemove = null;
        while (iterator.hasNext())
        {
            scopedMethod = iterator.next();
            if (scopedMethod.getMethod().toGenericString().equals(method.toGenericString()))
            {
                methodToRemove = scopedMethod;
            }
        }
        scopedMethods.remove(methodToRemove);
    }

    public void sendMessage(Message message) {
        sendMessage(message.getData().getClass().getSimpleName(), message);
    }

    /**
     * Send a message to a message handler.
     *
     * @param key The handler set to invoke.
     */
    public void sendMessage(String key, Message message)
    {
        System.out.println("Group Analysis: ".concat(key).concat(" : ").concat(NLP.getInstance().partsOfSpeach(key)));


        System.out.println("".concat(getClass().getSimpleName().replace("Controller",
                                                                                                                 "").toUpperCase()).concat(" says ").concat(key.toUpperCase()).concat(" ").concat(message.getChannel().toUpperCase()));


        List<ScopedMethod> methodList = getMethods().get(key);
        if (null == methodList)
        {
            return;
        }
        Iterator<ScopedMethod> methodIterator = methodList.iterator();
        ScopedMethod scopedMethod;
        while (methodIterator.hasNext())
        {
            try
            {
                scopedMethod = methodIterator.next();


                scopedMethod.invoke(message);
            }
            catch (Throwable e)
            {
//                e.printStackTrace();
                // Muck all exceptions.
            }
        }
    }


    /**
     * Send a message to a message handler.
     *
     * @param key The handler set to invoke.
     */
    public void sendMessage(String key, Object message)
    {

        List<ScopedMethod> methodList = getMethods().get(key);
        if (null == methodList)
        {
            return;
        }
        Iterator<ScopedMethod> methodIterator = methodList.iterator();
        ScopedMethod scopedMethod;
        while (methodIterator.hasNext())
        {
            try
            {
                scopedMethod = methodIterator.next();
                scopedMethod.invoke(Message.create(message));
            }
            catch (Throwable e)
            {
                e.printStackTrace();
                // Muck all exceptions.
            }
        }
    }
}