
import org.junit.rules.ExternalResource;
        import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "dorcas","njoka17%");
    }

    @Override
    protected void after() {
        try(Connection con = DB.sql2o.open()) {
            String deleteAnimals = "DELETE FROM animals *;";
            String deleteRangerSighting = "DELETE FROM rangerSighting *;";
            con.createQuery(deleteAnimals).executeUpdate();
            con.createQuery(deleteRangerSighting).executeUpdate();
        }
    }

}