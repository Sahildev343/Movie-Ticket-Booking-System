

function NowShowingCard({ movie }) {

  
  const handleRequest = () => {
   alert("Register To Book");
};
  return (
    <div className="movie-card">
      <img src={movie.moviePoster} alt={movie.title} className="movie-image" />
      <h3 className="movie-title">{movie.title}</h3>
      <button onClick = {handleRequest} className="book-now">Book Now</button>

    </div>
  );  
}

export default NowShowingCard;