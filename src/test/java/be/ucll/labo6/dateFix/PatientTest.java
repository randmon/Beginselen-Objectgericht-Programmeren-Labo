package be.ucll.labo6.dateFix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class PatientTest {
    private Patient p;

    @Before
    public void setUp() {
        p = new Patient("30-12-1999");
    }

    @Test (expected = IllegalArgumentException.class)
    public void futureDate() {
        p.setBirthdate("10-10-3000");
    }

    @Test (expected = DateTimeParseException.class)
    public void wrongDate() {
        p.setBirthdate("34-10-2000");
    }

    @Test (expected = DateTimeParseException.class)
    public void wrongFormat() {
        p.setBirthdate("abcdefgh");
    }

    @Test
    public void correctDate() {
        p.setBirthdate("30-12-1999");
        Assert.assertEquals(p.getBirthdateString(), "30-12-1999");
    }
}