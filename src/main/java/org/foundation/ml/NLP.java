package org.foundation.ml;



import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Sequence;
import org.foundation.Foundation;
import org.foundation.ml.nlp.BrownCorpusTranslator;
import org.foundation.ml.nlp.ConvertWordToNumber;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
 * Created 2/8/13 5:27 AM
 */
public class NLP
{

    private POSTaggerME tagger;
    private TokenizerME tokenizer;

    private static NLP instance;


    public static NLP getInstance()
    {

        if (null == instance)
        {
            instance = new NLP();
            instance.startNLP();
        }

        return instance;
    }


    public static void main(String[] args) throws Exception
    {

        new NLP().startNLP();
    }


    public void startNLP()
    {

        String statement = " I found a pretty big leak. It's flooding the kale I'm going to fix it. Where do I find the supplied I need? I might need Joe Byrns's help.";
        String taskStatement = "I completed my task.";

        System.out.println("Analyzing Statement:\n\n     ".concat(statement).concat("\n\n"));

        InputStream modelIn = ClassLoader.getSystemResourceAsStream("en-sent.bin");


        try
        {
            //            SentenceModel model = new SentenceModel(modelIn);
            //
            //            SentenceDetectorME sentenceDetector = new SentenceDetectorME(model);
            //
            //
            //            Span sentences[] = sentenceDetector.sentPosDetect(statement);
            //
            //            System.out.println("Sentence Position Detection\n");
            //
            //            for (Span span : sentences)
            //            {
            //                System.out.println(span.toString());
            //            }
            //
            //            System.out.println("\n\n");
            //
            //
            modelIn = ClassLoader.getSystemResourceAsStream("en-token.bin");
            //
            TokenizerModel tokenModel = new TokenizerModel(modelIn);
            tokenizer = new TokenizerME(tokenModel);
            //            String tokens[] = tokenizer.tokenize(statement);
            //
            //            System.out.println("Tokens:\n");
            //
            //            for (String token : tokens)
            //            {
            //                System.out.println(token);
            //            }
            //
            //            Span tokenSpans[] = tokenizer.tokenizePos(statement);
            //
            //            System.out.println("\n\nToken Positions:\n");
            //
            //            for (Span span : tokenSpans)
            //            {
            //                System.out.println(span.toString());
            //            }
            //
            //            double tokenProbs[] = tokenizer.getTokenProbabilities();
            //
            //
            //            System.out.println("\n\nToken Probabilities:\n");
            //
            //            for (double prob : tokenProbs)
            //            {
            //                System.out.println(prob);
            //            }
            //
            //
            //            modelIn = ClassLoader.getSystemResourceAsStream("en-ner-person.bin");
            //
            //            TokenNameFinderModel nameFinderModel = new TokenNameFinderModel(modelIn);
            //
            //            NameFinderME nameFinder = new NameFinderME(nameFinderModel);
            //
            //
            //            Span nameSpans[] = nameFinder.find(sentenceDetector.sentDetect(statement));
            //
            //            System.out.println("\n\nName Finder:\n");
            //
            //            for (Span span : nameSpans)
            //            {
            //                System.out.println(span.toString());
            //            }


            //
            //            Charset charset = Charset.forName("UTF-8");
            //            ObjectStream<String> lineStream = new PlainTextByLineStream(ClassLoader.getSystemResourceAsStream("en-ner-person.train"),
            //                                                                        charset);
            //            ObjectStream<NameSample> sampleStream = new NameSampleDataStream(lineStream);
            //
            //
            //
            //            try
            //            {
            //                nameFinderModel = NameFinderME.train("en",
            //                                           "person",
            //                                           sampleStream,
            //                                           Collections.<String, Object>emptyMap(),
            //                                           100,
            //                                           5);
            //            }
            //            finally
            //            {
            //                sampleStream.close();
            //            }


            modelIn = ClassLoader.getSystemResourceAsStream("en-pos-maxent.bin");
            POSModel partsOfSpeachModel = new POSModel(modelIn);

            tagger = new POSTaggerME(partsOfSpeachModel);

            String[] asdf = tokenizer.tokenize(taskStatement);

            Sequence topSequences[] = tagger.topKSequences(asdf);

            System.out.println(new BrownCorpusTranslator().translate(topSequences[0].getOutcomes()));


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (modelIn != null)
            {
                try
                {
                    modelIn.close();
                }
                catch (IOException e)
                {
                }
            }
        }

    }

    public List<String> getPartsOfSpeach(String statement) {
        statement = statement.replace("-",
                                      " ");
        String[] asdf = tokenizer.tokenize(statement);

        Sequence topSequences[] = tagger.topKSequences(asdf);

        return topSequences[0].getOutcomes();
    }

    public List<String> getTokens(String statement) {
        statement = statement.replace("-",
                                      " ");
        String[] asdf = tokenizer.tokenize(statement);
        List<String> tokens = new ArrayList<String>();
        for (String str : asdf) {
            tokens.add(str);
        }

        return tokens;
    }

    public String partsOfSpeach(String statement)
    {

        statement = statement.replace("-",
                                      " ");
        String[] asdf = tokenizer.tokenize(statement);

        Sequence topSequences[] = tagger.topKSequences(asdf);

        String out = new BrownCorpusTranslator().translate(topSequences[0].getOutcomes());
        System.out.println(out);

        return out;
    }

    public <Foundational extends Foundation> List<Foundational> extractPrimeContext (String statement) {
        List<Foundational> foundations = new ArrayList<Foundational>();








        statement = statement.replace("-",
                                      " ");
        String[] asdf = tokenizer.tokenize(statement);

        Sequence topSequences[] = tagger.topKSequences(asdf);
        List<String> outcoms = topSequences[0].getOutcomes();

        int posOfPreposition = -1;

        for (int i = 0; i < asdf.length; i += 1)
        {

            /**
             * Check for a plural noun after a cardinal numeral
             * NN
             */

            if ((i != 0 && i - 1 == posOfPreposition && (outcoms.get(i).equals("NN") || outcoms.get(i).equals("NNS"))))
            {

                if (outcoms.get(i).equals("NNS")) {
                    asdf[i] = asdf[i].replace(".", "");
                    asdf[i] = asdf[i].substring(0, asdf[i].length() - 1);
                }
                /**
                 * TODO Accept a generic entity type for the foundation by label search.
                 */
//                foundations.add((Foundational)Label.getFoundationsByLabel(Plant.class, asdf[i]));
            }

            if (outcoms.get(i).equals("IN"))
            {
                posOfPreposition = i;
            }


        }



        return foundations;

    }

    public List<Measurement> extractNumeralPluralNouns(String statement)
    {

        BrownCorpusTranslator translator = new BrownCorpusTranslator();
        List<Measurement> measurements = new ArrayList<Measurement>();


        statement = statement.replace("-",
                                      " ");
        String[] asdf = tokenizer.tokenize(statement);

        Sequence topSequences[] = tagger.topKSequences(asdf);
        List<String> outcoms = topSequences[0].getOutcomes();

        int posOfCardinalNumeral = -1;
        int posOfSingularNoun = -1;
        for (int i = 0; i < asdf.length; i += 1)
        {

            /**
             * Check for a plural noun after a cardinal numeral
             * NN
             */

            if ((i != 0 && i - 1 == posOfCardinalNumeral && outcoms.get(i).equals("NN")))
            {
                Measurement measurement = new Measurement();

                measurement.setUnit(asdf[i]);
                try
                {
                    measurement.setValue(Double.valueOf(ConvertWordToNumber.parse(asdf[i-1])));
                }
                catch (Exception e)
                {
                    measurement.setValue(Double.valueOf((asdf[i-1])));
                }
                measurements.add(measurement);
            }

            if ((i != 0 && i - 1 == posOfCardinalNumeral && outcoms.get(i).equals("NNS")) || (i != 1 && i - 2 == posOfCardinalNumeral && outcoms.get(i).equals("NNS") && posOfSingularNoun == i - 1))
            {
                Measurement measurement = new Measurement();

                if (i - 2 == posOfCardinalNumeral && posOfSingularNoun == i - 1)
                {
                    measurement.setUnit(asdf[i - 1] + " " + asdf[i]);
                    try
                    {
                        measurement.setValue(Double.valueOf(ConvertWordToNumber.parse(asdf[i - 2])));
                    }
                    catch (Exception e)
                    {
                        measurement.setValue(Double.valueOf((asdf[i - 2])));
                    }
                }
                else
                {
                    measurement.setUnit(asdf[i]);
                    try
                    {
                        measurement.setValue(Double.valueOf(ConvertWordToNumber.parse(asdf[i - 1])));
                    }
                    catch (Exception e)
                    {
                        measurement.setValue(Double.valueOf((asdf[i - 1])));
                    }
                }

                measurements.add(measurement);
            }

            if (outcoms.get(i).equals("NN"))
            {
                posOfSingularNoun = i;
            }

            if (outcoms.get(i).equals("CD"))
            {
                posOfCardinalNumeral = i;
            }

        }


        return measurements;
    }


}
