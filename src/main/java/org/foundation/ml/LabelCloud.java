package org.foundation.ml;



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
 * Created 2/17/13 11:27 PM
 */
public class LabelCloud
{
    private Map<String, Label> labelMap = new HashMap<String, Label>();
    private Map<String, List<LabelClassification>> labelClassifications = new HashMap<String, List<LabelClassification>>();

    private static LabelCloud instance;


    private LabelCloud()
    {

    }


    public static LabelCloud getInstance()
    {
        if (null == instance) {
            instance = new LabelCloud();
        }
        return instance;
    }


    /**
     * Add a label to the cloud.
     *
     * @param label The label to add to the cloud.
     */
    public void addLabel (Label label) {
        labelMap.put(label.getId(), label);
        classifyLabel(label);
    }

    private void classifyLabel (Label label) {
        List<String> strings = NLP.getInstance().getPartsOfSpeach(label.getValue());
        List<String> tokens = NLP.getInstance().getTokens(label.getValue());

        for (String str : strings) {
            if (!labelClassifications.containsKey(str)) {
                labelClassifications.put(str, new ArrayList<LabelClassification>());
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
     * @return Null if the label isn't found otherwise the requested label matching the provided id.
     */
    public Label getLabel (String id) {
        if (labelMap.containsKey(id)) {
            return labelMap.get(id);
        }
        return null;
    }

    /**
     * Remove a label from the cloud.
     *
     * @param label The label to remove from the cloud.
     */
    public void removeLabel (Label label) {
        if (labelMap.containsKey(label.getId())) {
            labelMap.remove(label.getId());
        }
    }


    /**
     * Remove a label from the cloud.
     *
     * @param id The ID of the label to remove from the cloud.
     */
    public void removeLabel (String id) {
        if (labelMap.containsKey(id)) {
            labelMap.remove(id);
        }
    }

    public List<LabelClassification> getClassificationsContaining(String token) {

        List<LabelClassification> classifications = new ArrayList<LabelClassification>();

        Set<String> keySet = labelClassifications.keySet();
        Iterator<String> keyIterator = keySet.iterator();
        while (keyIterator.hasNext()) {
            String key = keyIterator.next();
            for (LabelClassification classification : labelClassifications.get(key)) {
                if (classification.getToken().equals(token)) {
                    classifications.add(classification);
                }
            }
        }

        return classifications;
    }

    public List<Label> getLabelsLike(String statement) {
        List<String> tokens = NLP.getInstance().getTokens(statement);
        List<Label> similarLabels = new ArrayList<Label>();

        for (String token : tokens) {
            List<LabelClassification> classifications = getClassificationsContaining(token);
            Set<LabelClassification> set = new HashSet<LabelClassification>(classifications);

            List<Integer> fitness = new ArrayList<Integer>();


            int highestFrequency = -1;
            for (LabelClassification classification : set) {
                int frequency = Collections.frequency(classifications, classification);
                fitness.add(frequency);

                try {
                    if (frequency > fitness.get(highestFrequency)) {
                        highestFrequency = fitness.size() - 1;
                    }
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    highestFrequency = fitness.size() - 1;
                }
            }

            try {
                similarLabels.add(((LabelClassification)set.toArray()[highestFrequency]).getLabel());
            }
            catch (ArrayIndexOutOfBoundsException e) {
                // No labels matched.
            }

        }

        return similarLabels;
    }



}
