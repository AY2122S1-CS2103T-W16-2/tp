package ay2122s1_cs2103t_w16_2.btbb.logic.parser;

import static ay2122s1_cs2103t_w16_2.btbb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_MEMBERSHIP_PERIOD_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_MEMBERSHIP_START_DATE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.MEMBERSHIP_PERIOD_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.MEMBERSHIP_PERIOD_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.MEMBERSHIP_START_DATE_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.MEMBERSHIP_START_DATE_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_MEMBERSHIP_PERIOD_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_MEMBERSHIP_START_DATE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseFailure;
import static ay2122s1_cs2103t_w16_2.btbb.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static ay2122s1_cs2103t_w16_2.btbb.testutil.TypicalClients.BOB;

import org.junit.jupiter.api.Test;

import ay2122s1_cs2103t_w16_2.btbb.logic.commands.client.AddClientCommand;
import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.ClientDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.logic.parser.client.AddClientCommandParser;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Address;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Email;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Membership;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Name;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;
import ay2122s1_cs2103t_w16_2.btbb.testutil.ClientDescriptorBuilder;

public class AddClientCommandParserTest {
    private AddClientCommandParser parser = new AddClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ClientDescriptor expectedClientDescriptor = new ClientDescriptorBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(
                parser,
                PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                new AddClientCommand(expectedClientDescriptor)
        );

        // multiple names - last name accepted
        assertParseSuccess(
                parser,
                NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                new AddClientCommand(expectedClientDescriptor)
        );

        // multiple phones - last phone accepted
        assertParseSuccess(
                parser,
                NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                new AddClientCommand(expectedClientDescriptor)
        );

        // multiple emails - last email accepted
        assertParseSuccess(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                new AddClientCommand(expectedClientDescriptor)
        );

        // multiple addresses - last address accepted
        assertParseSuccess(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                new AddClientCommand(expectedClientDescriptor)
        );

        // multiple membership start date - last date accepted
        assertParseSuccess(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + MEMBERSHIP_START_DATE_DESC_AMY + MEMBERSHIP_START_DATE_DESC_BOB
                        + MEMBERSHIP_PERIOD_DESC_BOB,
                new AddClientCommand(expectedClientDescriptor)
        );

        // multiple membership period - last period accepted
        assertParseSuccess(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                        + ADDRESS_DESC_BOB + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_AMY
                        + MEMBERSHIP_PERIOD_DESC_BOB,
                new AddClientCommand(expectedClientDescriptor)
        );
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(
                parser,
                VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                expectedMessage
        );

        // missing phone prefix
        assertParseFailure(
                parser,
                NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                expectedMessage
        );

        // missing email prefix
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                expectedMessage
        );

        // missing address prefix
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                expectedMessage
        );

        // missing membership start date prefix
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + VALID_MEMBERSHIP_START_DATE_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                expectedMessage
        );

        // missing membership period prefix
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + VALID_MEMBERSHIP_PERIOD_BOB,
                expectedMessage
        );

        // all prefixes missing
        assertParseFailure(
                parser,
                VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                        + VALID_MEMBERSHIP_START_DATE_BOB + VALID_MEMBERSHIP_PERIOD_BOB,
                expectedMessage
        );
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(
                parser,
                INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS
        );

        // invalid phone
        assertParseFailure(
                parser,
                NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS
        );

        // invalid email
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS
        );

        // invalid address
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS
        );

        // invalid membership start date
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVALID_MEMBERSHIP_START_DATE_DESC + MEMBERSHIP_PERIOD_DESC_BOB,
                Membership.MESSAGE_CONSTRAINTS
        );

        // invalid membership period
        assertParseFailure(
                parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + INVALID_MEMBERSHIP_PERIOD_DESC,
                Membership.MESSAGE_CONSTRAINTS
        );

        // two invalid values, only first invalid value reported
        assertParseFailure(
                parser,
                INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS
        );

        // non-empty preamble
        assertParseFailure(
                parser,
                PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + MEMBERSHIP_START_DATE_DESC_BOB + MEMBERSHIP_PERIOD_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE)
        );
    }
}
