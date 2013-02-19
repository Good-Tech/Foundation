package org.foundation.ml;



import org.foundation.ml.nlp.Label;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
 * Created 2/17/13 11:10 PM
 */
public class LabelTest
{

    private Label label;


    @Before
    public void setUp() throws Exception
    {

        label = new Label();
    }


    @Test
    public void testCreatingLabel() throws Exception
    {

        Label label = Label.create("Test Label");
        assertNotNull("Our label should have been created.",
                      label);

    }


    @After
    public void tearDown() throws Exception
    {

        label = null;
    }
}
