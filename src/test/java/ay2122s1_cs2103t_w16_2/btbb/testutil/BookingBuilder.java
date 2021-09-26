package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

/**
 * A utility class to help with building Booking objects.
 */
public class BookingBuilder {
    private Phone phone;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        phone = new ClientBuilder().build().getPhone();
    }

    /**
     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
     */
    public BookingBuilder(Booking bookingToCopy) {
        phone = bookingToCopy.getPhone();
    }

    /**
     * Sets the {@code Phone} of the {@code Booking} that we are building.
     */
    public BookingBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    public Booking build() {
        return new Booking(new ClientBuilder().withPhone(phone.toString()).build());
    }
}
