package org.foundation.ml.nlp;



import java.util.*;

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
 * Created 2/20/13 3:28 AM
 */
public class ListAnalyzer
{

    private static ListAnalyzer instance;


    public static ListAnalyzer getInstance()
    {

        if (null == instance)
        {
            instance = new ListAnalyzer();
        }
        return instance;
    }

    public static void destroy()
    {
        instance = null;
    }


    private ListAnalyzer()
    {

    }


    /**
     * Get the entity that occurs the most in the list of entities.
     */
    public <Entity> Entity getHighestFrequencyRepeatElement(List<Entity> entities)
    {
        Set<Entity> set = new HashSet<Entity>(entities);

        List<Integer> frequencies = new ArrayList<Integer>();


        int freq = -1;
        for (Entity entity : set)
        {
            int val = Collections.frequency(entities,
                                            entity);
            try
            {
                if (frequencies.get(freq) < val)
                {
                    freq = frequencies.size();
                    frequencies.add(val);
                }
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                freq = 0;
                frequencies.add(val);
            }
        }

        try {
            return (Entity)set.toArray()[freq];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }


}
