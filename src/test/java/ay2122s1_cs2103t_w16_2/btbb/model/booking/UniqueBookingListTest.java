package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.BOOKING_ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.BOOKING_BENSON;
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
        assertFalse(uniqueBookingList.contains(BOOKING_ALICE));
    }

    @Test
    public void contains_bookingInList_returnsTrue() {
        uniqueBookingList.add(BOOKING_ALICE);
        assertTrue(uniqueBookingList.contains(BOOKING_ALICE));
    }

    @Test
    public void add_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.add(null));
    }

    @Test
    public void setBooking_nullTargetBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(null, BOOKING_ALICE));
    }

    @Test
    public void setBooking_nullEditedBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.setBooking(BOOKING_ALICE, null));
    }

    @Test
    public void setBooking_targetBookingNotInList_throwsBookingNotFoundException() {
        assertThrows(NotFoundException.class, () -> uniqueBookingList.setBooking(BOOKING_ALICE, BOOKING_ALICE));
    }

    @Test
    public void setBooking_editedBookingIsSameBooking_success() throws NotFoundException {
        uniqueBookingList.add(BOOKING_ALICE);
        uniqueBookingList.setBooking(BOOKING_ALICE, BOOKING_ALICE);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOOKING_ALICE);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void setBooking_editedBookingHasDifferentIdentity_success() throws NotFoundException {
        uniqueBookingList.add(BOOKING_ALICE);
        uniqueBookingList.setBooking(BOOKING_ALICE, BOOKING_BENSON);
        UniqueBookingList expectedUniqueBookingList = new UniqueBookingList();
        expectedUniqueBookingList.add(BOOKING_BENSON);
        assertEquals(expectedUniqueBookingList, uniqueBookingList);
    }

    @Test
    public void remove_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBookingList.remove(null));
    }
}
