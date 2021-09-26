package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.BOOKING_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.BOOKING_DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.FIONA;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.BookingBuilder;

class BookingTest {
    @Test
    public void equals() {
        // same values -> returns true
        Booking randomBookingCopy = new BookingBuilder(BOOKING_DANIEL).build();
        assertTrue(BOOKING_DANIEL.equals(randomBookingCopy));

        // same object -> returns true
        assertTrue(BOOKING_ALICE.equals(BOOKING_ALICE));

        // null -> returns false
        assertFalse(BOOKING_ALICE.equals(null));

        // different type -> returns false
        assertFalse(BOOKING_ALICE.equals(5));

        // different booking -> returns false
        assertFalse(BOOKING_ALICE.equals(BOOKING_DANIEL));

        // different client -> returns false
        Booking editedRandomBooking = new BookingBuilder(BOOKING_ALICE).forClient(FIONA).build();
        assertFalse(BOOKING_ALICE.equals(editedRandomBooking));
    }
}
