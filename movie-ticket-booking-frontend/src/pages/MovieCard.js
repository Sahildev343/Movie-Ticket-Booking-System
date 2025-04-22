import { useNavigate } from "react-router-dom";
import '../pages/MovieCard.css';



function MovieCard({ movie }) {

  var navigate = useNavigate();
  const handleBookNow = (movieId) => {
    localStorage.setItem("movieId", movieId); // ✅ Save clicked movieId
    navigate(`/select-theater/${movie.id}`); // Navigate to Booking Page
};
  return (
    <div className="movie-card">
      <img src={movie.moviePoster} alt={movie.title} className="movie-image" />
      <h3 className="movie-title">{movie.title}</h3>
      <button onClick = {() => handleBookNow(movie.id)} className="book-now">Book Now</button>

    </div>
  );  
}

export default MovieCard;
