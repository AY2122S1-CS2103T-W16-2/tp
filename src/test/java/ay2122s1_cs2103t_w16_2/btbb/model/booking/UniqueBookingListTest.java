package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.RANDOM_BOOKING_1;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.RANDOM_BOOKING_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;

class UniqueBookingListTest {
    private final UniqueBookingList uniqueBookingList = new UniqueBookingList();

    @Test
    public void contains_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.contains(null));
    }

    @Test
    public void contains_bookingNotInList_returnsFalse() {
        assertFalse(uniqueBookingList.contains(RANDOM_BOOKING_1));
    }

    @Test
    public void contains_bookingInList_returnsTrue() {
        uniqueBookingList.add(RANDOM_BOOKING_1);
        assertTrue(uniqueBookingList.contains(RANDOM_BOOKING_1));
    }

    @Test
    public void add_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.add(null));
    }

    @Test
    public void setBooking_nullTargetBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(null, RANDOM_BOOKING_1));
    }

    @Test
    public void setBooking_nullEditedBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(RANDOM_BOOKING_1, null));
    }

    @Test
    public void setBooking_targetBookingNotInList_throwsBookingNotFoundException() {
        assertThrows(NotFoundException.class, () -> uniqueBookingList.setBooking(RANDOM_BOOKING_1, RANDOM_BOOKING_1));
    }

    @Test
    public void setBooking_editedBookingIsSameBooking_success() throws NotFoundException {
        uniqueBookingList.add(RANDOM_BOOKING_1);
        uniqueBookingList.setBooking(RANDOM_BOOKING_1, RANDOM_BOOKING_1);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(RANDOM_BOOKING_1);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setBooking_editedBookingHasDifferentIdentity_success() throws NotFoundException {
        uniqueBookingList.add(RANDOM_BOOKING_1);
        uniqueBookingList.setBooking(RANDOM_BOOKING_1, RANDOM_BOOKING_2);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(RANDOM_BOOKING_2);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void remove_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.remove(null));
    }
}
