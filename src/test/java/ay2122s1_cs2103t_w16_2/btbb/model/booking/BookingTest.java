package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.RANDOM_BOOKING_1;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.RANDOM_BOOKING_2;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.RANDOM_BOOKING_3;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.RANDOM_BOOKING_4;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.BookingBuilder;

class BookingTest {
    @Test
    public void isSameBooking() {
        // same object -> returns true
        assertTrue(RANDOM_BOOKING_1.equals(RANDOM_BOOKING_1));

        // null -> returns false
        assertFalse(RANDOM_BOOKING_2.equals(null));

        // different phone -> returns false
        Booking editedRandomBooking = new BookingBuilder(RANDOM_BOOKING_3).withPhone(VALID_PHONE_BOB).build();
        assertFalse(RANDOM_BOOKING_3.equals(editedRandomBooking));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Booking randomBookingCopy = new BookingBuilder(RANDOM_BOOKING_4).build();
        assertTrue(RANDOM_BOOKING_4.equals(randomBookingCopy));

        // same object -> returns true
        assertTrue(RANDOM_BOOKING_1.equals(RANDOM_BOOKING_1));

        // null -> returns false
        assertFalse(RANDOM_BOOKING_1.equals(null));

        // different type -> returns false
        assertFalse(RANDOM_BOOKING_1.equals(5));

        // different booking -> returns false
        assertFalse(RANDOM_BOOKING_1.equals(RANDOM_BOOKING_4));

        // different phone -> returns false
        Booking editedRandomBooking = new BookingBuilder(RANDOM_BOOKING_1).withPhone(VALID_PHONE_BOB).build();
        assertFalse(RANDOM_BOOKING_1.equals(editedRandomBooking));
    }
}
