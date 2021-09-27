package ay2122s1_cs2103t_w16_2.btbb.storage;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.BOOKING_FOR_FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.FIONA;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;
import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

class JsonAdaptedBookingTest {
    private static final String INVALID_PHONE = "+651234";

    private static final String VALID_PHONE = FIONA.getPhone().toString();

    private AddressBook addressBook;

    @BeforeEach
    public void setUp() {
        addressBook = new AddressBook();
        addressBook.addClient(FIONA);
    }

    @Test
    public void toModelType_validBookingDetails_returnsBooking() throws Exception {
        JsonAdaptedBooking booking = new JsonAdaptedBooking(BOOKING_FOR_FIONA);
        assertEquals(BOOKING_FOR_FIONA, booking.toModelType(addressBook));
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedBooking booking = new JsonAdaptedBooking(INVALID_PHONE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> booking.toModelType(addressBook));
    }
}
