package org.good.tech.foundation;



import org.good.tech.foundation.ml.Label;
import org.good.tech.foundation.model.Conduit;
import org.good.tech.foundation.model.Node;
import org.good.tech.test.BaseAspectTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

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
 * Created 2/8/13 1:26 AM
 */
public class NodeTest extends BaseAspectTest
{

    private Node actor = new Node();
    private Node subject = new Node();

    @Before
    public void setUp() throws Exception
    {


        {
            Label label = new Label();
            label.setValue("Actor Node");

            actor.setConduits(new ArrayList<org.good.tech.foundation.model.Conduit>());
            actor.setName(label);

        }

        {
            Label label = new Label();
            label.setValue("Subject Node");

            subject.setConduits(new ArrayList<org.good.tech.foundation.model.Conduit>());
            subject.setName(label);
        }

        assertNotNull(actor.getConduits());
        assertNotNull(actor.getName());
        assertNotNull(actor.getName().getValue());

        assertNotNull(subject.getConduits());
        assertNotNull(subject.getName());
        assertNotNull(subject.getName().getValue());


    }


    @Test
    public void asdf() throws Exception
    {

        startTest();

        Conduit conduit = new Conduit();
        conduit.setActor(actor);
        conduit.setSubject(subject);

        actor.getConduits().add(conduit);


        actor.says("I want to know the square root of 2.").to();


    }


    @After
    public void tearDown() throws Exception
    {

    }
}
