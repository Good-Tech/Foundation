package org.universe;



import org.foundation.Foundation;
import org.foundation.aop.InstanceRelationshipObserver;
import org.foundation.ml.Label;
import org.goodgod.controller.Message;

import javax.persistence.Entity;
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
 * Created 2/2/13 4:49 AM
 */
@Entity
public class Relationship extends Foundation
{

    private String id;
    private Label  label;

    private List<Has> hasList;
    private List<Is> isList;

    private Label name;


    public Label getName()
    {

        return name;
    }


    public void setName(Label name)
    {

        this.name = name;
    }



    public Relationship()
    {
        registerListnersOfClass(this);
        registerListnersOfClass(InstanceRelationshipObserver.getInstance());
    }


    public String getId()
    {

        return id;
    }


    public void setId(String id)
    {

        this.id = id;
    }


    public Label getLabel()
    {

        return label;
    }


    public void setLabel(Label label)
    {

        this.label = label;
    }


    public List<Has> getHasList()
    {

        return hasList;
    }


    public void setHasList(List<Has> hasList)
    {

        this.hasList = hasList;
    }


    public List<Is> getIsList()
    {

        return isList;
    }


    public void setIsList(List<Is> list)
    {

        isList = list;
    }

    public void addHasA(Has has) {
        getHasList().add(has);
        sendMessage("add",
                    Message.create("has-a-relationship", this));

//        with(has).does("add", "has-a-relationship").to(this);
    }

    public void addIsA(Is is) {
        getIsList().add(is);
        sendMessage("add",
                    Message.create("is-a-relationship", this));
//        with(is).does("add", "is-a-relationship").to(this);
    }
}
