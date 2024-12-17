package watchIt;

import java.io.Serializable;
import java.time.LocalDate;

public class Director extends Cast implements Serializable {

    public Director(String firstName, String lastName, LocalDate dateOfBirth, enGender gender, String nationality) {
        super(firstName, lastName, dateOfBirth, gender, nationality);
    }
}