package org.foundation.ml;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
 * Created 2/18/13 3:01 AM
 */
public class LabelCloudTest
{
    @Before
    public void setUp() throws Exception
    {

    }


    @After
    public void tearDown() throws Exception
    {

    }


    @Test
    public void testAddLabel() throws Exception
    {

    }


    @Test
    public void testGetLabel() throws Exception
    {

    }


    @Test
    public void testRemoveLabel() throws Exception
    {

    }


    @Test
    public void testGetClassificationsContaining() throws Exception
    {

    }


    @Test
    public void testGetLabelsLike() throws Exception
    {
        LabelCloud.getInstance().addLabel(Label.create("Hello world"));
        LabelCloud.getInstance().addLabel(Label.create("The quick brown fox jumps over the lazy dog."));
        LabelCloud.getInstance().addLabel(Label.create("What is happening my friend?"));
        LabelCloud.getInstance().addLabel(Label.create("It is a beautiful world we live in."));
        LabelCloud.getInstance().addLabel(Label.create("Kale is amazing!"));
        LabelCloud.getInstance().addLabel(Label.create("Yeah buddy"));
        LabelCloud.getInstance().addLabel(Label.create("My dog is my best buddy"));
        LabelCloud.getInstance().addLabel(Label.create("The capacitance of a dielectric is directly proportional to it's surface area and inversely proportional to it's thickness."));
        LabelCloud.getInstance().addLabel(Label.create("Full speed ahead captain."));

        List<Label> labelList = LabelCloud.getInstance().getLabelsLike("My dog is my best buddy");

        for (Label label : labelList) {
            System.out.println(label.getValue());
        }
    }
}
