package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.BookingDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

/**
 * A utility class to help with building BookingDescriptorBuilder objects.
 */
public class BookingDescriptorBuilder {
    private BookingDescriptor descriptor;

    /**
     * Creates an empty {@code BookingDescriptorBuilder}.
     */
    public BookingDescriptorBuilder() {
        descriptor = new BookingDescriptor();
    }

    /**
     * Initializes the BookingDescriptorBuilder with the data of {@code descriptor}.
     *
     * @param descriptor The data from which to copy from to create a new booking descriptor.
     */
    public BookingDescriptorBuilder(BookingDescriptor descriptor) {
        this.descriptor = new BookingDescriptor(descriptor);
    }

    /**
     * Returns a {@code BookingDescriptor} with fields containing {@code booking}'s details
     *
     * @param booking The booking from which to copy from to create a new booking descriptor.
     */
    public BookingDescriptorBuilder(Booking booking) {
        descriptor = new BookingDescriptor();
        descriptor.setPhone(booking.getPhone());
    }

    /**
     * Sets the {@code Phone} of the {@code BookingDescriptor} that we are building.
     *
     * @param phone The phone that should be set.
     * @return A BookingDescriptorBuilder object that contains the new phone details.
     */
    public BookingDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    public BookingDescriptor build() {
        return descriptor;
    }
}
