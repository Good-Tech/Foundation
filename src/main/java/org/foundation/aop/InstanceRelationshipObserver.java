package org.foundation.aop;



import com.scottbyrns.utilities.FatalMappingException;
import com.scottbyrns.utilities.JSONObjectMapper;
import org.foundation.Foundation;
import org.foundation.ml.nlp.Label;
import org.goodgod.controller.Message;
import org.goodgod.controller.MessageHandler;
import org.universe.Relationship;

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
 * Created 2/2/13 10:14 PM
 */
public class InstanceRelationshipObserver extends Foundation
{



    private String id;
    private Label  name;

    private static InstanceRelationshipObserver instance;


    public static InstanceRelationshipObserver getInstance()
    {
        if (null == instance) {
            setInstance(new InstanceRelationshipObserver());
            Label label = new Label();
            label.setValue("Instance Relationship Observer");
            instance.setName(label);
        }

        return instance;
    }


    private static void setInstance(InstanceRelationshipObserver instance)
    {

        InstanceRelationshipObserver.instance = instance;
    }


    private InstanceRelationshipObserver()
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

    @MessageHandler(
            documentation = "Add a has a relationship to the observer.",
            group = "add",
            channel = "has-a-relationship"
    )
    public void addHasARelationship (Message<Relationship> relationship) {
        System.out.println("\n\n");
        System.out.println("Observer: Added a has a relationship.");
        try {
            System.out.println(relationship.getData().getName().getValue());
        }
        catch (NullPointerException e) {

        }
        try {
            System.out.println(JSONObjectMapper.convertEntityToJSON(
                    relationship
                                                                   ));
        }
        catch (FatalMappingException e) {

        }
        catch (NullPointerException e) {

        }
        System.out.println("\n\n");
    }

    @MessageHandler(
            documentation = "Add an is a relationship to the observer.",
            group = "add",
            channel = "is-a-relationship"
    )
    public void addIsARelationship (Message<Relationship> relationship) {
        System.out.println(relationship.getData().getHasList().size());
        System.out.println("Observer: Added an is a relationship.");
    }
}
