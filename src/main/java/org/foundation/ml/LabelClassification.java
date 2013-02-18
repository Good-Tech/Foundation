package org.foundation.ml;



/**
 * Classify a label by a given tokens part of speech.
 *
 *
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
 * Created 2/18/13 2:41 AM
 */
public class LabelClassification
{
    private String partOfSpeach;
    private String token;
    private Label label;


    public String getPartOfSpeach()
    {

        return partOfSpeach;
    }


    public void setPartOfSpeach(String partOfSpeach)
    {

        this.partOfSpeach = partOfSpeach;
    }


    public String getToken()
    {

        return token;
    }


    public void setToken(String token)
    {

        this.token = token;
    }


    public Label getLabel()
    {

        return label;
    }


    public void setLabel(Label label)
    {

        this.label = label;
    }
}
