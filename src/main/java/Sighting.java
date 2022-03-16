import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Sighting {
    private int id;
    private String rangerName;
    private String kwsPin;
    private List<animal> rangerAnimalList;


    public Sighting( String rangerName, String kwsPin) {
        this.rangerName = rangerName;
        this.kwsPin = kwsPin;
        rangerAnimalList=allRangerIdSightings();
    }

    public List<animal> getRangerAnimalList() {
        return rangerAnimalList;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRangerName() {
        return rangerName;
    }

    public void setRangerName(String rangerName) {
        this.rangerName = rangerName;
    }

    public String getKwsPin() {
        return kwsPin;
    }

    public void setKwsPin(String kwsPin) {
        this.kwsPin = kwsPin;
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangerSighting (rangerName, kwsPin ) VALUES (:rangerName, :kwsPin)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", getRangerName())
                    .addParameter("kwsPin", getKwsPin())
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Sighting> all() {
        String sql = "SELECT * FROM rangerSighting";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sighting.class);
        }
    }
    public List<animal> allRangerIdSightings() {
        String sql = "SELECT * FROM animals WHERE rangerid=:id";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id",this.getId())
                    .executeAndFetch(animal.class);

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sighting sighting = (Sighting) o;
        return Objects.equals(rangerName, sighting.rangerName) &&
                Objects.equals(kwsPin, sighting.kwsPin) &&
                Objects.equals(rangerAnimalList, sighting.rangerAnimalList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rangerName, kwsPin, rangerAnimalList);
    }


}
