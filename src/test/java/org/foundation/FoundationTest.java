package org.foundation;



import org.foundation.ml.Label;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
public class FoundationTest
{

    @Before
    public void setUp() throws Exception
    {


    }


    @Test
    public void testDunno() throws Exception
    {

        Foundation foundation = new Foundation()
        {
            @Override
            public String getId()
            {

                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }


            @Override
            public void setId(String id)
            {
                //To change body of implemented methods use File | Settings | File Templates.
            }


            @Override
            public Label getName()
            {

                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }


            @Override
            public void setName(Label label)
            {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };

        foundation.getRelationship();

    }


    @After
    public void tearDown() throws Exception
    {


    }
}
