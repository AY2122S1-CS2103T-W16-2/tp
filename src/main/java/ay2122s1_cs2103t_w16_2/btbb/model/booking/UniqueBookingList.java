package ay2122s1_cs2103t_w16_2.btbb.model.booking;

import static ay2122s1_cs2103t_w16_2.btbb.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import ay2122s1_cs2103t_w16_2.btbb.exception.NotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UniqueBookingList implements Iterable<Booking> {
    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Adds a booking to the list.
     *
     * @param toAdd Booking to be added to the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Returns true if the list contains an equivalent booking as the given argument.
     *
     * @param toCheck The booking which is used to check.
     * @return True if the list contains an equivalent booking as the given argument. False otherwise.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Replaces the booking {@code target} in the list with {@code editedBooking}.
     * {@code target} must exist in the list.
     * The booking identity of {@code editedBooking} must not be the same as another existing booking in the list.
     *
     * @param target The target to change.
     * @param editedBooking The replacement for target.
     * @throws NotFoundException If the target cannot be found in the list.
     */
    public void setBooking(Booking target, Booking editedBooking) throws NotFoundException {
        requireAllNonNull(target, editedBooking);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NotFoundException(Booking.class.getName());
        }

        internalList.set(index, editedBooking);
    }

    /**
     * Removes the equivalent booking from the list.
     * The booking must exist in the list.
     *
     * @param toRemove The booking that will be used to determine what booking should be removed.
     * @throws NotFoundException If no matching booking can be found.
     */
    public void remove(Booking toRemove) throws NotFoundException {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NotFoundException(Booking.class.getName());
        }
    }

    /**
     * Replaces the contents of this list with {@code bookings}.
     *
     * @param bookings Bookings of the list.
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        internalList.setAll(bookings);
    }

    /**
     * Returns the backing list as an unmodifiable Observable List.
     *
     * @return The original booking list.
     */
    public ObservableList<Booking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookingList // instanceof handles nulls
                && internalList.equals(((UniqueBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
