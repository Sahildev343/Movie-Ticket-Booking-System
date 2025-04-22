import axios from "axios";
import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./ShowTimeSelection.css";

function ShowTimeSelection() {
  const { theaterId } = useParams();
  const [showtimes, setShowtimes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedDate, setSelectedDate] = useState(getTodayDate());
  const navigate = useNavigate();

  // ✅ Get today's date in YYYY-MM-DD format
  function getTodayDate() {
    const today = new Date();
    return today.toISOString().split("T")[0];
  }

  // ✅ Generate next 7 dates for selection
  const getNext7Days = () => {
    const dates = [];
    const today = new Date();
    for (let i = 0; i < 7; i++) {
      const date = new Date(today);
      date.setDate(today.getDate() + i);
      dates.push(date.toISOString().split("T")[0]);
    }
    return dates;
  };

  // ✅ Fetch showtimes for selected theater and date
  useEffect(() => {
    const fetchShowtimes = async () => {
      setLoading(true);
      try {
        // 🔁 ✅ UPDATED API CALL: using correct endpoint with query params
        const response = await axios.get(`http://localhost:8080/showtimes`, {
          params: {
            theaterId: theaterId,       // ✅ Pass theaterId
            date: selectedDate          // ✅ Pass selectedDate
          },
        });

        const originalShowtimes = response.data;

        // ✅ Remove duplicate times (based on time value)
        const uniqueShowtimesMap = new Map();
        originalShowtimes.forEach(showtime => {
          if (!uniqueShowtimesMap.has(showtime.time)) {
            uniqueShowtimesMap.set(showtime.time, showtime);
          }
        });

        const uniqueShowtimes = Array.from(uniqueShowtimesMap.values());

        setShowtimes(uniqueShowtimes);
        setError(null);
      } catch (error) {
        console.error("Error fetching showtimes:", error);
        setError("Failed to load showtimes. Please try again later.");
      } finally {
        setLoading(false);
      }
    };

    if (theaterId && selectedDate) {
      fetchShowtimes();
    }
  }, [theaterId, selectedDate]);

  // ✅ Handle showtime click -> fetch booked seats and navigate
  const handleShowtimeSelection = async (showTimeId) => {
    try {
      const response = await axios.get(`http://localhost:8080/bookings/booked-seats/${showTimeId}`);
      const bookedSeatsData = response.data;

      navigate(`/select-seats/${showTimeId}`, { state: { bookedSeats: bookedSeatsData } });
    } catch (error) {
      console.error("Error fetching booked seats:", error);
      alert("Failed to fetch booked seats. Please try again later.");
    }
  };

  return (
    <div className="showtime-selection-container">
      <h2>Select Date and Showtime</h2>

      {/* ✅ Date Selection */}
      <div className="date-selector">
        {getNext7Days().map((date) => (
          <button
            key={date}
            className={date === selectedDate ? "selected" : ""}
            onClick={() => setSelectedDate(date)}
          >
            {new Date(date).toDateString()}
          </button>
        ))}
      </div>

      {/* ✅ Showtimes */}
      {loading ? (
        <p>Loading showtimes...</p>
      ) : error ? (
        <p className="error">{error}</p>
      ) : showtimes.length === 0 ? (
        <p>No showtimes available for this date.</p>
      ) : (
        <div className="showtime-buttons">
          {showtimes.map((showtime) => (
            <button
              key={showtime.id}
              onClick={() => handleShowtimeSelection(showtime.id)}
            >
              {showtime.time}
            </button>
          ))}
        </div>
      )}
    </div>
  );
}

export default ShowTimeSelection;
