package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;

/**
 * A utility class containing a list of {@code Booking} objects to be used in tests.
 */
public class TypicalBookings {
    public static final Booking RANDOM_BOOKING_1 = new BookingBuilder().withPhone("98562145").build();
    public static final Booking RANDOM_BOOKING_2 = new BookingBuilder().withPhone("45625411").build();
    public static final Booking RANDOM_BOOKING_3 = new BookingBuilder().withPhone("57854632").build();
    public static final Booking RANDOM_BOOKING_4 = new BookingBuilder().withPhone("12345678").build();
    public static final Booking RANDOM_BOOKING_5 = new BookingBuilder().withPhone("10214268").build();
    public static final Booking RANDOM_BOOKING_6 = new BookingBuilder().withPhone("99999999").build();

    // Manually added - Booking's details found in {@code CommandTestUtil}
    public static final Booking BOOKING_AMY = new BookingBuilder().withPhone(VALID_PHONE_AMY).build();
    public static final Booking BOOKING_BOB = new BookingBuilder().withPhone(VALID_PHONE_BOB).build();
}
