package org.foundation.ml.nlp;



import org.foundation.ml.NLP;

import java.util.*;

/**
 * A utility for mapping the properties of labels and classifying them by their properties.
 * <p/>
 * <p/>
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
 * Created 2/17/13 11:27 PM
 */
public class LabelCloud
{
    private Map<String, Label>                     labelMap             = new HashMap<String, Label>();
    private Map<String, List<LabelClassification>> labelClassifications = new HashMap<String, List<LabelClassification>>();

    private static LabelCloud instance;


    private LabelCloud()
    {

    }


    public static LabelCloud getInstance()
    {

        if (null == instance)
        {
            instance = new LabelCloud();
        }
        return instance;
    }


    /**
     * Add a label to the cloud.
     *
     * @param label The label to add to the cloud.
     */
    public void addLabel(Label label)
    {

        labelMap.put(label.getId(),
                     label);
        classifyLabel(label);
    }


    /**
     * Classify a label by it's extractable properties.
     *
     * @param label The label to classify.
     */
    private void classifyLabel(Label label)
    {
        /**
         * Divide the label into it's parts of speech.
         */
        List<String> strings = NLP.getInstance().getPartsOfSpeach(label.getValue());

        /**
         * Divide the label into it's individual tokens.
         */
        List<String> tokens = NLP.getInstance().getTokens(label.getValue());

        /**
         * For each part of speech we create a label classification
         * that relates the label, the part of speech, and the token for the part of speech.
         * This record is stored in a map of classifications.
         *
         * This will expand to perform statistical decomposition and classification of the
         * label into other measurable sets of data. This is an initial structuring for
         * the future analytics.
         */
        for (String str : strings)
        {
            if (!labelClassifications.containsKey(str))
            {
                labelClassifications.put(str,
                                         new ArrayList<LabelClassification>());
            }

            LabelClassification classification = new LabelClassification();
            classification.setLabel(label);
            classification.setPartOfSpeach(str);
            classification.setToken(tokens.get(strings.indexOf(str)));

            labelClassifications.get(str).add(classification);
        }
    }


    /**
     * Get a label by it's ID.
     *
     * @param id The ID of the label to get.
     *
     * @return Null if the label isn't found otherwise the requested label matching the provided id.
     */
    public Label getLabel(String id)
    {

        if (labelMap.containsKey(id))
        {
            return labelMap.get(id);
        }
        return null;
    }


    /**
     * Remove a label from the cloud.
     *
     * @param label The label to remove from the cloud.
     */
    public void removeLabel(Label label)
    {

        if (labelMap.containsKey(label.getId()))
        {
            labelMap.remove(label.getId());
        }
    }


    /**
     * Remove a label from the cloud.
     *
     * @param id The ID of the label to remove from the cloud.
     */
    public void removeLabel(String id)
    {

        if (labelMap.containsKey(id))
        {
            labelMap.remove(id);
        }
    }


    /**
     * Get classifications containing a given token.
     *
     * @param token The token to find classifications with.
     *
     * @return A list of classification that contain the provided token.
     */
    public List<LabelClassification> getClassificationsContaining(String token)
    {

        List<LabelClassification> classifications = new ArrayList<LabelClassification>();

        /**
         * Get a set of our known classifications.
         */
        Set<String> keySet = labelClassifications.keySet();
        Iterator<String> keyIterator = keySet.iterator();
        /**
         * Iterate over the classifications.
         */
        while (keyIterator.hasNext())
        {
            String key = keyIterator.next();
            /**
             * Iterate over known label classifications match our
             * classification to its classifications. If it is found
             * we add the classification to our output classifications.
             */
            for (LabelClassification classification : labelClassifications.get(key))
            {
                if (classification.getToken().equals(token))
                {
                    classifications.add(classification);
                }
            }
        }

        return classifications;
    }


    /**
     * Get the classification closest to the token.
     *
     * @param token The token to reference.
     * @return The closest classification to the provided label.
     */
    public LabelClassification getClassificationClosestTo(String token)
    {

        /**
         * Grab all classifications containing the token and flatten it into a set.
         */
        List<LabelClassification> classifications = getClassificationsContaining(token);
        Set<LabelClassification> set = new HashSet<LabelClassification>(classifications);

        List<Integer> frequencies = new ArrayList<Integer>();

        /**
         * Get the classification that occurs the most in the list of classifications.
         */
        int freq = -1;
        for (LabelClassification classification : set)
        {
            int val = Collections.frequency(classifications,
                                            classification);
            try
            {
                if (frequencies.get(freq) < val)
                {
                    freq = frequencies.size() - 1;
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
            return (LabelClassification)set.toArray()[freq];
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }


    }


    /**
     * Get labels that classify like the statement you provide.
     *
     * @param statement A statement to find similar labels to.
     *
     * @return Labels that are like the statement you provided.
     */
    public List<Label> getLabelsLike(String statement)
    {

        List<String> tokens = NLP.getInstance().getTokens(statement);
        List<Label> similarLabels = new ArrayList<Label>();

        for (String token : tokens)
        {

            /**
             * Get our classification and flatten them to a set to remove duplicate entries.
             */
            List<LabelClassification> classifications = getClassificationsContaining(token);
            Set<LabelClassification> set = new HashSet<LabelClassification>(classifications);

            List<Integer> fitness = new ArrayList<Integer>();


            /**
             * A value representing the index of the classification with the most hits.
             */
            int highestFrequency = -1;
            for (LabelClassification classification : set)
            {
                /**
                 * Get the frequency that the given classification occurs in our collection of classifications.
                 */
                int frequency = Collections.frequency(classifications,
                                                      classification);
                fitness.add(frequency);

                try
                {
                    /**
                     * If the frequency is greater than the frequency of the previously
                     * recorded highest frequency classification then we replace
                     * the existing index marker with the current index.
                     */
                    if (frequency > fitness.get(highestFrequency))
                    {
                        highestFrequency = fitness.size() - 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException e)
                {
                    highestFrequency = fitness.size() - 1;
                }
            }

            try
            {
                /**
                 * Add the highest frequency label for the classification to our output
                 * list of similar labels.
                 */
                similarLabels.add(((LabelClassification) set.toArray()[highestFrequency]).getLabel());
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                // No labels matched.
            }

        }

        return similarLabels;
    }


}
