import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;

class animalTest {
    @BeforeEach
    public void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "dorcas","njoka17%");
    }
    @AfterEach
    public void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimals = "DELETE FROM animals *;";
            String deleteRangerSighting = "DELETE FROM rangerSighting *;";
            con.createQuery(deleteAnimals).executeUpdate();
            con.createQuery(deleteRangerSighting).executeUpdate();
        }
    }

    public animal  testerAnimal(){
        return new animal("Buffalo", 3,"adult","Serengeti", "Healthy", "Endangered");
    }

    @Test
    public void animal_instantiatesCorrectly_true() {
        animal testerAnimal =  testerAnimal();
        assertEquals(true, testerAnimal instanceof animal);
    }

    @Test
    public void Animal_instantiatesWithName_String() {
        animal testerAnimal =  testerAnimal();
        assertEquals("Buffalo", testerAnimal.getName());
    }

    @Test
    public void Animal_instantiatesWithlocation_String() {
        animal testerAnimal =  testerAnimal();
        assertEquals("Serengeti", testerAnimal.getLocation());
    }

    @Test
    public void Animal_instantiatesWithRangerId_int() {
        animal testerAnimal =  testerAnimal();
        assertEquals(3, testerAnimal.getRangerId());
    }

    @Test
    public void Animal_instantiatesWithHealth_String() {
        animal testerAnimal =  testerAnimal();
        assertEquals("Healthy", testerAnimal.getHealth());
    }

    @Test
    public void Animal_instantiatesWithAge_String() {
        animal testerAnimal =  testerAnimal();
        assertEquals("adult", testerAnimal.getAge());
    }

    @Test
    public void Animal_instantiatesWithDangerStatus_String() {
        animal testerAnimal =  testerAnimal();

        assertEquals("Endangered", testerAnimal.getDangerStatus());
    }
    @Test
    public void Animal_savedInTestDb() {
        animal testerAnimal =  testerAnimal();
        animal cow= new animal("cow", 3,"adult","Serengeti", "Healthy", "Endangered");
        animal dog=new animal("dog", 3,"adult","Serengeti", "Healthy", "Endangered");
        testerAnimal.save();
        cow.save();
        dog.save();
        assertEquals(cow.getLocation(), dog.getLocation());
    }

}