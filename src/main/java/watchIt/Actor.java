package watchIt;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Actor extends Cast implements Serializable {

    public Actor() {
    }

    public Actor(String firstName, String lastName, LocalDate dateOfBirth, enGender gender, String nationality) {
        super(firstName, lastName, dateOfBirth, gender, nationality);
    }

}