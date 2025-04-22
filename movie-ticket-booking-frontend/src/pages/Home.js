import { useEffect, useState } from "react";
import axios from "axios";
import MovieCard from "./MovieCard"; // Ensure the import path is correct
import './Home.css';
import UserNavBar from "../comp/UserNAvBar";

function Home() {
  const [movies, setMovies] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");

  useEffect(() => {
    axios.get("http://localhost:8080/movies")
      .then((response) => {
        console.log("🎬 API Response:", response.data);

        if (Array.isArray(response.data)) {
          setMovies(response.data);
        } else if (response.data._embedded?.movies) {
          setMovies(response.data._embedded.movies);
        } else {
          console.error("❌ Unexpected API Response Format:", response.data);
          setMovies([]);
        }

        console.log("Movies array after fetching:", response.data);
      })
      .catch((error) => {
        console.error("🚨 API Error:", error);
        setMovies([]);
      });
  }, []);

  // Filter movies based on search query (case insensitive)
  const filteredMovies = movies.filter((movie) =>
    movie.title?.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div>
      <UserNavBar />

      <div className="container mx-auto p-6">
        <h1>🎬 Available Movies</h1>

        <input 
          type="text" 
          placeholder="Search for a movie..." 
          className="search-bar"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
        />

        <div className="movie-grid">
          {filteredMovies.length > 0 ? (
            filteredMovies.map((movie, index) => {
              const key = movie.id ? movie.id : `${movie.title}-${index}`;
              return <MovieCard key={key} movie={movie} />;
            })
          ) : (
            <p>No movies found</p>
          )}
        </div>
      </div>
    </div>
  );
}

export default Home;
