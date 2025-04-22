import React, { useEffect, useState } from "react";
import axios from "axios";
import BookingCard from "../pages/BookingCard";
import UserNavBar from "./UserNAvBar";

const BookingInfo = () => {
    const [bookings, setBookings] = useState([]);
    const [loading, setLoading] = useState(true);

    const userId = localStorage.getItem("userId");

    useEffect(() => {
        if (!userId) return;

        const fetchBookings = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/bookings/mybookings?userId=${userId}`);
                setBookings(response.data);
            } catch (error) {
                console.error("❌ Failed to fetch bookings:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchBookings();
    }, [userId]);

    if (loading) return <p>Loading your bookings...</p>;

    return (
        <div>
             <UserNavBar />
       
        
        <div style={{ padding: "20px" }}>
           
            <h2 style={{ textAlign: "center" }}>🎫 My Booking Info</h2>
            {bookings.length === 0 ? (
                <p>No bookings found.</p>
            ) : (
                bookings.map((booking) => (
                    <BookingCard key={booking.id} booking={booking} />
                ))
            )}
        </div>
        </div>
    );
};

export default BookingInfo;
