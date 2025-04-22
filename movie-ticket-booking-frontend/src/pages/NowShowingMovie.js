import { useEffect, useState } from "react";
import axios from "axios";
import Navbar from "../comp/Navbar";
import NowShowingCard from "./NowShowingMovieCard";

function NowShowingShow() {
  const [movies, setMovies] = useState([]); 
  const [searchTerm, setSearchTerm] = useState("");
 
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
        })
        .catch((error) => {
            console.error("🚨 API Error:", error);
            setMovies([]);
        });
  }, []);

  const handleSearch = (e) => {
    const value = e.target.value;
    setSearchTerm(value);
    const filtered = movies.filter(movie => movie.title.toLowerCase().includes(value.toLowerCase()));
    setMovies(filtered);
  };

  return (
    <div style={{ maxWidth: "1200px", margin: "0 auto", padding: "20px" }}>
      <Navbar />
      <h1 style={{ textAlign: "center", color: "#333", fontSize: "24px" }}>🎬 Available Movies</h1>
      
      <input 
        type="text" 
        placeholder="Search for a movie..." 
        value={searchTerm}
        onChange={handleSearch}
        style={{
          width: "100%",
          padding: "10px",
          fontSize: "16px",
          borderRadius: "5px",
          border: "1px solid #ccc",
          marginBottom: "20px",
        }}
      />

      <div style={{ display: "flex", flexWrap: "wrap", gap: "20px", justifyContent: "center" }}>
        {Array.isArray(movies) && movies.length > 0 ? (
          movies.map((movie) => (
            <NowShowingCard 
              key={movie.id} 
              movie={movie} 
              style={{ width: "250px", boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)", padding: "10px", borderRadius: "10px" }} 
            />
          ))
        ) : (
          <p style={{ color: "red", fontSize: "18px" }}>No movies available</p>
        )}
      </div>
    </div>
  );
}

export default NowShowingShow;
