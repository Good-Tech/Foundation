package org.foundation.warehouse;



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
 * Created 2/5/13 12:35 AM
 */
public class WarehouseImpl extends Foundation implements Warehouse
{
    private String id;
    private Label  name;


    public WarehouseImpl()
    {
        registerListnersOfClass(this);
        setName(Label.create("Generic Data Warehouse"));
    }


    public <FoundationalEntity extends Foundation> void save(FoundationalEntity foundation)
    {

    }


    public <FoundationalEntity extends Foundation> void archive(FoundationalEntity foundation)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public <FoundationalEntity extends Foundation> FoundationalEntity load(String foundationId)
    {

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public <FoundationalEntity extends Foundation> void disable(FoundationalEntity foundationalEntity)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public <FoundationalEntity extends Foundation> void enable(String foundationId, Class<? extends FoundationalEntity> type)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public <FoundationalEntity extends Foundation> void restrict(FoundationalEntity foundationalEntity)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    public <FoundationalEntity extends Foundation> void unrestrict(String foundationId, Class<? extends FoundationalEntity> type)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    /**
     * Getters and Setters
     */
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
