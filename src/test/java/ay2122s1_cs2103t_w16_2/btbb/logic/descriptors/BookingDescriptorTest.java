package ay2122s1_cs2103t_w16_2.btbb.logic.descriptors;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BOOKING_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.DESC_BOOKING_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.testutil.BookingDescriptorBuilder;

class BookingDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        BookingDescriptor descriptorWithSameValues = new BookingDescriptor(DESC_BOOKING_AMY);
        assertTrue(DESC_BOOKING_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_BOOKING_AMY.equals(DESC_BOOKING_AMY));

        // null -> returns false
        assertFalse(DESC_BOOKING_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_BOOKING_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_BOOKING_AMY.equals(DESC_BOOKING_BOB));

        // different phone -> returns false
        BookingDescriptor editedBooking = new BookingDescriptorBuilder(DESC_BOOKING_AMY)
                .withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_BOOKING_AMY.equals(editedBooking));
    }
}
