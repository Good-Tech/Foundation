package org.foundation.ml.nlp;



import org.codehaus.jackson.annotate.JsonIgnore;
import org.foundation.Foundation;
import org.foundation.warehouse.ActivePersistenceImpl;

import java.util.List;

/**
 * A label provides a plain english way of tagging information.
 * <p/>
 * Labels are required for each and every foundation in the form of a name.
 * This places a requirement that every instance be identifiable as a unique agent.
 * <p/>
 * A label will be used to perform linguistic mapping of the foundation and all systems
 * derived from it. This linguistic map will allow for autowiring of aspect messages
 * between various agents in the system. The map will also provide a way of identifying
 * the context in which an aspect message is being conveyed. In this way we can
 * automatch second generation delegates to all aspect messages. This increases
 * the probability of a message finding an outlet to share it's information.
 * <p/>
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
 * Created 2/1/13 3:05 AM
 */
public class Label extends Foundation
{

    private String id;

    private String value;

    @JsonIgnore
    private Label name;


    public Label getName()
    {

        return name;
    }


    public void setName(Label name)
    {

        this.name = name;
    }


    public Label()
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


    public String getValue()
    {

        return value;
    }


    public void setValue(String value)
    {

        this.value = value;
    }


    /**
     * Create a new label.
     *
     * @param value The value of the label.
     *
     * @return A new label with teh provided value.
     */
    public static Label create(String value)
    {

        Label label = null;// ActivePersistenceImpl.getInstance().findByProperty(Label.class,
        //                                          "value",
        //                                        value);


        if (label == null)
        {
            label = new Label();
            label.setValue(value);

            //            ActivePersistenceImpl.getInstance().create(label);
        }

        label.setName(label);

        return label;
    }


    @Deprecated
    public static <Foundational extends Foundation> Foundation getFoundationsByLabel(Class<? extends Foundation> type, String label)
    {

        return ActivePersistenceImpl.getInstance().findByLabel(type,
                                                               label);

    }


    @Deprecated
    public static <Foundational extends Foundation> List<Foundational> getFoundationsByLabel(String type, String label)
    {

        return ActivePersistenceImpl.getInstance().findByLabel(type,
                                                               label);

    }


    public Long getVersion()
    {

        return 2L;
    }
}
