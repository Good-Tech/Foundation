package org.foundation.aop;



import org.foundation.Foundation;
import org.goodgod.controller.Action;
import org.goodgod.controller.ScopedMethod;
import org.goodgod.controller.Statement;
import org.goodgod.controller.When;

import java.lang.reflect.Method;
import java.util.Map;

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
 * Created 2/28/13 12:51 PM
 */
public class AspectController
{
    private Map<Statement, ScopedMethod> statements;
    private Map<Action, ScopedMethod>    actions;
    private Map<When, ScopedMethod>      reactions;


    public AspectController(Class controllerClass, final Foundation context)
    {

        Method[] clazzMethods = controllerClass.getMethods();
        for (Method method : clazzMethods)
        {

            final Action action = method.getAnnotation(Action.class);
            final Statement statement = method.getAnnotation(Statement.class);
            final When when = method.getAnnotation(When.class);

            if (null != action) {
                ScopedMethod scopedMethod = new ScopedMethod(context, method, context.getId(), action.documentation());
            }
        }
    }
}
