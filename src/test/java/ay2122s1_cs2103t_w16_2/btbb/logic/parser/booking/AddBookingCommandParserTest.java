package ay2122s1_cs2103t_w16_2.btbb.logic.parser.booking;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalBookings.BOOKING_FOR_BOB;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.booking.AddBookingCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.BookingDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.testutil.BookingDescriptorBuilder;

class AddBookingCommandParserTest {
    private AddBookingCommandParser parser = new AddBookingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        BookingDescriptor expectedBookingDescriptor = new BookingDescriptorBuilder(BOOKING_FOR_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PHONE_DESC_BOB,
                new AddBookingCommand(expectedBookingDescriptor));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, PHONE_DESC_AMY + PHONE_DESC_BOB,
                new AddBookingCommand(expectedBookingDescriptor));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE);

        // missing phone prefix
        assertParseFailure(parser, VALID_PHONE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_PHONE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PHONE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBookingCommand.MESSAGE_USAGE));
    }
}
