package org.foundation.warehouse;



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
 * Created 2/17/13 11:06 PM
 */
public class ActivePersistenceTest
{

    private ActivePersistence persistence;

    @Before
    public void setUp() throws Exception
    {
        persistence = ActivePersistenceImpl.getInstance();
    }


    @Test
    public void testCreate() throws Exception
    {

    }


    @Test
    public void testRead() throws Exception
    {

    }


    @Test
    public void testUpdate() throws Exception
    {

    }


    @Test
    public void testDelete() throws Exception
    {

    }

    @After
    public void tearDown() throws Exception
    {

    }

}
