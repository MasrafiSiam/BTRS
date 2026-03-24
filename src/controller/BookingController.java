package controller;

import dao.BookingDAO;

public class BookingController {

    BookingDAO dao = new BookingDAO();

    public boolean book(int userId, int busId, int seat) {
        return dao.book(userId, busId, seat);
    }
}