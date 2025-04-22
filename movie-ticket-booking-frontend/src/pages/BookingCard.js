import React, { useRef } from "react";
import jsPDF from "jspdf";
import html2canvas from "html2canvas";
import "./BookingCard.css";

const BookingCard = ({ booking }) => {
    const cardRef = useRef();

   
   

    return (
        <div className="booking-card" ref={cardRef}>
            <div className="booking-card-header">
                <h3>🎟️ {booking.movie.title}</h3>
                <span className="booking-status">{booking.bookingStatus}</span>
            </div>
            <p><strong>Theater:</strong> {booking.theater.name} - {booking.theater.location}</p>
            <p><strong>Show Date:</strong> {booking.showDate}</p>
            <p><strong>Show Time:</strong> {booking.showTime}</p>
            <p><strong>Booked Seats:</strong> {booking.bookedSeats.join(", ")}</p>
            <p><strong>Booking Date:</strong> {booking.bookingTime}</p>
          
        </div>
    );
};

export default BookingCard;
