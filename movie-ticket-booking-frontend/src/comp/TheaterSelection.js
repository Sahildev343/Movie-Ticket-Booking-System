    import axios from "axios";
    import { useEffect, useState } from "react";
    import { useParams } from "react-router-dom";
    import TheaterCard from "../pages/TheaterCard"; // Import TheaterCard component
    import "./TheaterSelection.css";

    function Booking() {
    const { movieId } = useParams();
    const [theaters, setTheaters] = useState([]);
    const [selectedTheater, setSelectedTheater] = useState(null);
    const [showtimes, setShowtimes] = useState([]);
    const [selectedShowtime, setSelectedShowtime] = useState(null);
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [bookedSeats, setBookedSeats] = useState([]);

    // Fetch theaters based on movieId
    useEffect(() => {
    axios.get(`http://localhost:8080/theaters/movie/${movieId}`)
        .then((response) => {
        console.log("Theater API Response:", response.data); // Debugging
        setTheaters(response.data);
        })
        .catch((error) => console.error("Error fetching theaters:", error));
    }, [movieId]);

    const fetchTheaters = () => {
        axios.get(`http://localhost:8080/theaters/movie/${movieId}`)
        .then((response) => setTheaters(response.data))
        .catch((error) => console.error("Error fetching theaters:", error));
    };
    
    // Call fetchTheaters inside useEffect
    useEffect(() => {
        fetchTheaters();
    }, [movieId]);
    

    // Fetch showtimes when a theater is selected
    useEffect(() => {
        if (selectedTheater) {
        axios.get(`http://localhost:8080/showtimes/theater/${selectedTheater.id}`)
            .then((response) => setShowtimes(response.data))
            .catch((error) => console.error("Error fetching showtimes:", error));
        }
    }, [selectedTheater]);

    // Fetch booked seats when a showtime is selected
    useEffect(() => {
        if (selectedShowtime) {
        axios.get(`http://localhost:8080/bookings/booked-seats/${selectedShowtime.id}`)
            .then((response) => setBookedSeats(response.data || []))
            .catch((error) => console.error("Error fetching booked seats:", error));
        }
    }, [selectedShowtime]);

    // Handle showtime selection
    const handleShowtimeSelection = (showtime) => {
        setSelectedShowtime(showtime);
        setSelectedSeats([]); // Reset seat selection
    };

    // Handle seat selection
    const handleSeatClick = (seat) => {
        if (!bookedSeats.includes(seat)) {
        setSelectedSeats((prev) =>
            prev.includes(seat) ? prev.filter((s) => s !== seat) : [...prev, seat]
        );
        }
    };

    // Confirm booking
    const confirmBooking = () => {
        if (!selectedShowtime || selectedSeats.length === 0) {
        alert("Please select a showtime and at least one seat.");
        return;
        }

        // axios.post(`http://localhost:8080/bookings`, {
        // movieId,
        // theaterId: selectedTheater.id,
        // showtimeId: selectedShowtime.id,
        // seats: selectedSeats,
        // })
        // .then(() => {
        // alert("Booking confirmed!");
        // setSelectedSeats([]);
        // axios.get(`http://localhost:8080/bookings/booked-seats/${selectedShowtime.id}`)
        //     .then((response) => setBookedSeats(response.data || []))
        //     .catch((error) => console.error("Error updating booked seats:", error));
        // })
        // .catch((error) => {
        // console.error("Error processing booking:", error);
        // alert("Booking failed. Try again.");
        // });
    };

    return (
        <div className="booking-container">
        {theaters.length === 0 ? (
        <p>No theaters available</p>
    ) : (
       
        theaters.map((theater) => (
        <TheaterCard 
            key={theater.id  || theater.na}
            theater={theater} 
            onSelect={() => setSelectedTheater(theater)} 
        />
        ))
    )}
   
    

        {selectedTheater && (
            <>
            <h2>Select Showtime</h2>
            <div className="showtime-list">
            {showtimes.length > 0 ? (
        showtimes.map((showtime) => (
        <button 
            key={showtime.id} // ✅ Unique key added
            onClick={() => handleShowtimeSelection(showtime)} 
            className={`showtime-button ${selectedShowtime?.id === showtime.id ? 'selected' : ''}`}
        >
            {showtime.time}
        </button>
        ))
    ) : (
        <p>No showtimes available</p>
    )}
            </div>
            </>
        )}

        {selectedShowtime && (
            <>
            <h2>Select Seats</h2>
            <div className="seat-grid">
                {Array.from({ length: selectedTheater.availableSeats }, (_, i) => `A${i + 1}`).map((seat) => (
                <button
                    key={seat}
                    disabled={bookedSeats.includes(seat)}
                    onClick={() => handleSeatClick(seat)}
                    className={`seat-button ${bookedSeats.includes(seat) ? "booked" : selectedSeats.includes(seat) ? "selected" : "available"}`}
                >
                    {seat}
                </button>
                ))}
            </div>
            <button className="book-button" onClick={confirmBooking}>Confirm Booking</button>
            </>
        )}
        </div>
    );
    }

    export default Booking;
