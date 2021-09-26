package ay2122s1_cs2103t_w16_2.btbb.testutil.stubs;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Optional;

import ay2122s1_cs2103t_w16_2.btbb.model.AddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.ReadOnlyAddressBook;
import ay2122s1_cs2103t_w16_2.btbb.model.booking.Booking;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Client;
import ay2122s1_cs2103t_w16_2.btbb.model.client.Phone;

/**
 * A Model stub that always accepts the booking being added if it already contains the client that the booking
 * is associated with.
 */
public class ModelStubAcceptingBookingAdded extends ModelStub {
    private final ArrayList<Client> clientsAdded = new ArrayList<>();
    private final ArrayList<Booking> bookingsAdded = new ArrayList<>();

    public ArrayList<Booking> getBookingsAdded() {
        return bookingsAdded;
    }

    @Override
    public void addClient(Client client) {
        requireNonNull(client);
        clientsAdded.add(client);
    }

    @Override
    public void addBooking(Booking booking) {
        requireNonNull(booking);
        bookingsAdded.add(booking);
    }

    @Override
    public Optional<Client> getClientByPhone(Phone phone) {
        return clientsAdded.stream().filter(client -> client.getPhone().equals(phone)).findFirst();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return new AddressBook();
    }
}
