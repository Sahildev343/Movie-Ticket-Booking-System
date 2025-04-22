import React, { useEffect, useState, useRef } from "react";
import axios from "axios";
import "./AdminManageMovies.css";

const AdminManageMovies = () => {
  const [movies, setMovies] = useState([]);
  const [newMovie, setNewMovie] = useState({ title: "", genre: "", director: "", duration: "", moviePoster: "" });
  const [editingMovie, setEditingMovie] = useState(null);
  const [loadingButton, setLoadingButton] = useState(null);
  const [popupMessage, setPopupMessage] = useState({ text: "", type: "" });
  const formRef = useRef(null);

  useEffect(() => {
    fetchMovies();
  }, []);

  const fetchMovies = async () => {
    try {
      const response = await axios.get("http://localhost:8080/movies");
      setMovies(response.data);
    } catch (error) {
      console.error("Error fetching movies:", error);
    }
  };


  const showPopup = (message, type) => {
    setPopupMessage({ text: message, type });
    setTimeout(() => setPopupMessage({ text: "", type: "" }), 5000); 
  };


  const handleAddMovie = async () => {
    if (!newMovie.title || !newMovie.genre || !newMovie.director || !newMovie.duration) {
      alert("Please fill in all fields before adding a movie.");
      return;
    }

    setLoadingButton("add");

    try {
      const response = await axios.post("http://localhost:8080/movies", newMovie);
      setMovies([...movies, response.data]);
      setNewMovie({ title: "", genre: "", director: "", duration: "", moviePoster: "" });

      showPopup("Movie added successfully!", "success"); 
    } catch (error) {
      console.error("Error adding movie:", error);
    } finally {
      setLoadingButton(null);
    }
  };


  const handleEdit = (movie, id) => {
    setLoadingButton(`edit-${id}`);
    setTimeout(() => {
      setEditingMovie(movie);
      formRef.current.scrollIntoView({ behavior: "smooth", block: "start" });
      setLoadingButton(null);
    }, 1000);
  };


  const handleUpdateMovie = async () => {
    if (!editingMovie) return;

    setLoadingButton("update");

    try {
      const response = await axios.put(`http://localhost:8080/movies/${editingMovie.id}`, editingMovie);
      setMovies(movies.map((movie) => (movie.id === editingMovie.id ? response.data : movie)));
      setEditingMovie(null);

      showPopup("Movie updated successfully!", "success"); 
    } catch (error) {
      console.error("Error updating movie:", error);
    } finally {
      setLoadingButton(null);
    }
  };


  const handleDelete = async (id) => {
    setLoadingButton(`delete-${id}`);

    setTimeout(async () => {
      try {
        await axios.delete(`http://localhost:8080/movies/${id}`);
        setMovies(movies.filter((movie) => movie.id !== id));

        showPopup("Movie deleted successfully!", "error"); 
      } catch (error) {
        console.error("Error deleting movie:", error);
      }
      setLoadingButton(null);
    }, 1000);
  };

  return (
    <div className="admin-movies-container">
      <h2>Manage Movies</h2>

     
      {popupMessage.text && (
        <div className={`popup-message ${popupMessage.type}`}>
          {popupMessage.text}
        </div>
      )}


      <div className="movie-form" ref={formRef}>
        <input type="text" name="title" placeholder="Title" value={editingMovie ? editingMovie.title : newMovie.title} onChange={(e) => editingMovie ? setEditingMovie({ ...editingMovie, title: e.target.value }) : setNewMovie({ ...newMovie, title: e.target.value })} />
        <input type="text" name="genre" placeholder="Genre" value={editingMovie ? editingMovie.genre : newMovie.genre} onChange={(e) => editingMovie ? setEditingMovie({ ...editingMovie, genre: e.target.value }) : setNewMovie({ ...newMovie, genre: e.target.value })} />
        <input type="text" name="director" placeholder="Director" value={editingMovie ? editingMovie.director : newMovie.director} onChange={(e) => editingMovie ? setEditingMovie({ ...editingMovie, director: e.target.value }) : setNewMovie({ ...newMovie, director: e.target.value })} />
        <input type="text" name="duration" placeholder="Duration (min)" value={editingMovie ? editingMovie.duration : newMovie.duration} onChange={(e) => editingMovie ? setEditingMovie({ ...editingMovie, duration: e.target.value }) : setNewMovie({ ...newMovie, duration: e.target.value })} />
        <input type="text" name="moviePoster" placeholder="Movie Poster URL" value={editingMovie ? editingMovie.moviePoster : newMovie.moviePoster} onChange={(e) => editingMovie ? setEditingMovie({ ...editingMovie, moviePoster: e.target.value }) : setNewMovie({ ...newMovie, moviePoster: e.target.value })} />

        <button className="add-update-btn" onClick={editingMovie ? handleUpdateMovie : handleAddMovie} disabled={loadingButton === "add" || loadingButton === "update"}>
          {loadingButton === "add" || loadingButton === "update" ? <span className="spinner"></span> : editingMovie ? "Update Movie" : "Add Movie"}
        </button>
      </div>

      <div className="movies-list">
        {movies.map((movie) => (
          <div className="movie-card" key={movie.id}>
            <img src={movie.moviePoster || "https://via.placeholder.com/150"} alt={movie.title} />
            <h3>{movie.title}</h3>
            <p><b>Genre:</b> {movie.genre}</p>
            <p><b>Director:</b> {movie.director}</p>
            <p><b>Duration:</b> {movie.duration} min</p>
            <div className="buttons">
              <button className={`edit-btn ${loadingButton === `edit-${movie.id}` ? "loading" : ""}`} onClick={() => handleEdit(movie, movie.id)} disabled={loadingButton === `edit-${movie.id}`}>
                {loadingButton === `edit-${movie.id}` ? <span className="spinner"></span> : "Edit"}
              </button>
              <button className={`delete-btn ${loadingButton === `delete-${movie.id}` ? "loading" : ""}`} onClick={() => handleDelete(movie.id)} disabled={loadingButton === `delete-${movie.id}`}>
                {loadingButton === `delete-${movie.id}` ? <span className="spinner"></span> : "Delete"}
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default AdminManageMovies;
