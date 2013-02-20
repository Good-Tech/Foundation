package org.foundation.ml.nlp;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
 * Created 2/20/13 3:32 AM
 */
public class ListAnalyzerTest
{
    @Before
    public void setUp() throws Exception
    {
        ListAnalyzer.getInstance();
    }


    @After
    public void tearDown() throws Exception
    {
        ListAnalyzer.destroy();
    }


    @Test
    public void testGetHighestFrequencyRepeatElement() throws Exception
    {

        List<Integer> integers = new ArrayList<Integer>();
        integers.add(1);
        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(3);
        integers.add(3);
        integers.add(2);
        Integer highestFrequencyNumber = ListAnalyzer.getInstance().getHighestFrequencyRepeatElement(integers);
        assertEquals("Highest frequency should be ", 2, highestFrequencyNumber, 0.00001);
    }
}
