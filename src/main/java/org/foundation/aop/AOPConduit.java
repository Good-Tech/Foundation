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
 * Created 2/3/13 7:44 AM
 */
public class AOPConduit extends Foundation
{
    private String id;
    private Label  name;
    private String actorId;
    private String subjectId;
    private String context;
    private String statement;



    public String getActorId()
    {

        return actorId;
    }


    public void setActorId(String actorId)
    {

        this.actorId = actorId;
    }


    public String getSubjectId()
    {

        return subjectId;
    }


    public void setSubjectId(String subjectId)
    {

        this.subjectId = subjectId;
    }


    public String getStatement()
    {

        return statement;
    }


    public void setStatement(String statement)
    {

        this.statement = statement;
    }


    public AOPConduit()
    {

        registerListnersOfClass(this);
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


    public String getContext()
    {

        return context;
    }


    public void setContext(String context)
    {

        this.context = context;
    }

    public static AOPConduit clone (AOPConduit conduit) {
        AOPConduit clone = new AOPConduit();
        clone.setContext(conduit.getContext());
        clone.setStatement(conduit.getStatement());
        clone.setActorId(conduit.getActorId());
        clone.setSubjectId(conduit.getSubjectId());

        return clone;
    }
}
