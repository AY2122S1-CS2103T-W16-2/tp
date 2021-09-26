package ay2122s1_cs2103t_w16_2.btbb.testutil;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;

public class TypicalBookings {
    // Manually added - Booking's details found in {@code CommandTestUtil}
    public static final Booking BOOKING_AMY = new BookingBuilder().withPhone(VALID_PHONE_AMY).build();
    public static final Booking BOOKING_BOB = new BookingBuilder().withPhone(VALID_PHONE_BOB).build();
}
