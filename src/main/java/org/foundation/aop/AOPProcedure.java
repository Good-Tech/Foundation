package org.foundation.aop;



import org.foundation.Foundation;
import org.foundation.ml.nlp.Label;

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
 * Created 2/3/13 8:11 AM
 */
public class AOPProcedure extends Foundation
{
    private String id;
    private Label  name;

    private List<AOPConduit> aopConduits = new ArrayList<AOPConduit>();

    public void addConduit(AOPConduit conduit) {
        aopConduits.add(conduit);
    }

    public void run () {
        for (AOPConduit aopConduit : aopConduits) {
            AOPRuntime.execute(aopConduit);
        }
    }


    public AOPProcedure()
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


}
