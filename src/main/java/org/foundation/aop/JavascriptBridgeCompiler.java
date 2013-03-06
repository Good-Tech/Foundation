package org.foundation.aop;



import org.foundation.Foundation;
import org.goodgod.controller.Action;
import org.goodgod.controller.Statement;
import org.goodgod.controller.When;

import java.lang.reflect.Method;

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
 * Created 2/28/13 3:36 AM
 */
public class JavascriptBridgeCompiler
{

    public static String compileController (Class controllerClass, final Foundation executionContext) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Controller.").append(controllerClass.getSimpleName());
        stringBuilder.append(" = {");

        Method[] clazzMethods = controllerClass.getMethods();
        for (Method method : clazzMethods)
        {

            final Action action = method.getAnnotation(Action.class);
            final Statement statement = method.getAnnotation(Statement.class);
            final When when = method.getAnnotation(When.class);

            if (null != action) {
                stringBuilder.append("\"").append(action.name()).append("\" : function () {")
                             .append("Foundation.MessageController.sendMessage('").append(executionContext.getId()).append("', '").append(action.name()).append("');")

                             .append("},");
            }

            if (null != statement) {
                stringBuilder.append("\"").append(statement.name()).append("\" : function () {")
                             .append("Foundation.MessageController.sendMessage('").append(executionContext.getId()).append("', '").append(statement.name()).append("');")

                             .append("},");
            }

            if (null != when) {
                stringBuilder.append("\"").append(when.name()).append("\" : function () {")
                             .append("Foundation.MessageController.sendMessage('").append(executionContext.getId()).append("', '").append(when.name()).append("');")

                             .append("},");
            }


        }

        stringBuilder.append("};");

        return stringBuilder.toString();

    }
}
