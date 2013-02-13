package org.foundation.aop;



import org.foundation.Foundation;
import org.foundation.ml.Label;

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
 * Created 2/3/13 7:57 AM
 */
public class AOPRuntime extends Foundation
{
    private String id;
    private Label  name;


    /**
     * Execute an AOP Conduit.
     *
     * @param conduit The conduit to execute.
     */
    public static void execute(AOPConduit conduit) {
        Foundation actor = getFoundationById(conduit.getActorId());
        Foundation subject = getFoundationById(conduit.getSubjectId());
        Foundation context = getFoundationById(conduit.getContext());

        String debug = "\n".concat(actor.getClass().getSimpleName()).concat(" says to ").concat(context.getClass().getSimpleName()).concat(" \"").concat(conduit.getStatement()).concat("\" using this ").concat(subject.getClass().getSimpleName());
        System.out.println(debug);

        context.says(conduit.getStatement()).like(subject);
    }

    public String getId()
    {

        return id;
    }


    public void setId(String id)
    {

        this.id = id;
    }


    public Label getName()
    {

        return name;
    }


    public void setName(Label name)
    {

        this.name = name;
    }
}
