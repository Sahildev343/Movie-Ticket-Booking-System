import React, { useEffect, useState } from 'react';
import './WelcomePage.css';

import Navbar from '../comp/Navbar';
import { Link, useNavigate } from 'react-router-dom';
import { Alert } from 'bootstrap';

function WelcomePage() {

  const [alertMessage, setAlertMessage] = useState('');
  const [showAlert, setShowAlert] = useState(false);
  const [inputValue, setInputValue] = useState('');
  const [errorMessage, setErrorMessage] = useState('');


  const navigate = useNavigate();
  const [nowShowingMovies] = useState([
    { id: 1, title: 'Chhaava', poster: 'https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-image,i-discovery-catalog@@icons@@star-icon-202203010609.png,lx-24,ly-615,w-29,l-end:l-text,ie-OS4yLzEwICAzMzIuM0sgVm90ZXM%3D,fs-29,co-FFFFFF,ly-612,lx-70,pa-8_0_0_0,l-end/et00408691-ccqetrbtcj-portrait.jpg' },

    { id: 2, title: 'Sikandar', poster: 'https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-image,i-discovery-catalog@@icons@@star-icon-202203010609.png,lx-24,ly-615,w-29,l-end:l-text,ie-Ni4xLzEwICA2LjhLIFZvdGVz,fs-29,co-FFFFFF,ly-612,lx-70,pa-8_0_0_0,l-end/et00394804-ejdtwrjrtz-portrait.jpg' },

    { id: 3, title: 'Empuraan', poster: 'https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-image,i-discovery-catalog@@icons@@star-icon-202203010609.png,lx-24,ly-615,w-29,l-end:l-text,ie-OC4xLzEwICA2Ny43SyBWb3Rlcw%3D%3D,fs-29,co-FFFFFF,ly-612,lx-70,pa-8_0_0_0,l-end/et00305698-jquqhbdnvv-portrait.jpg' },
    { id: 4, title: 'The Diplomat', poster: 'https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-image,i-discovery-catalog@@icons@@star-icon-202203010609.png,lx-24,ly-615,w-29,l-end:l-text,ie-OC44LzEwICAyMC40SyBWb3Rlcw%3D%3D,fs-29,co-FFFFFF,ly-612,lx-70,pa-8_0_0_0,l-end/et00363454-yvvwucdegu-portrait.jpg' },
  ]);

  const [comingSoonMovies] = useState([
    { id: 5, title: 'A MineCraft Movie', poster: 'https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-text,ie-NCwgQXByIDIwMjU%3D,fs-29,co-FFFFFF,ly-612,lx-24,pa-8_0_0_0,l-end/et00410687-tkgbmjdjmf-portrait.jpg' },

    { id: 6, title: 'The Raja Saab', poster: 'https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-text,ie-MTAsIEFwciAyMDI1,fs-29,co-FFFFFF,ly-612,lx-24,pa-8_0_0_0,l-end/et00383697-qwhwgzxwgh-portrait.jpg'},

    { id: 7, title: 'Mahavatar Narsimha', poster: 'https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-text,ie-MywgQXByIDIwMjU%3D,fs-29,co-FFFFFF,ly-612,lx-24,pa-8_0_0_0,l-end/et00429289-lelpvnleyb-portrait.jpg' },

    { id: 8, title: 'A Kannappa', poster: 'https://assets-in.bmscdn.com/discovery-catalog/events/tr:w-400,h-600,bg-CCCCCC:w-400.0,h-660.0,cm-pad_resize,bg-000000,fo-top:l-text,ie-MjUsIEFwciAyMDI1,fs-29,co-FFFFFF,ly-612,lx-24,pa-8_0_0_0,l-end/et00377025-bbdfrznvfp-portrait.jpg' },
  ]);

 
  const [currentSlide, setCurrentSlide] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentSlide((prevSlide) => (prevSlide + 1) % nowShowingMovies.length);
    }, 5000); // Change slide every 5 seconds

    return () => clearInterval(interval); // Clean up the interval on unmount
  }, [nowShowingMovies.length]);

  const nextSlide = () => {
    setCurrentSlide((prevSlide) => (prevSlide + 1) % nowShowingMovies.length);
  };

  const prevSlide = () => {
    setCurrentSlide((prevSlide) => (prevSlide - 1 + nowShowingMovies.length) % nowShowingMovies.length);
  };

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
    setErrorMessage(''); // Clear error on typing
  };

  const bookButton = ()=> {

    setAlertMessage('Register to access');
    setShowAlert(true);
  
  }

  const subscribeButton = ()=> {
    if (!inputValue.trim()) {
      setErrorMessage('Please fill out this field before submitting!');
    } else {
    alert("Subscribed Successfully")
      setInputValue(''); // Clear input after successful submission
    }

        }

        const closeAlert = () => {
          setShowAlert(false);
          
        };


  return (
    <div className="welcome-page">
      <header className="header">
      <Navbar />
      </header>

      <section className="carousel">
      <div className="carousel-container">
        <button className="carousel-prev" onClick={prevSlide}>
          &lt;
        </button>
        <div className="carousel-slide">
          <img
            src={nowShowingMovies[currentSlide].poster}
            alt={nowShowingMovies[currentSlide].title}
            className="carousel-image"
          />
          <div className="carousel-content">
            <h2>{nowShowingMovies[currentSlide].title}</h2>
            <p>A thrilling adventure...</p>
            <button onClick={bookButton} className="hero-details-button">Book Tickets</button>
          </div>
        </div>
        <button className="carousel-next" onClick={nextSlide}>
          &gt;
        </button>
      </div>
    </section>


      <section className="movie-list">
        <div className="container">
          <h2 className="movie-list-h2">Now Showing</h2>
          <div className="movie-grid">
            {nowShowingMovies.map((movie) => (
              <div key={movie.id} className="movie-card">
                <img src={movie.poster} alt={movie.title} className="movie-card-img" />
                <h3 className="movie-card-h3">{movie.title}</h3>
                <button onClick={bookButton} className="hero-details-button">Book Tickets</button>
              </div>
            ))}
          </div>
          <Link to={'/nowShowing/view'} className="movie-list-view-all">View All</Link>
         
        </div>
      </section>

      <section className="movie-list">
        <div className="container">
          <h2 className="movie-list-h2">Coming Soon</h2>
          <div className="movie-grid">
            {comingSoonMovies.map((movie) => (
              <div key={movie.id} className="movie-card">
                <img src={movie.poster} alt={movie.title} className="movie-card-img" />
                <h3 className="movie-card-h3">{movie.title}</h3>
                
               
              </div>
            ))}
          </div>
          <a href="#" className="movie-list-view-all">View All</a>
        </div>
      </section>

      <section className="subscribe">
        <div className="container">
          <h2 className="subscribe-h2">Subscribe to our Newsletter</h2>
          
          <form>
            <input type="email" placeholder="Enter your email" value={inputValue} onChange={handleInputChange} className="subscribe-input" required />
            <button onClick={subscribeButton} className="subscribe-button">Subscribe</button>
            </form>
            {errorMessage && <p style={{ color: 'red', marginTop: '10px' }}>{errorMessage}</p>}
        </div>
      </section>

      <footer className="footer">
        <div className="container">
          <div className="footer-links">
            <a href="#" className="footer-links-a">Account</a>
            <a href="#" className="footer-links-a">Subscribe to Newsletter</a>
          </div>
          <p>&copy; {new Date().getFullYear()} BookMyTicket. All rights reserved.</p>
        </div>
      </footer>

      {showAlert && (
        <div className="custom-alert">
          <div className="custom-alert-content">
            <p>{alertMessage}</p>
            <button onClick={closeAlert}>OK</button>
          </div>
        </div>
      )}
    </div>
  );
}

export default WelcomePage;