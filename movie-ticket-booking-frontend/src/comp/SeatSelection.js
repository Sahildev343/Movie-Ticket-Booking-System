import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";
import "./SeatSelection.css";
import { toast, ToastContainer } from "react-toastify";

const SeatSelection = ({ onBookingSuccess }) => {
    const navigate = useNavigate();
    const { showTimeId } = useParams();
    const [bookedSeats, setBookedSeats] = useState([]);
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [loading, setLoading] = useState(true);
    const [showTimeDetails, setShowTimeDetails] = useState(null);
    

    const userId = localStorage.getItem("userId");
    const movieId = localStorage.getItem("movieId"); // ✅ Fetch movieId from localStorage

    useEffect(() => {
        if (!showTimeId) {
            console.error("❌ showTimeId is missing!");
            return;
        }

        const fetchShowtimeDetails = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/showtimes/${showTimeId}`);
                setShowTimeDetails(response.data);
            } catch (error) {
                console.error("❌ Error fetching showtime details:", error);
            }
        };

        const fetchBookedSeats = async () => {
            try {
                setLoading(true);
                const response = await axios.get(`http://localhost:8080/bookings/booked-seats/${showTimeId}`);
                setBookedSeats(response.data);
            } catch (error) {
                console.error("❌ Error fetching booked seats:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchShowtimeDetails();
        fetchBookedSeats();

        const intervalId = setInterval(fetchBookedSeats, 10000); // Refresh booked seats every 10 seconds
        return () => clearInterval(intervalId);
    }, [showTimeId]);

    const handleSeatClick = (seat) => {
        if (bookedSeats.includes(seat)) return;

        setSelectedSeats((prevSelected) =>
            prevSelected.includes(seat)
                ? prevSelected.filter(s => s !== seat)
                : [...prevSelected, seat]
        );
    };

    

    const handleBooking = async () => {
        if (!showTimeId || !userId || !movieId) {
            toast.error("Missing showTimeId, userId or movieId.");
            return;
        }
    
        if (selectedSeats.length === 0) {
            toast.error("Please select at least one seat!");
            return;
        }
    
        const bookingRequest = {
            showTimeId,
            userId,
            movieId, 
            selectedSeats,
            bookingDate: new Date().toISOString().split("T")[0],
            showTime: showTimeDetails ? showTimeDetails.time : "",
            showDate: showTimeDetails ? showTimeDetails.date : "",
        };
    
        try {
            const response = await axios.post("http://localhost:8080/bookings", bookingRequest);
            console.log("✅ Booking successful:", response.data);
            toast.success("Booking Successful!");
    
            setSelectedSeats([]);
            onBookingSuccess && onBookingSuccess();
    
            // Refresh booked seats
            const updatedSeats = await axios.get(`http://localhost:8080/bookings/booked-seats/${showTimeId}`);
            setBookedSeats(updatedSeats.data);
    
         
            setTimeout(() => {
                navigate("/user/home");
            }, 2000);
    
        } catch (error) {
            console.error("❌ Booking failed:", error);
            toast.error(`Booking Failed: ${error.response?.data?.message || "Server Error"}`);
        }
    };
    return (
        <div className="book-seats-container">
            <h2 className="book-seats-title">Select Your Seats</h2>

            {loading ? (
                <p className="book-seats-loading">Loading seats...</p>
            ) : (
                <div className="book-seats-grid">
                    {["A", "B", "C", "D", "E"].map(row =>
                        Array.from({ length: 10 }, (_, i) => i + 1).map(num => {
                            const seat = `${row}${num}`;
                            const isBooked = bookedSeats.includes(seat);
                            const isSelected = selectedSeats.includes(seat);

                            return (
                                <div
                                    key={seat}
                                    onClick={() => handleSeatClick(seat)}
                                    className={`book-seats-seat ${
                                        isBooked
                                            ? "booked"
                                            : isSelected
                                                ? "selected"
                                                : "available"
                                    }`}
                                >
                                    {seat}
                                </div>
                            );
                        })
                    )}
                </div>
            )}

            <div className="book-seats-screen-wrapper">
                <img
                    src="https://assets-in.bmscdn.com/m6/images/seat-layout/cinema-screen.png"
                    alt="Cinema Screen"
                    className="book-seats-screen-image"
                />
            </div>

            <button
                onClick={handleBooking}
                className="book-seats-button"
                disabled={loading}
            >
                {loading ? "Processing..." : "Book Your Seats"}
            </button>

            <ToastContainer />
        </div>
    );
};

export default SeatSelection;
