package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.AMY;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BENSON;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.CARL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.DANIEL;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ELLE;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.FIONA;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.GEORGE;

import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {
    public static final Booking BOOKING_ALICE = new BookingBuilder().forClient(ALICE).build();
    public static final Booking BOOKING_BENSON = new BookingBuilder().forClient(BENSON).build();
    public static final Booking BOOKING_CARL = new BookingBuilder().forClient(CARL).build();
    public static final Booking BOOKING_DANIEL = new BookingBuilder().forClient(DANIEL).build();
    public static final Booking BOOKING_ELLE = new BookingBuilder().forClient(ELLE).build();
    public static final Booking BOOKING_FIONA = new BookingBuilder().forClient(FIONA).build();
    public static final Booking BOOKING_GEORGE = new BookingBuilder().forClient(GEORGE).build();

    // Manually added - Booking's details found in {@code CommandTestUtil}
    public static final Booking BOOKING_AMY = new BookingBuilder().forClient(AMY).build();
    public static final Booking BOOKING_BOB = new BookingBuilder().forClient(BOB).build();
}
