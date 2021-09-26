package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;

/**
 * A utility class to help with building Booking objects.
 */
public class BookingBuilder {
    private Client client;

    /**
     * Creates a {@code BookingBuilder} with the default details.
     */
    public BookingBuilder() {
        client = new ClientBuilder().build();
    }

    /**
     * Initializes the BookingBuilder with the data of {@code bookingToCopy}.
     *
     * @param bookingToCopy The data from which to copy from to create a new booking.
     */
    public BookingBuilder(Booking bookingToCopy) {
        client = bookingToCopy.getClient();
    }

    /**
     * Sets the {@code client} of the {@code BookingBuilder} that we are building.
     *
     * @param client The client associated with the booking we are building.
     * @return The {@code BookingBuilder} object.
     */
    public BookingBuilder forClient(Client client) {
        this.client = client;
        return this;
    }

    public Booking build() {
        return new Booking(client);
    }
}
