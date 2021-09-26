package ay2122s1_cs2103t_w16_2.btbb.model.client;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;

public class MembershipTest {
    @Test
    public void isValidMembershipDate() {
        assertTrue(Membership.isValidMembershipDate("12-12-2021"));
        assertFalse(Membership.isValidMembershipDate("29-02-2021"));
        assertFalse(Membership.isValidMembershipDate("2021-12-12"));
    }

    @Test
    public void isValidMembershipPeriod() {
        assertTrue(Membership.isValidMembershipPeriod("31m"));
        assertTrue(Membership.isValidMembershipPeriod("31y"));
        assertFalse(Membership.isValidMembershipPeriod("31"));
    }

    @Test
    public void newMembership_correctUserInput() {
        try {
            Membership sixMonthMembership = new Membership("01-01-2021", "6m");
            assertEquals(LocalDate.parse("2021-01-01"), sixMonthMembership.getStartDate());
            assertEquals(LocalDate.parse("2021-07-01"), sixMonthMembership.getEndDate());
            Membership oneYearMembership = new Membership("01-01-2021", "1y");
            assertEquals(LocalDate.parse("2021-01-01"), oneYearMembership.getStartDate());
            assertEquals(LocalDate.parse("2022-01-01"), oneYearMembership.getEndDate());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newMembership_wrongUserInput_exceptionThrown() {
        assertThrows(
                IllegalValueException.class, "Invalid format for membership start date",
                () -> new Membership("29-02-2021", "6m")
        );
        assertThrows(
                IllegalValueException.class, "Period is in invalid format.",
                () -> new Membership("29-01-2021", "6")
        );
    }

    @Test
    public void newMembership_correctJsonInput() {
        try {
            Membership oneYearMembership = new Membership("01-01-2021 01-01-2022");
            assertEquals(LocalDate.parse("2021-01-01"), oneYearMembership.getStartDate());
            assertEquals(LocalDate.parse("2022-01-01"), oneYearMembership.getEndDate());
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void newMembership_wrongJsonInput_exceptionThrown() {
        assertThrows(
                IllegalValueException.class, "Invalid JSON input for membership",
                () -> new Membership("2021-02-21 2021-02-22")
        );
        assertThrows(
                IllegalValueException.class, "Invalid start date in membership json",
                () -> new Membership("29-02-2021 01-01-2022")
        );
        assertThrows(
                IllegalValueException.class, "Invalid end date in membership json",
                () -> new Membership("29-01-2021 29-02-2021")
        );
        assertThrows(
                IllegalValueException.class, "Membership end date is earlier than start date in json.",
                () -> new Membership("29-01-2022 29-01-2021")
        );
    }

    @Test
    public void membershipToString() {
        try {
            assertEquals(
                    "01-01-2021 01-01-2022",
                    new Membership("01-01-2021", "1y").toString()
            );
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void membershipEquals() {
        try {
            assertEquals(
                    new Membership("01-01-2021 01-01-2022"),
                    new Membership("01-01-2021", "1y")
            );
            assertNotEquals(
                    new Membership("01-01-2021 01-01-2022"),
                    new Membership("01-01-2021", "1m")
            );
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }
}
