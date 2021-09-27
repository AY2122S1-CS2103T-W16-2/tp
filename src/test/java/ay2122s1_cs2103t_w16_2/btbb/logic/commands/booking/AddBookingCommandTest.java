package ay2122s1_cs2103t_w16_2.btbb.logic.commands.booking;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.Assert.assertThrows;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.ALICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandResult;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.BookingDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.testutil.BookingBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.BookingDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.stubs.ModelStubAcceptingBookingAdded;

class AddBookingCommandTest {
    @Test
    public void constructor_nullBooking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBookingCommand(null));
    }

    @Test
    public void execute_bookingAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBookingAdded modelStub = new ModelStubAcceptingBookingAdded();
        Booking validBooking = new BookingBuilder().forClient(ALICE).build();
        BookingDescriptor validBookingDescriptor = new BookingDescriptorBuilder(validBooking).build();

        // A booking can only be added if the client already exists.
        modelStub.addClient(ALICE);
        CommandResult commandResult = new AddBookingCommand(validBookingDescriptor).execute(modelStub);

        assertEquals(String.format(AddBookingCommand.MESSAGE_SUCCESS, validBooking), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBooking), modelStub.getBookingsAdded());
    }

    @Test
    public void equals() {
        BookingDescriptor bookingDescriptorForAmy = new BookingDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        BookingDescriptor bookingDescriptorForBob = new BookingDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        AddBookingCommand addBookingForAmyCommand = new AddBookingCommand(bookingDescriptorForAmy);
        AddBookingCommand addBookingForBobCommand = new AddBookingCommand(bookingDescriptorForBob);

        // same object -> returns true
        assertTrue(addBookingForAmyCommand.equals(addBookingForAmyCommand));

        // same values -> returns true
        AddBookingCommand addBookingForAmyCommandCopy = new AddBookingCommand(bookingDescriptorForAmy);
        assertTrue(addBookingForAmyCommand.equals(addBookingForAmyCommandCopy));

        // different types -> returns false
        assertFalse(addBookingForAmyCommand.equals(1));

        // null -> returns false
        assertFalse(addBookingForAmyCommand.equals(null));

        // different client -> returns false
        assertFalse(addBookingForAmyCommand.equals(addBookingForBobCommand));
    }
}
