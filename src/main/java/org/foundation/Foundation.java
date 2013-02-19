package org.foundation;



import org.codehaus.jackson.annotate.JsonIgnore;
import org.foundation.ml.nlp.Label;
import org.foundation.warehouse.ActivePersistenceImpl;
import org.goodeducation.language.LevenshteinDistance;
import org.goodeducation.language.Sentence;
import org.goodeducation.language.Word;
import org.goodgod.controller.Message;
import org.goodgod.controller.MessageController;
import org.goodgod.controller.MessageHandler;
import org.goodgod.controller.ScopedMethod;
import org.universe.Has;
import org.universe.Is;
import org.universe.Relationship;

import javax.persistence.Entity;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
 * Created 2/1/13 8:00 PM
 */
public abstract class Foundation extends MessageController
{

    private static Map<String, Foundation> foundationInstances = new HashMap<String, Foundation>();

    public static Foundation getFoundationById(String id) {
        return foundationInstances.get(id);
    }

    private static void registerFoundation(String id, Foundation foundation) {
        foundationInstances.put(id, foundation);
    }

    private String type;


    public String getType()
    {

        return type;
    }


    private Foundation primeContext;

    private String lastStatement = "";

    private Relationship relationship;


    public Relationship getRelationship()
    {

        return relationship;
    }


    public void setRelationship(Relationship relationship)
    {

        this.relationship = relationship;
    }


    public abstract String getId();

    public abstract void setId(String id);

    public abstract Label getName();

    public abstract void setName(Label label);


    /**
     * Delegate to Label.create()
     *
     * @param name
     */
    public void setName(String name)
    {
        setName(Label.create(name));
    }

    /*
        Can use anonymous entities.
     */


    public class AspectCall
    {
        private Method methodToCall;
        private Object context;


        AspectCall(final Method methodToCall, final Object context)
        {

            this.methodToCall = methodToCall;
            this.context = context;
        }


        public void set(final Message message)
        {

            sendMessage("set",
                        Message.create(methodToCall.getName().replace("set",
                                                                      "").concat("-started"),
                                       message.getData()));
            try
            {
                //                System.out.println(methodToCall.getParameterTypes()[0].getName().equals(message.getData().getClass().getName()));
                methodToCall.invoke(context,
                                    message.getData());
                sendMessage("set",
                            Message.create(methodToCall.getName().replace("set",
                                                                          "").concat("-complete"),
                                           message.getData()));
            }
            catch (InvocationTargetException e)
            {
                sendMessage("set",
                            Message.create(methodToCall.getName().replace("set",
                                                                          "").concat("-failed"),
                                           message.getData()));
                //                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                sendMessage("set",
                            Message.create(methodToCall.getName().replace("set",
                                                                          "").concat("-failed"),
                                           message.getData()));
                e.printStackTrace();
            }
        }


        public void get(final Message message)
        {

            sendMessage("get",
                        Message.create(methodToCall.getName().replace("get",
                                                                      "").concat("-started"),
                                       message.getData()));

        }
    }


    public void registerListnersOfClass(Object context)
    {

        registerListenersOfClass(context.getClass(),
                                 context);
    }


    @Override
    public void registerListenersOfClass(final Class clazz, final Object context)
    {

        setId(UUID.randomUUID().toString());
        registerFoundation(getId(), this);

        type = this.getClass().getName();

        List<Method> methods = EntitySpider.getSetters(clazz);

        for (final Method method : methods)
        {
            // What is the growth habit for kale
            Method aspectedSetMethod = null;
            Method aspectedGetMethod = null;
            Method aspectedWhenMethod = null;
            Method aspectedWillMethod = null;
            for (Method aMethod : AspectCall.class.getMethods())
            {
                if (aMethod.getName().equals("set"))
                {
                    aspectedSetMethod = aMethod;
                    break;
                }
            }

            MessageHandler messageHandler = new MessageHandler()
            {
                public String channel()
                {

                    return method.getName().replace("set",
                                                    "").toLowerCase();
                }


                public String group()
                {

                    return "set";
                }


                public String documentation()
                {

                    return "Set a value for ".concat(method.getName().replace("set", ""));
                }


                public Class<? extends Annotation> annotationType()
                {

                    return MessageHandler.class;
                }
            };

            if (null != aspectedSetMethod)
            {
                final AspectCall aspectCall = new AspectCall(method,
                                                             context);
                registerListener(messageHandler,
                                 aspectedSetMethod,
                                 aspectCall);
            }

        }

        super.registerListenersOfClass(clazz,
                                       context);    //To change body of overridden methods use File | Settings | File Templates.
    }


    protected void setFieldValue(String fieldName, Object value)
    {

    }


    public Foundation to(Foundation entity)
    {

        return entity;
    }


    public Interaction with(Foundation entity)
    {

        Interaction interaction = new Interaction();

        interaction.setActor(this);
        interaction.setSubject(entity);

        primeContext = entity;

        return interaction;
    }


    public Interaction withSelf()
    {

        Interaction interaction = new Interaction();

        interaction.setActor(this);
        interaction.setSubject(this);

        primeContext = this;

        return interaction;
    }


    /**
     * Add a has a relationship.
     */
    private void addRelationship(Has has)
    {

        if (null == relationship)
        {
            relationship = new Relationship();
        }
        if (null == relationship.getHasList())
        {
            relationship.setHasList(new ArrayList<Has>());
        }

        relationship.addHasA(has);
    }


    /**
     * Add an is a relationship.
     */
    private void addRelationship(Is is)
    {

        if (null == relationship)
        {
            relationship = new Relationship();
        }
        if (null == relationship.getIsList())
        {
            relationship.setIsList(new ArrayList<Is>());
        }
        relationship.addIsA(is);
    }


    /**
     * This has another.
     *
     * @param entity
     */
    public void has(Foundation entity)
    {

        Has has = new Has();
        has.setChildId(entity.getId());
        has.setFoundation(entity);

        has.setName(entity.getName());

        addRelationship(has);


        String name = entity.getClass().toString();
        entity.registerListenersOfClass(this.getClass(),
                                        this);

        primeContext = entity;
    }


    /**
     * This is something else.
     *
     * @param entity
     */
    public void is(Foundation entity)
    {


        Is is = new Is();
        is.setParrentId(entity.getId());
        is.setFoundation(entity);

        addRelationship(is);


        String name = entity.getClass().toString();

        if (null == primeContext)
        {
            try
            {
                primeContext = getRelationship().getIsList().get(getRelationship().getIsList().size() - 1).getFoundation().primeContext;
            }
            catch (NullPointerException e)
            {
                primeContext = this;
            }
        }


        /**
         * I am entity thus any thing done to entity is done to me.
         * Entity is not me there for what is done to me is not done to entity unless I
         * delegate it to entity explicitly later down the message chain.
         */
        registerListenersOfClass(entity.getClass(),
                                 entity);


        //        primeContext = entity;
    }


    private String getBestGroupMatch(String statement)
    {

        Sentence sentence = new Sentence();
        sentence.setValue(statement);

        int best = 10000;
        String bestString = null;

        for (int i = 0; i < sentence.length(); i += 1)
        {
            Word word = sentence.getWordAtIndex(i);


            //                    System.out.println(word.getValue());

            for (String group : getAvailableGroups())
            {
                //                System.out.println(group);
                if (LevenshteinDistance.score(word.getValue(),
                                              group) < best)
                {
                    best = LevenshteinDistance.score(word.getValue(),
                                                     group);
                    bestString = group;
                }

            }
        }

        return bestString;
    }


    public Prepositions says(String statement)
    {

        return says(statement,
                    true);
    }


    public Prepositions says(String statement, boolean delegate)
    {



        System.out.println(getClass().getSimpleName().concat(" says:"));
        System.out.println(statement);
        statement = statement.toLowerCase();
        statement = " ".concat(statement);
        statement.replace(" i ",
                          " ");

        statement = statement.toLowerCase();

        Sentence sentence = new Sentence();
        sentence.setValue(statement.toLowerCase());


        int bestChannelMeasure = 10000;

        String bestChannel = null;

        for (int i = 0; i < sentence.length(); i += 1)
        {
            Word word = sentence.getWordAtIndex(i);

            //                    System.out.println(word.getValue());

            String bestGroup = getBestGroupMatch(statement);

            if (null == bestGroup)
            {
                if (!delegate)
                {
                    return new Prepositions();
                }

                try
                {
                    //            System.out.println("\n\n");
                    //            System.out.println(getBestGroupMatch(statement));
                    //                    if (primeContext.primeContext.equals(this))
                    //                    {
                    //                        primeContext.says(statement,
                    //                                          false);
                    //                    }
                    return primeContext.says(statement);
                }
                catch (NullPointerException e)
                {
                    // No prime context.
                }
            }

            double bestOverallMatch = 0;
            String bestOverallChannel = "";

            try
            {
                for (ScopedMethod channel : getAvailableChannelsForGroup(bestGroup))
                {
//                    System.out.println(channel.getChannel());
                    Sentence multiWordChannel = new Sentence();
                    multiWordChannel.setValue(channel.getChannel().replace("-",
                                                                           ""));
                    boolean contains = false;
                    double percent = 0;
                    for (int j = 0; j < multiWordChannel.length(); j += 1)
                    {

                        Word word1 = multiWordChannel.getWordAtIndex(j);
                        contains = statement.replace(" ",
                                                     "").contains(word1.getValue());
                        if (statement.replace(" ",
                                              "").contains(word1.getValue()) || LevenshteinDistance.computeLevenshteinDistance(statement.replace(" ",
                                                                                                                                                 ""),
                                                                                                                               (word1.getValue())) < 3)
                        {
                            percent += 1;
                        }

                        for (int k = 0; k < sentence.length(); k += 1)
                        {
                            int dist = LevenshteinDistance.computeLevenshteinDistance(word1.getValue(),
                                                                                      sentence.getWordAtIndex(k).getValue());
                            if (dist < 1)
                            {
                                percent += 0.95;
                            }
                            else if (dist < 2)
                            {
                                percent += 0.85;
                            }
                            else if (dist < 3)
                            {
                                percent += 0.75;
                            }
                            else if (dist < 4)
                            {
                                percent += 0.5;
                            }
                            else if (dist < 5)
                            {
                                percent += 0.25;
                            }

                        }
                    }

                    percent = percent / multiWordChannel.length();


                    if (LevenshteinDistance.computeLevenshteinDistance(word.getValue().toLowerCase(),
                                                                       channel.getChannel().toLowerCase()) < bestChannelMeasure)
                    {
                        bestChannelMeasure = LevenshteinDistance.computeLevenshteinDistance(word.getValue().toLowerCase(),
                                                                                            channel.getChannel().toLowerCase());
                        bestChannel = channel.getChannel();

                    }

                    if (bestOverallMatch > percent) {
                        bestOverallMatch = percent;
                        bestOverallChannel = channel.getChannel();
                    }

//                    if (percent > 0.5)
//                    {
////                        System.out.println(statement.toLowerCase());
//                        System.out.println(getName().getValue().concat(" wants to ").concat(bestGroup.toLowerCase()).concat(" ").concat(channel.getChannel().toLowerCase()));
//                        return does(bestGroup.toLowerCase(),
//                                    channel.getChannel().toLowerCase());
//                    }
                }

                if (bestOverallMatch > 0.5) {
                    return does(bestGroup.toLowerCase(),
                                bestOverallChannel);
                }
            }
            catch (NullPointerException e)
            {
                // No groups.
            }

            //            for (String group : getAvailableGroups())
            //            {
            //                for (ScopedMethod channel : getAvailableChannelsForGroup(group))
            //                {
            ////                    System.out.println(LevenshteinDistance.computeLevenshteinDistance(word.getValue().toLowerCase(),
            ////                                                                                      channel.getChannel().toLowerCase()));
            //                    if (LevenshteinDistance.computeLevenshteinDistance(word.getValue().toLowerCase(),
            //                                                                       channel.getChannel().toLowerCase().replace("-", "")) < 3 || statement.contains(channel.getChannel().toLowerCase().replace("-", "")))
            //                    {
            //                        //                                System.out.println((word.getValue() + " " + channel.getChannel()));
            //
            //                        if (LevenshteinDistance.computeLevenshteinDistance(getBestGroupMatch(statement.toLowerCase()),
            //                                                                           group.toLowerCase().replace("-", "")) < 3 || statement.contains(group.toLowerCase().replace("-", "")))
            //                        {
            //                            System.out.println(statement.toLowerCase());
            //                            System.out.println(getName().getValue().concat(" wants to ").concat(group.toLowerCase()).concat(" ").concat(channel.getChannel().toLowerCase()));
            //                            return does(group.toLowerCase(),
            //                                        channel.getChannel().toLowerCase());
            //                        }
            //                    }
            //                    if (LevenshteinDistance.computeLevenshteinDistance(word.getValue().toLowerCase(),
            //                                                                                           channel.getChannel().toLowerCase()) < bestChannelMeasure) {
            //                        bestChannelMeasure = LevenshteinDistance.computeLevenshteinDistance(word.getValue().toLowerCase(),
            //                                                                                               channel.getChannel().toLowerCase());
            //                        bestChannel = channel.getChannel();
            //                    }
            //                    //                            System.out.println(LevenshteinDistance.computeLevenshteinDistance(word.getValue(), channel.getChannel()));
            //                }
            //            }
        }

        //        try
        //        {
        //            InputStream is = new ByteArrayInputStream(statement.getBytes());
        //            new TokenizerModel("en", new PerceptronModel()
        //            {
        //            })
        //            TokenizerModel tokenizerModel = new TokenizerModel(is);
        //            Tokenizer tokenizer = new TokenizerME(tokenizerModel);
        //
        //            String tokens[] = tokenizer.tokenize("This is kale.");
        //            for (String token : tokens) {
        //                System.out.println(token);
        //            }
        //
        //        }
        //        catch (Throwable e)
        //        {
        //            e.printStackTrace();
        //        }
        try
        {
//            System.out.println("\n\n".concat(statement));
            try
            {
//                System.out.println(primeContext.getClass().getName());
            }
            catch (NullPointerException e)
            {

            }
//            System.out.println(getBestGroupMatch(statement));
            return does(getBestGroupMatch(statement),
                        bestChannel);
        }
        catch (NullPointerException e)
        {

        }


        return new Prepositions();
    }


    /**
     * Ask has a relationships for information.
     *
     * @param question
     */
    public void asks(String question)
    {

        List<Has> hasAList = getRelationship().getHasList();
        for (Has hasA : hasAList)
        {
            focusOn(hasA.getFoundation());
            says(question);
        }
    }


    public void focusOn(Foundation foundation)
    {

        primeContext = foundation;
    }


    public Prepositions does(String verb, String charactaristc)
    {

        if (null == primeContext)
        {
            try
            {
                primeContext = getRelationship().getIsList().get(getRelationship().getIsList().size() - 1).getFoundation().primeContext;
            }
            catch (NullPointerException e)
            {
                primeContext = this;
            }
        }
        return with(primeContext).does(verb,
                                       charactaristc);
    }


    public <T extends Foundation> T manifest(Class<T> clazz)
    {

        try
        {
            T t = clazz.newInstance();

            has(t);

            if (t.getName() != null && t.getName().getValue() != null && !t.getName().getValue().equals("")) {
                t.setName(t.getName().getValue() + " " +t.getClass().getSimpleName());
            }
            else {
                t.setName(t.getClass().getSimpleName());
            }

            ActivePersistenceImpl.getInstance().create(t);

            return t;
        }
        catch (IllegalAccessException e)
        {
            return null;
        }
        catch (InstantiationException e)
        {
            return null;
        }
    }

}
