package org.foundation.ml.nlp;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Created 2/8/13 6:38 AM
 */
public class BrownCorpusTranslator
{
    private Map<String, String> meanings = new HashMap<String, String>();


    public BrownCorpusTranslator()
    {

        meanings.put(".",
                     "sentence closer (. ; ? *)");
        meanings.put("(",
                     "left paren");
        meanings.put(")",
                     "right paren");
        meanings.put("*",
                     "not, n't");
        meanings.put("--",
                     "dash");
        meanings.put(",",
                     "comma");
        meanings.put(":",
                     "colon");
        meanings.put("ABL",
                     "pre-qualifier (quite, rather)");
        meanings.put("ABN",
                     "pre-quantifier (half, all)");
        meanings.put("ABX",
                     "pre-quantifier (both)");
        meanings.put("AP",
                     "post-determiner (many, several, next)");
        meanings.put("AT",
                     "article (a, the, no)");
        meanings.put("BE",
                     "be");
        meanings.put("BED",
                     "were");
        meanings.put("BEDZ",
                     "	was");
        meanings.put("BEG",
                     "being");
        meanings.put("BEM",
                     "am");
        meanings.put("BEN",
                     "been");
        meanings.put("BER",
                     "are, art");
        meanings.put("BEZ",
                     "is");
        meanings.put("CC",
                     "coordinating conjunction (and, or)");
        meanings.put("CD",
                     "cardinal numeral (one, two, 2, etc.)");
        meanings.put("CS",
                     "subordinating conjunction (if, although)");
        meanings.put("DO",
                     "do");
        meanings.put("DOD",
                     "did");
        meanings.put("DOZ",
                     "does");
        meanings.put("DT",
                     "singular determiner/quantifier (this, that)");
        meanings.put("DTI",
                     "singular or plural determiner/quantifier (some, any)");
        meanings.put("DTS",
                     "plural determiner (these, those)");
        meanings.put("DTX",
                     "determiner/double conjunction (either)");
        meanings.put("EX",
                     "existential there");
        meanings.put("FW",
                     "foreign word (hyphenated before regular tag)");
        meanings.put("HV",
                     "have");
        meanings.put("HVD",
                     "had (past tense)");
        meanings.put("HVG",
                     "having");
        meanings.put("HVN",
                     "had (past participle)");
        meanings.put("IN",
                     "preposition");
        meanings.put("JJ",
                     "adjective");
        meanings.put("JJR",
                     "comparative adjective");
        meanings.put("JJS",
                     "semantically superlative adjective (chief, top)");
        meanings.put("JJT",
                     "morphologically superlative adjective (biggest)");
        meanings.put("MD",
                     "modal auxiliary (can, should, will)");
        meanings.put("NC",
                     "cited word (hyphenated after regular tag)");
        meanings.put("NN",
                     "singular or mass noun");
        meanings.put("NN$",
                     "possessive singular noun");
        meanings.put("NNS",
                     "plural noun");
        meanings.put("NNS$",
                     "	possessive plural noun");
        meanings.put("NP",
                     "proper noun or part of name phrase");
        meanings.put("NP$",
                     "possessive proper noun");
        meanings.put("NPS",
                     "plural proper noun");
        meanings.put("NPS$",
                     "	possessive plural proper noun");
        meanings.put("NR",
                     "adverbial noun (home, today, west)");
        meanings.put("OD",
                     "ordinal numeral (first, 2nd)");
        meanings.put("PN",
                     "nominal pronoun (everybody, nothing)");
        meanings.put("PN$",
                     "possessive nominal pronoun");
        meanings.put("PP$",
                     "possessive personal pronoun (my, our)");
        meanings.put("PP$$",
                     "	second (nominal) possessive pronoun (mine, ours)");
        meanings.put("PPL",
                     "singular reflexive/intensive personal pronoun (myself)");
        meanings.put("PPLS",
                     "	plural reflexive/intensive personal pronoun (ourselves)");
        meanings.put("PPO",
                     "objective personal pronoun (me, him, it, them)");
        meanings.put("PPS",
                     "3rd. singular nominative pronoun (he, she, it, one)");
        meanings.put("PPSS",
                     "	other nominative personal pronoun (I, we, they, you)");
        meanings.put("PRP",
                     "Personal pronoun");
        meanings.put("PRP$",
                     "	Possessive pronoun");
        meanings.put("QL",
                     "qualifier (very, fairly)");
        meanings.put("QLP",
                     "post-qualifier (enough, indeed)");
        meanings.put("RB",
                     "adverb");
        meanings.put("RBR",
                     "comparative adverb");
        meanings.put("RBT",
                     "superlative adverb");
        meanings.put("RN",
                     "nominal adverb (here, then, indoors)");
        meanings.put("RP",
                     "adverb/particle (about, off, up)");
        meanings.put("TO",
                     "infinitive marker to");
        meanings.put("UH",
                     "interjection, exclamation");
        meanings.put("VB",
                     "verb, base form");
        meanings.put("VBD",
                     "verb, past tense");
        meanings.put("VBG",
                     "verb, present participle/gerund");
        meanings.put("VBN",
                     "verb, past participle");
        meanings.put("VBP",
                     "verb, non 3rd person, singular, present");
        meanings.put("VBZ",
                     "verb, 3rd. singular present");
        meanings.put("WDT",
                     "wh- determiner (what, which)");
        meanings.put("WP$",
                     "possessive wh- pronoun (whose)");
        meanings.put("WPO",
                     "objective wh- pronoun (whom, which, that)");
        meanings.put("WPS",
                     "nominative wh- pronoun (who, which, that)");
        meanings.put("WQL",
                     "wh- qualifier (how)");
        meanings.put("WRB",
                     "wh- adverb (how, where, when)");


    }


    public String translate(List<String> strings)
    {

        String string = "";

        for (String el : strings)
        {
            try
            {
//                System.out.println(el);
                string = string.concat(meanings.get(el).concat(" - "));
            }
            catch (NullPointerException e)
            {
                string = string.concat(el);
            }

        }
        System.out.println(string);
        return string;
    }
}
