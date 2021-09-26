package ay2122s1_cs2103t_w16_2.btbb.logic.commands.booking;

import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.BookingDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.Model;
import ay2122s1_cs2103t_w16_2.btbb.model.ModelManager;
import ay2122s1_cs2103t_w16_2.btbb.model.UserPrefs;
import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.testutil.BookingBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.BookingDescriptorBuilder;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddBookingCommand}.
 */
public class AddBookingCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newBooking_success() {
        Client validClient = new ClientBuilder().build();
        Booking validBooking = new BookingBuilder().forClient(validClient).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        // A booking cannot be added without the model containing the associated Client.
        model.addClient(validClient);
        expectedModel.addClient(validClient);
        expectedModel.addBooking(validBooking);
        BookingDescriptor validBookingDescriptor = new BookingDescriptorBuilder(validBooking).build();

        assertCommandSuccess(new AddBookingCommand(validBookingDescriptor), model,
                String.format(AddBookingCommand.MESSAGE_SUCCESS, validBooking), expectedModel);
    }
}
