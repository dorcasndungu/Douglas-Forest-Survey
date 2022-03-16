import org.sql2o.Connection;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.List;
import java.util.Objects;

public class animal {
    private int id;
    private String name;
    private int rangerId;
    private String age;
    private String location;
    private String health;
    private String dangerStatus;
    private Timestamp timeSeen;
    private String formattedTimeString;


    public animal(String name, int rangerId, String age, String location, String health, String dangerStatus){
        this.name = name;
        this.rangerId = rangerId;
        this.age = age;
        this.location = location;
        this.health = health;
        this.dangerStatus = dangerStatus;


    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getRangerId() {
        return rangerId;
    }

    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    public String getDangerStatus() {
        return dangerStatus;
    }
    public Timestamp getTimeSeen() { return timeSeen; }
    public void setTimeSeen(Timestamp timeSeen) { this.timeSeen = timeSeen; }

    public void setFormattedTimeString() {
        this.formattedTimeString = timeSeen();
    }
    public String getFormattedTimeString() {
        return formattedTimeString;
    }

    public int getId() {
        return id;
    }



    public static List<animal> all() {
        String sql = "SELECT name, rangerId, age, location, health, dangerStatus FROM animals";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(animal.class);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash( name, rangerId, age, location, health, dangerStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof animal)) {
            return false;
        } else {
            animal newAnimal = (animal) o;
            return this.getName().equals(newAnimal.getName()) &&
                    this.getRangerId()==newAnimal.getRangerId() &&
                    this.getAge().equals(newAnimal.getAge()) &&
                    this.getLocation().equals(newAnimal.getLocation()) &&
                    this.getHealth().equals(newAnimal.getHealth()) &&
                    this.getDangerStatus().equals(newAnimal.getDangerStatus());

        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, rangerId, age, location, health, dangerStatus,timeSeen ) VALUES (:name, :rangerId, :age, :location, :health, :dangerStatus,now())";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("age", this.age)
                    .addParameter("location", this.location)
                    .addParameter("health", this.health)
                    .addParameter("dangerStatus", this.dangerStatus)
                    .executeUpdate()
                    .getKey();
        }
    }
    public String timeSeen(){
        try(Connection conn = DB.sql2o.open() ) {
            String sql = "SELECT timeseen FROM sightings WHERE id = :id";
            this.timeSeen = conn.createQuery(sql)
                    .addParameter("id",this.id)
                    .executeAndFetchFirst(Timestamp.class);
            return DateFormat.getDateTimeInstance().format(timeSeen);
        }
    }

}
