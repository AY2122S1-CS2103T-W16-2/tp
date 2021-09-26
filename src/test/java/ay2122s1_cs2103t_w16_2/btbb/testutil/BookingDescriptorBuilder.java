package ay2122s1_cs2103t_w16_2.btbb.testutil;

import ay2122s1_cs2103t_w16_2.btbb.logic.descriptors.BookingDescriptor;
import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

/**
 * A utility class to help with building BookingDescriptorBuilder objects.
 */
public class BookingDescriptorBuilder {
    private BookingDescriptor descriptor;

    public BookingDescriptorBuilder() {
        descriptor = new BookingDescriptor();
    }

    public BookingDescriptorBuilder(BookingDescriptor descriptor) {
        this.descriptor = new BookingDescriptor(descriptor);
    }

    /**
     * Returns a {@code BookingDescriptor} with fields containing {@code booking}'s details
     */
    public BookingDescriptorBuilder(Booking booking) {
        descriptor = new BookingDescriptor();
        descriptor.setPhone(booking.getPhone());
    }

    /**
     * Sets the {@code Phone} of the {@code BookingDescriptor} that we are building.
     */
    public BookingDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    public BookingDescriptor build() {
        return descriptor;
    }
}
