package ay2122s1_cs2103t_w16_2.btbb.model.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.AppUtil.checkArgument;
import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a Client's membership.
 * Guarantees: immutable; is valid as declared in {@link #isValidMembershipJson(String)}
 */
public class Membership {
    public static final String MESSAGE_CONSTRAINTS = "Membership should be of the format "
            + "m/start-date pe/period "
            + "and adhere to the following constraints:\n"
            + "1. The start-date must be of the format DD-MM-YYYY.\n"
            + "2. The period must be in the format <number><unit> where unit must be either m or y.\n"
            + "Examples of input:\n"
            + "    - m/12-01-2020 pe/6m (a membership that starts in 12 January 2020 and ends 6 months later)\n";

    private static final String JSON_VALIDATION_REGEX = "^\\d{2}-\\d{2}-\\d{4}\\s+\\d{2}-\\d{2}-\\d{4}$";
    private static final String PERIOD_VALIDATION_REGEX = "^\\d+[my]$";

    private static final DateTimeFormatter MEMBERSHIP_INPUT_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter MEMBERSHIP_UI_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs a Membership instance with a start date and the end date.
     *
     * @param startDate The start date of the membership.
     * @param endDate The end date of the membership.
     */
    public Membership(LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(startDate, endDate);
        checkArgument(endDate.isAfter(startDate), "Membership end date is earlier than start date");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs a Membership instance with a start date parsed from the provided string start date and the end date
     * calculated using the provided period.
     *
     * @param startDateInput A string representation of the start date in DD-MM-YYYY format.
     * @param period The period of the membership which is used to calculate the end date.
     */
    public Membership(String startDateInput, String period) {
        requireAllNonNull(startDateInput, period);
        checkArgument(isValidMembershipDate(startDateInput), "Invalid format for membership start date");
        this.startDate = LocalDate.parse(startDateInput, MEMBERSHIP_INPUT_DATE_FORMATTER);
        this.endDate = calculateEndDate(this.startDate, period);
    }

    /**
     * Constructs a Membership instance from its JSON representation. The JSON representation
     * of the membership is in the following format: "startDate endDate".
     *
     * @param json The json representation of the membership.
     */
    public Membership(String json) {
        requireAllNonNull(json);
        checkArgument(isValidMembershipJson(json), "Invalid JSON input for membership");

        String [] dates = json.split("\\s+");
        LocalDate startDate = LocalDate.parse(dates[0], MEMBERSHIP_INPUT_DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(dates[1], MEMBERSHIP_INPUT_DATE_FORMATTER);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the start date of this membership.
     *
     * @return the start date of this membership.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of this membership.
     *
     * @return the start date of this membership.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    private static LocalDate calculateEndDate(LocalDate startDate, String period) {
        requireAllNonNull(startDate, period);
        checkArgument(isValidMembershipPeriod(period), "Period is in invalid format.");

        try {
            long value = Long.parseLong(period.substring(0, period.length() - 1));
            String unit = period.substring(period.length() - 1);
            switch (unit) {
            case "m":
                return startDate.plusMonths(value);
            case "y":
                return startDate.plusYears(value);
            default:
                throw new IllegalArgumentException("Unit of period is invalid");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unable to parse number in period");
        }
    }

    /**
     * Returns true if the provided string representation of a date follows the date format, DD-MM-YYYY,
     * and is a valid date.
     *
     * @param dateInput The string representation of the date to check.
     *
     * @return true if the provided string representation of a date matches the date format and is a valid date.
     */
    public static boolean isValidMembershipDate(String dateInput) {
        requireAllNonNull(dateInput);
        try {
            LocalDate.parse(dateInput, MEMBERSHIP_INPUT_DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the provided period matches the format number followed by unit which is either "m" or "y".
     *
     * @param period The period to check.
     *
     * @return true if the provided period matches the format.
     */
    public static boolean isValidMembershipPeriod(String period) {
        requireAllNonNull(period);
        return period.matches(PERIOD_VALIDATION_REGEX);
    }

    /**
     * Returns true if the provided JSON representation of a membership matches the
     * format: "start_date end_date". Also checks if end date is after start date.
     *
     * @param json The json representation of the membership to check
     *
     * @return true if the provided JSON matches the format.
     */
    public static boolean isValidMembershipJson(String json) {
        requireAllNonNull(json);
        if (!json.matches(JSON_VALIDATION_REGEX)) {
            return false;
        }
        String [] dates = json.split("\\s+");
        try {
            LocalDate startDate = LocalDate.parse(dates[0], MEMBERSHIP_INPUT_DATE_FORMATTER);
            LocalDate endDate = LocalDate.parse(dates[1], MEMBERSHIP_INPUT_DATE_FORMATTER);
            return endDate.isAfter(startDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if this membership has expired by comparing with the current date.
     *
     * @return true if this membership has expired.
     */
    public boolean isExpired() {
        return !endDate.isAfter(LocalDate.now());
    }

    /**
     * Returns a String representation of the start date in input format: DD-MM-YYYY.
     *
     * @return a String representation of the start date in input format.
     */
    public String getStartDateInInputFormat() {
        return startDate.format(MEMBERSHIP_INPUT_DATE_FORMATTER);
    }

    /**
     * Returns a String representation of the start date in UI format: DD/MM/YYYY.
     *
     * @return a String representation of the start date in UI format.
     */
    public String getStartDateInUiFormat() {
        return startDate.format(MEMBERSHIP_UI_DATE_FORMATTER);
    }

    /**
     * Returns a String representation of the end date in input format: DD-MM-YYYY.
     *
     * @return a String representation of the end date in input format.
     */
    public String getEndDateInInputFormat() {
        return endDate.format(MEMBERSHIP_INPUT_DATE_FORMATTER);
    }

    /**
     * Returns a String representation of the end date in UI format: DD/MM/YYYY.
     *
     * @return a String representation of the end date in UI format.
     */
    public String getEndDateInUiFormat() {
        return endDate.format(MEMBERSHIP_UI_DATE_FORMATTER);
    }

    /**
     * Returns the period of this membership.
     *
     * @return the period of this membership.
     */
    public String getPeriod() {
        Period period = Period.between(startDate, endDate);
        return period.getMonths() != 0 ? period.getMonths() + "m" : period.getYears() + "y";
    }

    /**
     * Returns the JSON representation of this membership as a String.
     *
     * @return the JSON representation of this membership.
     */
    @Override
    public String toString() {
        return startDate.format(MEMBERSHIP_INPUT_DATE_FORMATTER)
                + " " + endDate.format(MEMBERSHIP_INPUT_DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Membership)) {
            return false;
        }
        Membership otherMembership = (Membership) other;
        return this.startDate.equals(otherMembership.startDate) && this.endDate.equals(otherMembership.endDate);
    }
}
