package org.foundation;



import org.goodgod.controller.Message;

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
 * Created 2/2/13 4:41 PM
 */
public class Prepositions
{

    private Interaction interaction;
    private String verb;
    private String characataristic;


    public Interaction getInteraction()
    {

        return interaction;
    }


    public void setInteraction(Interaction interaction)
    {

        this.interaction = interaction;
    }


    public String getVerb()
    {

        return verb;
    }


    public void setVerb(String verb)
    {

        this.verb = verb;
    }


    public String getCharacataristic()
    {

        return characataristic;
    }


    public void setCharacataristic(String characataristic)
    {

        this.characataristic = characataristic;
    }


    public void to (Object foundation) {
//        if (foundation instanceof Foundation) {
//            getInteraction().getActor().has((Foundation)foundation);
//        }
        try {
            getInteraction().getSubject().sendMessage(getVerb(),
                                                              Message.create(getCharacataristic(), foundation));
        }
        catch (NullPointerException e) {
            System.out.println("No subject and or verb.");
        }
    }

    public void like (Object foundation) {
//        getInteraction().getSubject().sendMessage(getVerb(),
//                                                  Message.create(getCharacataristic(), foundation));
        to(foundation);
    }

}
