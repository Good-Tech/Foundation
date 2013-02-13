package org.foundation.framework.mcl;



import org.codehaus.jackson.annotate.JsonIgnore;
import org.foundation.Foundation;
import org.foundation.ml.Label;

import java.util.UUID;

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
 * Created 1/30/13 4:41 AM
 */

public abstract class ControllableEntityLayer extends Foundation// extends GenericDAOImpl
{

    private String id;


    public String getId()
    {

        return id;
    }


    public void setId(String id)
    {

        this.id = id;
    }


    private String instanceId = UUID.randomUUID().toString();

    private Label name;


    public Label getName()
    {

        return name;
    }


    public void setName(Label name)
    {

        this.name = name;
    }


    public String getInstanceId()
    {

        return instanceId;
    }


    public void setInstanceId(String instanceId)
    {

        this.instanceId = instanceId;

        /**
         * @TODO load data from storage for instance with specified ID.
         */

    }


    @JsonIgnore
    private Controller controller;

    @JsonIgnore
    private DataAccessLayer dataAccessLayer;


    public DataAccessLayer getDataAccessLayer()
    {

        return dataAccessLayer;
    }


    public void setDataAccessLayer(DataAccessLayer dataAccessLayer)
    {

        this.dataAccessLayer = dataAccessLayer;
    }


    public Controller getController()
    {

        return controller;
    }


    public void setController(Controller controller)
    {

        this.controller = controller;
    }


    //    public void sendMessage(String handlerSet, Message message)
    //    {
    //
    //        controller.sendMessage(handlerSet,
    //                               message);
    //    }
    //
    //    public void sendMessage(Message message) {
    //        controller.sendMessage(message);
    //    }
    //
    //
    //    public void unregisterListenersOfClass(Class clazz)
    //    {
    //
    //        controller.unregisterListenersOfClass(clazz);
    //    }


    public void communicateWith(Class clazz, Object context)
    {

        registerListenersOfClass(clazz,
                                 context);
    }
}
