import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SightingTest {
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
    public Sighting  testerSighting(){
        return new Sighting("frank", "34567");
    }
    @Test
    public void Sighting_instantiatesCorrectly_true() {
        Sighting testerSighting =  testerSighting();
        assertEquals(true, testerSighting instanceof Sighting);
    }

    @Test
    public void Animal_instantiatesWithRangerName_String() {
         Sighting testerSighting =  testerSighting();
        assertEquals("frank", testerSighting.getRangerName());
    }

    @Test
    public void Animal_instantiatesWithkwsPin_String() {
         Sighting testerSighting =  testerSighting();
        assertEquals("34567", testerSighting.getKwsPin());
    }
    @Test
    public void Animal_savedInTestDb() {
         Sighting testerSighting =  testerSighting();
        Sighting cow= new Sighting("dan", "3456");
        Sighting dog=new Sighting("dave", "3456");
        testerSighting.save();
        cow.save();
        dog.save();
        assertEquals(Sighting.all().get(0).getRangerName(),"frank");
    }
//    @Test
//    public void animalsSpottedByRanger_list() {
//        Sighting testerSighting =  testerSighting();
//        animal cow= new animal("cow", 1,"adult","Serengeti", "Healthy", "Endangered");
//        animal dog=new animal("dog", 1,"adult","Serengeti", "Healthy", "Endangered");
//        cow.save();
//        dog.save();
//        testerSighting.save();
//        assertEquals(2, testerSighting.getRangerAnimalList().size());
//    }
}