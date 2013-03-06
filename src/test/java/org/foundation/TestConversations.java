package org.foundation;



import org.foundation.aop.JavascriptBridgeCompiler;
import org.foundation.ml.nlp.Label;
import org.goodgod.controller.Action;
import org.goodgod.controller.Message;
import org.goodgod.controller.Statement;
import org.goodgod.controller.When;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
 * Created 2/26/13 2:51 AM
 */
public class TestConversations
{
    @Test
    public void testSpeaking() throws Exception
    {

        Person scott = new Person();

        scott.says("Task completed", scott.getTask());

        JavascriptBridgeCompiler.compileController(Person.class, scott);
        System.out.print(JavascriptBridgeCompiler.compileController(Person.class, scott));
        assertTrue(scott.getTask().comleted);
        assertFalse(scott.hasTasks);

    }

    public class Task extends Foundation {
        private String id;
        private Label  name;
        public boolean comleted = false;


        @Action(
                name = "set task to completed state",
                documentation = "You did finish your current task."
        )
        public void didFinishTask (Message<Person> message) {
            comleted = true;
            says("Task is marked completed", message.getData());
        }


        private Task()
        {
            registerListnersOfClass(this);

        }


        public String getId()
        {

            return id;
        }


        public void setId(String id)
        {

            this.id = id;
        }


        public Label getName()
        {

            return name;
        }


        public void setName(Label name)
        {

            this.name = name;
        }
    }

    public class Person extends Foundation {
        private String id;
        private Label  name;
        private Task task = new Task();
        public boolean hasTasks = true;


        public Task getTask()
        {

            return task;
        }


        private Person()
        {
            registerListnersOfClass(this);
        }


        @When(
                name = "Task is marked completed",
                documentation = "A task you were working on is now marked complete."
        )
        public void taskDidGetMarkedComplete (Message<Task> message) {
            hasTasks = false;
        }

        @Statement(
                name = "Task completed",
                documentation = "You have completed your current task."
        )
        public void completedCurrentTask (Message<Task> message) {
            says("set task to completed state", task);
        }

        public String getId()
        {

            return id;
        }


        public void setId(String id)
        {

            this.id = id;
        }


        public Label getName()
        {

            return name;
        }


        public void setName(Label name)
        {

            this.name = name;
        }
    }
}
