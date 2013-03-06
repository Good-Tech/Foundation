package org.foundation;



import org.foundation.ml.nlp.Label;
import org.goodgod.controller.Action;
import org.goodgod.controller.Message;
import org.goodgod.controller.Statement;
import org.goodgod.controller.When;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

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
 * Created 2/26/13 4:01 AM
 */
public class TestApplication
{

    @Test
    public void testScottPlantingACrop() throws Exception
    {
        Person scott = new Person();
        Crop myCrop = new Crop();
        PlantCropActivity plantCropActivity = new PlantCropActivity(myCrop, scott);

        assertEquals(3, myCrop.getPlants().size());

    }

    public class Plant extends Foundation {
        private String id;
        private Label  name;


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

    public class PlantedPlant extends Foundation {
        private String id;
        private Label  name;
        private Plant plant;


        public Plant getPlant()
        {

            return plant;
        }


        public void setPlant(Plant plant)
        {

            this.plant = plant;
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

    public class Crop extends Foundation {
        private String id;
        private Label  name;

        private List<PlantedPlant> plants;


        public List<PlantedPlant> getPlants()
        {

            return plants;
        }


        public void setPlants(List<PlantedPlant> plants)
        {

            this.plants = plants;
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


    public class PlantCropActivity extends Foundation
    {

        private String id;
        private Label  name;
        private Crop   crop;
        private Person scott;


        public PlantCropActivity(Crop crop, Person scott)
        {

            registerListnersOfClass(this);

            this.crop = crop;
            this.scott = scott;

            this.says("Lets start planting.",
                      scott);

        }


        @Action(
                name = "Add a plant.",
                documentation = "You have just added a plant.")
        public void addPlant(Message<Plant> plant)
        {

            PlantedPlant plantedPlant = new PlantedPlant();
            plantedPlant.setPlant(plant.getData());

            if (null == crop.getPlants())
            {
                crop.setPlants(new ArrayList<PlantedPlant>());
            }

            crop.getPlants().add(plantedPlant);
        }


        @When(
                name = "plant was added",
                documentation = "A plant was added to the crop.")
        public void handlePlantAdded(Message<Plant> plant)
        {

        }

        //        @Statement(
        //                name = "A plant was added to the crop.",
        //                documentation = "You have just added a plant to the crop."
        //        )


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


    public class Task extends Foundation
    {
        private String id;
        private Label  name;
        public boolean comleted = false;


        @Action(
                name = "set task to completed state",
                documentation = "You did finish your current task.")
        public void didFinishTask(Message<Person> message)
        {
            comleted = true;
            says("Task is marked completed",
                 message.getData());
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


    public class Person extends Foundation
    {
        private String id;
        private Label  name;
        private Task    task     = new Task();
        public  boolean hasTasks = true;


        public Task getTask()
        {

            return task;
        }


        private Person()
        {

            registerListnersOfClass(this);
        }

        @Statement(
                name = "Lets start planting.",
                documentation = "The PlantCropActivity would like to start planting."
        )
        public void startPlanting (Message<PlantCropActivity> activity) {
            activity.getData().says("Add a plant.",
                                    new Plant());
            activity.getData().says("Add a plant.", new Plant());
            activity.getData().says("Add a plant.", new Plant());
        }

        @When(
                name = "Task is marked completed",
                documentation = "A task you were working on is now marked complete.")
        public void taskDidGetMarkedComplete(Message<Task> message)
        {

            hasTasks = false;
        }


        @Statement(
                name = "Task completed",
                documentation = "You have completed your current task.")
        public void completedCurrentTask(Message<Task> message)
        {

            says("set task to completed state",
                 task);
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
