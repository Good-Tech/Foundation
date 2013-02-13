package org.foundation.ml;



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
 * Created 2/8/13 7:15 AM
 */
public class Measurement
{
    private String unit;
    private double value;


    public String getUnit()
    {

        return unit;
    }


    public void setUnit(String unit)
    {

        this.unit = unit;
    }


    public double getValue()
    {

        return value;
    }


    public void setValue(double value)
    {

        this.value = value;
    }
}
