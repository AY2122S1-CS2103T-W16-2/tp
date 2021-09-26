package ay2122s1_cs2103t_w16_2.btbb.model.client;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import ay2122s1_cs2103t_w16_2.btbb.exception.IllegalValueException;

public class Membership {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public static final String MESSAGE_CONSTRAINTS = "Membership should be of the formats "
            + "start-date<space>offset or start-date<space>end-date"
            + "and adhere to the following constraints:\n"
            + "1. The start-date and end-date must be of the format DD-MM-YYYY.\n"
            + "2. The offset must be in the format <number><unit> where unit must be either d, w, m or y.\n"
            + "Examples of input:\n"
            + "    - 12-01-2020 12-03-2020 (a membership that starts in 12 January 2020 and ends in 12 March 2020)\n"
            + "    - 12-01-2020 6m (a membership that starts in 12 January 2020 and ends 6 months later)\n";

    private static final String PERIOD_VALIDATION_REGEX = "^\\d+[my]$";
    private static final String JSON_VALIDATION_REGEX = "^\\d{2}-\\d{2}-\\d{4}\\s+\\d{2}-\\d{2}-\\d{4}$";

    private static final DateTimeFormatter MEMBERSHIP_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-uuuu").withResolverStyle(ResolverStyle.STRICT);

    public Membership(LocalDate startDate, LocalDate endDate) throws IllegalValueException {
        requireAllNonNull(startDate, endDate);
        if (endDate.isBefore(startDate)) {
            throw new IllegalValueException("Membership end date is earlier than start date.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Membership(String startDateInput, String period) throws IllegalValueException {
        requireAllNonNull(startDateInput, period);

        if (!isValidMembershipDate(startDateInput)) {
            throw new IllegalValueException("Invalid format for membership start date");
        }

        this.startDate = LocalDate.parse(startDateInput, MEMBERSHIP_DATE_FORMATTER);
        this.endDate = calculateEndDate(this.startDate, period);
    }

    public Membership(String jsonInput) throws IllegalValueException {
        requireAllNonNull(jsonInput);

        if (!jsonInput.matches(JSON_VALIDATION_REGEX)) {
            throw new IllegalValueException("Invalid JSON input for membership");
        }

        String [] args = jsonInput.split("\\s+");
        if (!isValidMembershipDate(args[0])) {
            throw new IllegalValueException("Invalid start date in membership json");
        }
        if (!isValidMembershipDate(args[1])) {
            throw new IllegalValueException("Invalid end date in membership json");
        }

        LocalDate startDate = LocalDate.parse(args[0], MEMBERSHIP_DATE_FORMATTER);
        LocalDate endDate = LocalDate.parse(args[1], MEMBERSHIP_DATE_FORMATTER);
        if (endDate.isBefore(startDate)) {
            throw new IllegalValueException("Membership end date is earlier than start date in json.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    private static LocalDate calculateEndDate(LocalDate startDate, String period) throws IllegalValueException {
        requireAllNonNull(startDate, period);
        if (!isValidMembershipPeriod(period)) {
            throw new IllegalValueException("Period is in invalid format.");
        }
        try {
            long value = Long.parseLong(period.substring(0, period.length() - 1));
            String unit = period.substring(period.length() - 1);
            switch (unit) {
            case "m":
                return startDate.plusMonths(value);
            case "y":
                return startDate.plusYears(value);
            default:
                throw new IllegalValueException("Unit of period is invalid");
            }
        } catch (NumberFormatException e) {
            throw new IllegalValueException("Unable to parse number in period");
        }
    }

    public static boolean isValidMembershipDate(String dateInput) {
        requireAllNonNull(dateInput);
        try {
            LocalDate.parse(dateInput, MEMBERSHIP_DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidMembershipPeriod(String period) {
        requireAllNonNull(period);
        return period.matches(PERIOD_VALIDATION_REGEX);
    }

    public String toString() {
        return startDate.format(MEMBERSHIP_DATE_FORMATTER) + " " + endDate.format(MEMBERSHIP_DATE_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof  Membership)) {
            return false;
        }
        Membership otherMembership = (Membership) other;
        return this.startDate.equals(otherMembership.startDate) && this.endDate.equals(otherMembership.endDate);
    }
}
