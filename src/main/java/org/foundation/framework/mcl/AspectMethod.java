package org.foundation.framework.mcl;



import org.foundation.Foundation;
import org.foundation.aop.Input;
import org.foundation.aop.Output;
import org.goodgod.controller.Message;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
 * Created 2/28/13 4:19 PM
 */
public class AspectMethod
{
    private List<Input>  inputs  = new ArrayList<Input>();
    private Output output;

    private Method method;

    private Foundation context;


    public AspectMethod(Method method, Output output)
    {

        this.method = method;

        for (Class clazz : method.getParameterTypes()) {
            addInput(new Input(clazz));
        }

        output = new Output(method.getReturnType());


    }


    private void compute () {
        try {
            // TODO pass inputs to method invoke.
            Object result = method.invoke(context);
            context.sendMessage(method.getName(),
                                Message.create(context.getId(), result));
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {

        }
    }


    public void addInput(Input input)
    {

        inputs.add(input);

        if (inputsAreSatisfied()) {
            compute();
        }
    }

    private boolean inputsAreSatisfied () {
        return false;
    }


    public void addOutput(Output output)
    {

    }
}
