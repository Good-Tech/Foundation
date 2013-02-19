package org.foundation.ml;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
 * Created 2/18/13 3:01 AM
 */
public class LabelCloudTest
{
    @Before
    public void setUp() throws Exception
    {

    }


    @After
    public void tearDown() throws Exception
    {

    }


    @Test
    public void testAddLabel() throws Exception
    {

    }


    @Test
    public void testGetLabel() throws Exception
    {

    }


    @Test
    public void testRemoveLabel() throws Exception
    {

    }


    @Test
    public void testGetClassificationsContaining() throws Exception
    {

    }

    public void testGetClassificationClosestTo() throws Exception
    {
//        getClassificationClosestTo
    }

    @Test(timeout=1000000)
    public void testGetLabelsLike() throws Exception
    {
        LabelCloud.getInstance().addLabel(Label.create("Hello world"));
        LabelCloud.getInstance().addLabel(Label.create("The quick brown fox jumps over the lazy dog."));
        LabelCloud.getInstance().addLabel(Label.create("What is happening my friend?"));
        LabelCloud.getInstance().addLabel(Label.create("It is a beautiful world we live in."));
        LabelCloud.getInstance().addLabel(Label.create("Kale is amazing!"));
        LabelCloud.getInstance().addLabel(Label.create("Yeah buddy"));
        LabelCloud.getInstance().addLabel(Label.create("My dog is my best buddy"));
        LabelCloud.getInstance().addLabel(Label.create("The capacitance of a dielectric is directly proportional to it's surface area and inversely proportional to it's thickness."));
        LabelCloud.getInstance().addLabel(Label.create("Full speed ahead captain."));

        textBlobToLabelList(nineafor);

        List<Label> labelList = LabelCloud.getInstance().getLabelsLike("how are you today");

        System.out.println("\n\nLABELS LIKE");
        System.out.println("--------------------------------------------");

        for (Label label : labelList) {
            System.out.println(label.getValue());
            System.out.println("\n");


        }

        System.out.println("\n" + "\nCLASSIFICATIONS CONTAINING");
        System.out.println("--------------------------------------------");

        List<LabelClassification> classifications = LabelCloud.getInstance().getClassificationsContaining("voice");

        for (LabelClassification label : classifications) {
            System.out.println(label.getLabel().getValue());
            System.out.println("\n");
        }

        System.out.println("\n" + "\nCLASSIFICATION CLOSEST TO");
        System.out.println("--------------------------------------------");

        System.out.println(LabelCloud.getInstance().getClassificationClosestTo("window").getLabel().getValue());

        System.out.println("\n");
    }

    private static List<Label> textBlobToLabelList(String blob) {
        List<Label> labels = new ArrayList<Label>();

        String[] split = blob.split("\\.");

        for (String str : split) {
            Label label = Label.create(str.toLowerCase());
            labels.add(label);
            LabelCloud.getInstance().addLabel(label);
        }



        return labels;
    }

    private static String nineafor = "It was a bright cold day in April, and the clocks were striking thirteen. Winston Smith, his chin nuzzled into his breast in an effort to escape the vile wind, slipped quickly through the glass doors of Victory Mansions, though not quickly enough to prevent a swirl of gritty dust from entering along with him. \n" +
            "\n" +
            "The hallway smelt of boiled cabbage and old rag mats. At one end of it a coloured poster, too large for indoor display, had been tacked to the wall. It depicted simply an enormous face, more than a metre wide: the face of a man of about forty-five, with a heavy black moustache and ruggedly handsome features. Winston made for the stairs. It was no use trying the lift. Even at the best of times it was seldom working, and at present the electric current was cut off during daylight hours. It was part of the economy drive in preparation for Hate Week. The flat was seven flights up, and Winston, who was thirty-nine and had a varicose ulcer above his right ankle, went slowly, resting several times on the way. On each landing, opposite the lift-shaft, the poster with the enormous face gazed from the wall. It was one of those pictures which are so contrived that the eyes follow you about when you move. BIG BROTHER IS WATCHING YOU, the caption beneath it ran. \n" +
            "\n" +
            "Inside the flat a fruity voice was reading out a list of figures which had something to do with the production of pig-iron. The voice came from an oblong metal plaque like a dulled mirror which formed part of the surface of the right-hand wall. Winston turned a switch and the voice sank somewhat, though the words were still distinguishable. The instrument (the telescreen, it was called) could be dimmed, but there was no way of shutting it off completely. He moved over to the window: a smallish, frail figure, the meagreness of his body merely emphasized by the blue overalls which were the uniform of the party. His hair was very fair, his face naturally sanguine, his skin roughened by coarse soap and blunt razor blades and the cold of the winter that had just ended. \n" +
            "\n" +
            "Outside, even through the shut window-pane, the world looked cold. Down in the street little eddies of wind were whirling dust and torn paper into spirals, and though the sun was shining and the sky a harsh blue, there seemed to be no colour in anything, except the posters that were plastered everywhere. The blackmoustachio'd face gazed down from every commanding corner. There was one on the house-front immediately opposite. BIG BROTHER IS WATCHING YOU, the caption said, while the dark eyes looked deep into Winston's own. Down at streetlevel another poster, torn at one corner, flapped fitfully in the wind, alternately covering and uncovering the single word INGSOC. In the far distance a helicopter skimmed down between the roofs, hovered for an instant like a bluebottle, and darted away again with a curving flight. It was the police patrol, snooping into people's windows. The patrols did not matter, however. Only the Thought Police mattered. \n" +
            "\n" +
            "Behind Winston's back the voice from the telescreen was still babbling away about pig-iron and the overfulfilment of the Ninth Three-Year Plan. The telescreen received and transmitted simultaneously. Any sound that Winston made, above the level of a very low whisper, would be picked up by it, moreover, so long as he remained within the field of vision which the metal plaque commanded, he could be seen as well as heard. There was of course no way of knowing whether you were being watched at any given moment. How often, or on what system, the Thought Police plugged in on any individual wire was guesswork. It was even conceivable that they watched everybody all the time. But at any rate they could plug in your wire whenever they wanted to. You had to live -- did live, from habit that became instinct -- in the assumption that every sound you made was overheard, and, except in darkness, every movement scrutinized. \n" +
            "\n" +
            "Winston kept his back turned to the telescreen. It was safer, though, as he well knew, even a back can be revealing. A kilometre away the Ministry of Truth, his place of work, towered vast and white above the grimy landscape. This, he thought with a sort of vague distaste -- this was London, chief city of Airstrip One, itself the third most populous of the provinces of Oceania. He tried to squeeze out some childhood memory that should tell him whether London had always been quite like this. Were there always these vistas of rotting nineteenth-century houses, their sides shored up with baulks of timber, their windows patched with cardboard and their roofs with corrugated iron, their crazy garden walls sagging in all directions? And the bombed sites where the plaster dust swirled in the air and the willow-herb straggled over the heaps of rubble; and the places where the bombs had cleared a larger patch and there had sprung up sordid colonies of wooden dwellings like chicken-houses? But it was no use, he could not remember: nothing remained of his childhood except a series of bright-lit tableaux occurring against no background and mostly unintelligible. \n" +
            "\n" +
            "The Ministry of Truth -- Minitrue, in Newspeak -- was startlingly different from any other object in sight. It was an enormous pyramidal structure of glittering white concrete, soaring up, terrace after terrace, 300 metres into the air. From where Winston stood it was just possible to read, picked out on its white face in elegant lettering, the three slogans of the Party: \n" +
            "\n" +
            "WAR IS PEACE \n" +
            "\n" +
            "FREEDOM IS SLAVERY \n" +
            "\n" +
            "IGNORANCE IS STRENGTH \n" +
            "\n" +
            "The Ministry of Truth contained, it was said, three thousand rooms above ground level, and corresponding ramifications below. Scattered about London there were just three other buildings of similar appearance and size. So completely did they dwarf the surrounding architecture that from the roof of Victory Mansions you could see all four of them simultaneously. They were the homes of the four Ministries between which the entire apparatus of government was divided. The Ministry of Truth, which concerned itself with news, entertainment, education, and the fine arts. The Ministry of Peace, which concerned itself with war. The Ministry of Love, which maintained law and order. And the Ministry of Plenty, which was responsible for economic affairs. Their names, in Newspeak: Minitrue, Minipax, Miniluv, and Miniplenty. \n" +
            "\n" +
            "The Ministry of Love was the really frightening one. There were no windows in it at all. Winston had never been inside the Ministry of Love, nor within half a kilometre of it. It was a place impossible to enter except on official business, and then only by penetrating through a maze of barbed-wire entanglements, steel doors, and hidden machine-gun nests. Even the streets leading up to its outer barriers were roamed by gorilla-faced guards in black uniforms, armed with jointed truncheons. \n" +
            "\n" +
            "Winston turned round abruptly. He had set his features into the expression of quiet optimism which it was advisable to wear when facing the telescreen. He crossed the room into the tiny kitchen. By leaving the Ministry at this time of day he had sacrificed his lunch in the canteen, and he was aware that there was no food in the kitchen except a hunk of dark-coloured bread which had got to be saved for tomorrow's breakfast. He took down from the shelf a bottle of colourless liquid with a plain white label marked VICTORY GIN. It gave off a sickly, oily smell, as of Chinese ricespirit. Winston poured out nearly a teacupful, nerved himself for a shock, and gulped it down like a dose of medicine. \n" +
            "\n" +
            "Instantly his face turned scarlet and the water ran out of his eyes. The stuff was like nitric acid, and moreover, in swallowing it one had the sensation of being hit on the back of the head with a rubber club. The next moment, however, the burning in his belly died down and the world began to look more cheerful. He took a cigarette from a crumpled packet marked VICTORY CIGARETTES and incautiously held it upright, whereupon the tobacco fell out on to the floor. With the next he was more successful. He went back to the living-room and sat down at a small table that stood to the left of the telescreen. From the table drawer he took out a penholder, a bottle of ink, and a thick, quarto-sized blank book with a red back and a marbled cover. \n" +
            "\n" +
            "For some reason the telescreen in the living-room was in an unusual position. Instead of being placed, as was normal, in the end wall, where it could command the whole room, it was in the longer wall, opposite the window. To one side of it there was a shallow alcove in which Winston was now sitting, and which, when the flats were built, had probably been intended to hold bookshelves. By sitting in the alcove, and keeping well back, Winston was able to remain outside the range of the telescreen, so far as sight went. He could be heard, of course, but so long as he stayed in his present position he could not be seen. It was partly the unusual geography of the room that had suggested to him the thing that he was now about to do. \n" +
            "\n" +
            "But it had also been suggested by the book that he had just taken out of the drawer. It was a peculiarly beautiful book. Its smooth creamy paper, a little yellowed by age, was of a kind that had not been manufactured for at least forty years past. He could guess, however, that the book was much older than that. He had seen it lying in the window of a frowsy little junk-shop in a slummy quarter of the town (just what quarter he did not now remember) and had been stricken immediately by an overwhelming desire to possess it. Party members were supposed not to go into ordinary shops ('dealing on the free market', it was called), but the rule was not strictly kept, because there were various things, such as shoelaces and razor blades, which it was impossible to get hold of in any other way. He had given a quick glance up and down the street and then had slipped inside and bought the book for two dollars fifty. At the time he was not conscious of wanting it for any particular purpose. He had carried it guiltily home in his briefcase. Even with nothing written in it, it was a compromising possession. \n" +
            "\n" +
            "The thing that he was about to do was to open a diary. This was not illegal (nothing was illegal, since there were no longer any laws), but if detected it was reasonably certain that it would be punished by death, or at least by twenty-five years in a forced-labour camp. Winston fitted a nib into the penholder and sucked it to get the grease off. The pen was an archaic instrument, seldom used even for signatures, and he had procured one, furtively and with some difficulty, simply because of a feeling that the beautiful creamy paper deserved to be written on with a real nib instead of being scratched with an ink-pencil. Actually he was not used to writing by hand. Apart from very short notes, it was usual to dictate everything into the speakwrite which was of course impossible for his present purpose. He dipped the pen into the ink and then faltered for just a second. A tremor had gone through his bowels. To mark the paper was the decisive act. In small clumsy letters he wrote: \n" +
            "\n" +
            "April 4th, 1984. \n" +
            "\n" +
            "He sat back. A sense of complete helplessness had descended upon him. To begin with, he did not know with any certainty that this was 1984. It must be round about that date, since he was fairly sure that his age was thirty-nine, and he believed that he had been born in 1944 or 1945; but it was never possible nowadays to pin down any date within a year or two. \n" +
            "\n" +
            "For whom, it suddenly occurred to him to wonder, was he writing this diary? For the future, for the unborn. His mind hovered for a moment round the doubtful date on the page, and then fetched up with a bump against the Newspeak word doublethink. For the first time the magnitude of what he had undertaken came home to him. How could you communicate with the future? It was of its nature impossible. Either the future would resemble the present, in which case it would not listen to him: or it would be different from it, and his predicament would be meaningless. \n" +
            "\n" +
            "For some time he sat gazing stupidly at the paper. The telescreen had changed over to strident military music. It was curious that he seemed not merely to have lost the power of expressing himself, but even to have forgotten what it was that he had originally intended to say. For weeks past he had been making ready for this moment, and it had never crossed his mind that anything would be needed except courage. The actual writing would be easy. All he had to do was to transfer to paper the interminable restless monologue that had been running inside his head, literally for years. At this moment, however, even the monologue had dried up. Moreover his varicose ulcer had begun itching unbearably. He dared not scratch it, because if he did so it always became inflamed. The seconds were ticking by. He was conscious of nothing except the blankness of the page in front of him, the itching of the skin above his ankle, the blaring of the music, and a slight booziness caused by the gin. \n" +
            "\n" +
            "Suddenly he began writing in sheer panic, only imperfectly aware of what he was setting down. His small but childish handwriting straggled up and down the page, shedding first its capital letters and finally even its full stops: \n" +
            "\n" +
            "April 4th, 1984. Last night to the flicks. All war films. One very good one of a ship full of refugees being bombed somewhere in the Mediterranean. Audience much amused by shots of a great huge fat man trying to swim away with a helicopter after him, first you saw him wallowing along in the water like a porpoise, then you saw him through the helicopters gunsights, then he was full of holes and the sea round him turned pink and he sank as suddenly as though the holes had let in the water, audience shouting with laughter when he sank. then you saw a lifeboat full of children with a helicopter hovering over it. there was a middle-aged woman might have been a jewess sitting up in the bow with a little boy about three years old in her arms. little boy screaming with fright and hiding his head between her breasts as if he was trying to burrow right into her and the woman putting her arms round him and comforting him although she was blue with fright herself, all the time covering him up as much as possible as if she thought her arms could keep the bullets off him. then the helicopter planted a 20 kilo bomb in among them terrific flash and the boat went all to matchwood. then there was a wonderful shot of a child's arm going up up up right up into the air a helicopter with a camera in its nose must have followed it up and there was a lot of applause from the party seats but a woman down in the prole part of the house suddenly started kicking up a fuss and shouting they didnt oughter of showed it not in front of kids they didnt it aint right not in front of kids it aint until the police turned her turned her out i dont suppose anything happened to her nobody cares what the proles say typical prole reaction they never -- \n" +
            "\n" +
            "Winston stopped writing, partly because he was suffering from cramp. He did not know what had made him pour out this stream of rubbish. But the curious thing was that while he was doing so a totally different memory had clarified itself in his mind, to the point where he almost felt equal to writing it down. It was, he now realized, because of this other incident that he had suddenly decided to come home and begin the diary today. \n" +
            "\n" +
            "It had happened that morning at the Ministry, if anything so nebulous could be said to happen. \n" +
            "\n" +
            "It was nearly eleven hundred, and in the Records Department, where Winston worked, they were dragging the chairs out of the cubicles and grouping them in the centre of the hall opposite the big telescreen, in preparation for the Two Minutes Hate. Winston was just taking his place in one of the middle rows when two people whom he knew by sight, but had never spoken to, came unexpectedly into the room. One of them was a girl whom he often passed in the corridors. He did not know her name, but he knew that she worked in the Fiction Department. Presumably -- since he had sometimes seen her with oily hands and carrying a spanner she had some mechanical job on one of the novel-writing machines. She was a bold-looking girl, of about twenty-seven, with thick hair, a freckled face, and swift, athletic movements. A narrow scarlet sash, emblem of the Junior Anti-Sex League, was wound several times round the waist of her overalls, just tightly enough to bring out the shapeliness of her hips. Winston had disliked her from the very first moment of seeing her. He knew the reason. It was because of the atmosphere of hockey-fields and cold baths and community hikes and general clean-mindedness which she managed to carry about with her. He disliked nearly all women, and especially the young and pretty ones. It was always the women, and above all the young ones, who were the most bigoted adherents of the Party, the swallowers of slogans, the amateur spies and nosers-out of unorthodoxy. But this particular girl gave him the impression of being more dangerous than most. Once when they passed in the corridor she gave him a quick sidelong glance which seemed to pierce right into him and for a moment had filled him with black terror. The idea had even crossed his mind that she might be an agent of the Thought Police. That, it was true, was very unlikely. Still, he continued to feel a peculiar uneasiness, which had fear mixed up in it as well as hostility, whenever she was anywhere near him. \n" +
            "\n" +
            "The other person was a man named O'Brien, a member of the Inner Party and holder of some post so important and remote that Winston had only a dim idea of its nature. A momentary hush passed over the group of people round the chairs as they saw the black overalls of an Inner Party member approaching. O'Brien was a large, burly man with a thick neck and a coarse, humorous, brutal face. In spite of his formidable appearance he had a certain charm of manner. He had a trick of resettling his spectacles on his nose which was curiously disarming -- in some indefinable way, curiously civilized. It was a gesture which, if anyone had still thought in such terms, might have recalled an eighteenth-century nobleman offering his snuffbox. Winston had seen O'Brien perhaps a dozen times in almost as many years. He felt deeply drawn to him, and not solely because he was intrigued by the contrast between O'Brien's urbane manner and his prize-fighter's physique. Much more it was because of a secretly held belief -- or perhaps not even a belief, merely a hope -- that O'Brien's political orthodoxy was not perfect. Something in his face suggested it irresistibly. And again, perhaps it was not even unorthodoxy that was written in his face, but simply intelligence. But at any rate he had the appearance of being a person that you could talk to if somehow you could cheat the telescreen and get him alone. Winston had never made the smallest effort to verify this guess: indeed, there was no way of doing so. At this moment O'Brien glanced at his wrist-watch, saw that it was nearly eleven hundred, and evidently decided to stay in the Records Department until the Two Minutes Hate was over. He took a chair in the same row as Winston, a couple of places away. A small, sandy-haired woman who worked in the next cubicle to Winston was between them. The girl with dark hair was sitting immediately behind. \n" +
            "\n" +
            "The next moment a hideous, grinding speech, as of some monstrous machine running without oil, burst from the big telescreen at the end of the room. It was a noise that set one's teeth on edge and bristled the hair at the back of one's neck. The Hate had started. \n" +
            "\n" +
            "As usual, the face of Emmanuel Goldstein, the Enemy of the People, had flashed on to the screen. There were hisses here and there among the audience. The little sandy-haired woman gave a squeak of mingled fear and disgust. Goldstein was the renegade and backslider who once, long ago (how long ago, nobody quite remembered), had been one of the leading figures of the Party, almost on a level with Big Brother himself, and then had engaged in counter-revolutionary activities, had been condemned to death, and had mysteriously escaped and disappeared. The programmes of the Two Minutes Hate varied from day to day, but there was none in which Goldstein was not the principal figure. He was the primal traitor, the earliest defiler of the Party's purity. All subsequent crimes against the Party, all treacheries, acts of sabotage, heresies, deviations, sprang directly out of his teaching. Somewhere or other he was still alive and hatching his conspiracies: perhaps somewhere beyond the sea, under the protection of his foreign paymasters, perhaps even -- so it was occasionally rumoured -- in some hiding-place in Oceania itself. \n" +
            "\n" +
            "Winston's diaphragm was constricted. He could never see the face of Goldstein without a painful mixture of emotions. It was a lean Jewish face, with a great fuzzy aureole of white hair and a small goatee beard -- a clever face, and yet somehow inherently despicable, with a kind of senile silliness in the long thin nose, near the end of which a pair of spectacles was perched. It resembled the face of a sheep, and the voice, too, had a sheep-like quality. Goldstein was delivering his usual venomous attack upon the doctrines of the Party -- an attack so exaggerated and perverse that a child should have been able to see through it, and yet just plausible enough to fill one with an alarmed feeling that other people, less level-headed than oneself, might be taken in by it. He was abusing Big Brother, he was denouncing the dictatorship of the Party, he was demanding the immediate conclusion of peace with Eurasia, he was advocating freedom of speech, freedom of the Press, freedom of assembly, freedom of thought, he was crying hysterically that the revolution had been betrayed -- and all this in rapid polysyllabic speech which was a sort of parody of the habitual style of the orators of the Party, and even contained Newspeak words: more Newspeak words, indeed, than any Party member would normally use in real life. And all the while, lest one should be in any doubt as to the reality which Goldstein's specious claptrap covered, behind his head on the telescreen there marched the endless columns of the Eurasian army -- row after row of solid-looking men with expressionless Asiatic faces, who swam up to the surface of the screen and vanished, to be replaced by others exactly similar. The dull rhythmic tramp of the soldiers' boots formed the background to Goldstein's bleating voice. \n" +
            "\n" +
            "Before the Hate had proceeded for thirty seconds, uncontrollable exclamations of rage were breaking out from half the people in the room. The self-satisfied sheep-like face on the screen, and the terrifying power of the Eurasian army behind it, were too much to be borne: besides, the sight or even the thought of Goldstein produced fear and anger automatically. He was an object of hatred more constant than either Eurasia or Eastasia, since when Oceania was at war with one of these Powers it was generally at peace with the other. But what was strange was that although Goldstein was hated and despised by everybody, although every day and a thousand times a day, on platforms, on the telescreen, in newspapers, in books, his theories were refuted, smashed, ridiculed, held up to the general gaze for the pitiful rubbish that they were in spite of all this, his influence never seemed to grow less. Always there were fresh dupes waiting to be seduced by him. A day never passed when spies and saboteurs acting under his directions were not unmasked by the Thought Police. He was the commander of a vast shadowy army, an underground network of conspirators dedicated to the overthrow of the State. The Brotherhood, its name was supposed to be. There were also whispered stories of a terrible book, a compendium of all the heresies, of which Goldstein was the author and which circulated clandestinely here and there. It was a book without a title. People referred to it, if at all, simply as the book. But one knew of such things only through vague rumours. Neither the Brotherhood nor the book was a subject that any ordinary Party member would mention if there was a way of avoiding it. \n" +
            "\n" +
            "In its second minute the Hate rose to a frenzy. People were leaping up and down in their places and shouting at the tops of their voices in an effort to drown the maddening bleating voice that came from the screen. The little sandy-haired woman had turned bright pink, and her mouth was opening and shutting like that of a landed fish. Even O'Brien's heavy face was flushed. He was sitting very straight in his chair, his powerful chest swelling and quivering as though he were standing up to the assault of a wave. The dark-haired girl behind Winston had begun crying out 'Swine! Swine! Swine!' and suddenly she picked up a heavy Newspeak dictionary and flung it at the screen. It struck Goldstein's nose and bounced off; the voice continued inexorably. In a lucid moment Winston found that he was shouting with the others and kicking his heel violently against the rung of his chair. The horrible thing about the Two Minutes Hate was not that one was obliged to act a part, but, on the contrary, that it was impossible to avoid joining in. Within thirty seconds any pretence was always unnecessary. A hideous ecstasy of fear and vindictiveness, a desire to kill, to torture, to smash faces in with a sledge-hammer, seemed to flow through the whole group of people like an electric current, turning one even against one's will into a grimacing, screaming lunatic. And yet the rage that one felt was an abstract, undirected emotion which could be switched from one object to another like the flame of a blowlamp. Thus, at one moment Winston's hatred was not turned against Goldstein at all, but, on the contrary, against Big Brother, the Party, and the Thought Police; and at such moments his heart went out to the lonely, derided heretic on the screen, sole guardian of truth and sanity in a world of lies. And yet the very next instant he was at one with the people about him, and all that was said of Goldstein seemed to him to be true. At those moments his secret loathing of Big Brother changed into adoration, and Big Brother seemed to tower up, an invincible, fearless protector, standing like a rock against the hordes of Asia, and Goldstein, in spite of his isolation, his helplessness, and the doubt that hung about his very existence, seemed like some sinister enchanter, capable by the mere power of his voice of wrecking the structure of civilization. \n" +
            "\n" +
            "It was even possible, at moments, to switch one's hatred this way or that by a voluntary act. Suddenly, by the sort of violent effort with which one wrenches one's head away from the pillow in a nightmare, Winston succeeded in transferring his hatred from the face on the screen to the dark-haired girl behind him. Vivid, beautiful hallucinations flashed through his mind. He would flog her to death with a rubber truncheon. He would tie her naked to a stake and shoot her full of arrows like Saint Sebastian. He would ravish her and cut her throat at the moment of climax. Better than before, moreover, he realized why it was that he hated her. He hated her because she was young and pretty and sexless, because he wanted to go to bed with her and would never do so, because round her sweet supple waist, which seemed to ask you to encircle it with your arm, there was only the odious scarlet sash, aggressive symbol of chastity. \n" +
            "\n" +
            "The Hate rose to its climax. The voice of Goldstein had become an actual sheep's bleat, and for an instant the face changed into that of a sheep. Then the sheep-face melted into the figure of a Eurasian soldier who seemed to be advancing, huge and terrible, his sub-machine gun roaring, and seeming to spring out of the surface of the screen, so that some of the people in the front row actually flinched backwards in their seats. But in the same moment, drawing a deep sigh of relief from everybody, the hostile figure melted into the face of Big Brother, black-haired, black-moustachio'd, full of power and mysterious calm, and so vast that it almost filled up the screen. Nobody heard what Big Brother was saying. It was merely a few words of encouragement, the sort of words that are uttered in the din of battle, not distinguishable individually but restoring confidence by the fact of being spoken. Then the face of Big Brother faded away again, and instead the three slogans of the Party stood out in bold capitals: \n" +
            "\n" +
            "WAR IS PEACE \n" +
            "\n" +
            "FREEDOM IS SLAVERY \n" +
            "\n" +
            "IGNORANCE IS STRENGTH \n" +
            "\n" +
            "But the face of Big Brother seemed to persist for several seconds on the screen, as though the impact that it had made on everyone's eyeballs was too vivid to wear off immediately. The little sandyhaired woman had flung herself forward over the back of the chair in front of her. With a tremulous murmur that sounded like 'My Saviour!' she extended her arms towards the screen. Then she buried her face in her hands. It was apparent that she was uttering a prayer. \n" +
            "\n" +
            "At this moment the entire group of people broke into a deep, slow, rhythmical chant of 'B-B! ...B-B!' -- over and over again, very slowly, with a long pause between the first 'B' and the second-a heavy, murmurous sound, somehow curiously savage, in the background of which one seemed to hear the stamp of naked feet and the throbbing of tom-toms. For perhaps as much as thirty seconds they kept it up. It was a refrain that was often heard in moments of overwhelming emotion. Partly it was a sort of hymn to the wisdom and majesty of Big Brother, but still more it was an act of self-hypnosis, a deliberate drowning of consciousness by means of rhythmic noise. Winston's entrails seemed to grow cold. In the Two Minutes Hate he could not help sharing in the general delirium, but this sub-human chanting of 'B-B! ...B-B!' always filled him with horror. Of course he chanted with the rest: it was impossible to do otherwise. To dissemble your feelings, to control your face, to do what everyone else was doing, was an instinctive reaction. But there was a space of a couple of seconds during which the expression of his eyes might conceivably have betrayed him. And it was exactly at this moment that the significant thing happened -- if, indeed, it did happen. \n" +
            "\n" +
            "Momentarily he caught O'Brien's eye. O'Brien had stood up. He had taken off his spectacles and was in the act of resettling them on his nose with his characteristic gesture. But there was a fraction of a second when their eyes met, and for as long as it took to happen Winston knew-yes, he knew!-that O'Brien was thinking the same thing as himself. An unmistakable message had passed. It was as though their two minds had opened and the thoughts were flowing from one into the other through their eyes. 'I am with you,' O'Brien seemed to be saying to him. 'I know precisely what you are feeling. I know all about your contempt, your hatred, your disgust. But don't worry, I am on your side!' And then the flash of intelligence was gone, and O'Brien's face was as inscrutable as everybody else's. \n" +
            "\n" +
            "That was all, and he was already uncertain whether it had happened. Such incidents never had any sequel. All that they did was to keep alive in him the belief, or hope, that others besides himself were the enemies of the Party. Perhaps the rumours of vast underground conspiracies were true after all -- perhaps the Brotherhood really existed! It was impossible, in spite of the endless arrests and confessions and executions, to be sure that the Brotherhood was not simply a myth. Some days he believed in it, some days not. There was no evidence, only fleeting glimpses that might mean anything or nothing: snatches of overheard conversation, faint scribbles on lavatory walls -- once, even, when two strangers met, a small movement of the hand which had looked as though it might be a signal of recognition. It was all guesswork: very likely he had imagined everything. He had gone back to his cubicle without looking at O'Brien again. The idea of following up their momentary contact hardly crossed his mind. It would have been inconceivably dangerous even if he had known how to set about doing it. For a second, two seconds, they had exchanged an equivocal glance, and that was the end of the story. But even that was a memorable event, in the locked loneliness in which one had to live. \n" +
            "\n" +
            "Winston roused himself and sat up straighter. He let out a belch. The gin was rising from his stomach. \n" +
            "\n" +
            "His eyes re-focused on the page. He discovered that while he sat helplessly musing he had also been writing, as though by automatic action. And it was no longer the same cramped, awkward handwriting as before. His pen had slid voluptuously over the smooth paper, printing in large neat capitals - \n" +
            "\n" +
            "DOWN WITH BIG BROTHER \n" +
            "\n" +
            "DOWN WITH BIG BROTHER \n" +
            "\n" +
            "DOWN WITH BIG BROTHER \n" +
            "\n" +
            "DOWN WITH BIG BROTHER \n" +
            "\n" +
            "DOWN WITH BIG BROTHER \n" +
            "\n" +
            "over and over again, filling half a page. \n" +
            "\n" +
            "He could not help feeling a twinge of panic. It was absurd, since the writing of those particular words was not more dangerous than the initial act of opening the diary, but for a moment he was tempted to tear out the spoiled pages and abandon the enterprise altogether. \n" +
            "\n" +
            "He did not do so, however, because he knew that it was useless. Whether he wrote DOWN WITH BIG BROTHER, or whether he refrained from writing it, made no difference. Whether he went on with the diary, or whether he did not go on with it, made no difference. The Thought Police would get him just the same. He had committed -- would still have committed, even if he had never set pen to paper -- the essential crime that contained all others in itself. Thoughtcrime, they called it. Thoughtcrime was not a thing that could be concealed for ever. You might dodge successfully for a while, even for years, but sooner or later they were bound to get you. \n" +
            "\n" +
            "It was always at night -- the arrests invariably happened at night. The sudden jerk out of sleep, the rough hand shaking your shoulder, the lights glaring in your eyes, the ring of hard faces round the bed. In the vast majority of cases there was no trial, no report of the arrest. People simply disappeared, always during the night. Your name was removed from the registers, every record of everything you had ever done was wiped out, your one-time existence was denied and then forgotten. You were abolished, annihilated: vaporized was the usual word. \n" +
            "\n" +
            "For a moment he was seized by a kind of hysteria. He began writing in a hurried untidy scrawl: \n" +
            "\n" +
            "theyll shoot me i don't care theyll shoot me in the back of the neck i dont care down with big brother they always shoot you in the back of the neck i dont care down with big brother -- \n" +
            "\n" +
            "He sat back in his chair, slightly ashamed of himself, and laid down the pen. The next moment he started violently. There was a knocking at the door. \n" +
            "\n" +
            "Already! He sat as still as a mouse, in the futile hope that whoever it was might go away after a single attempt. But no, the knocking was repeated. The worst thing of all would be to delay. His heart was thumping like a drum, but his face, from long habit, was probably expressionless. He got up and moved heavily towards the door.";
}
