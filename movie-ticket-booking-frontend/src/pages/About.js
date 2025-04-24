
import React from "react";
import "./About.css";
import profileImg from "../images/profileImg.jpeg";
import Ankush from "../images/ANKUSH.jpg";
import Teena from "../images/TEENA.jpg";
import Navbar from "../comp/Navbar";


function About() {

    return (
        <div className="about-container">
            <Navbar />
        {/* Hero Section */}
        <div className="hero">
            <h1>Welcome to <span>BookMyCinema</span></h1>
            <p>Your ultimate destination for booking movie tickets effortlessly.</p>
        </div>

        {/* Features Section */}
        <div className="features">
            <div className="feature-box">
                <h3>🎟️ Easy Booking</h3>
                <p>Select your favorite movies, theaters, and seats in a few clicks.</p>
            </div>
            <div className="feature-box">
                <h3>⚡ Fast & Secure</h3>
                <p>Experience quick booking our favourite movies</p>
            </div>
            <div className="feature-box">
                <h3>🌍 Explore Movies</h3>
                <p>Get details of Ongoing movies and book in advance.</p>
            </div>
        </div>

        {/* Our Team Section */}
        <div className="team">
            <h2>Meet Our Team</h2>
            <div className="team-members">
                <div className="team-member">
                    <img src={profileImg} alt="team1" />
                    <h4>Sahil Uradi</h4>
                    <p>CEO & Founder</p>
                </div>
                <div className="team-member">
                    <img src={Ankush} alt="team2" />
                    <h4>Ankush Gupta</h4>
                    <p>Lead Developer</p>
                </div>
                <div className="team-member">
                    <img src={Teena} alt="team3" />
                    <h4>Teena Rode</h4>
                    <p>UI/UX Designer</p>
                </div>
            </div>
        </div>

        {/* Call to Action */}
        <div className="cta">
            <h2>Start Booking Now!</h2>
            <p>Join thousands of happy movie-goers today.</p>
            <button onClick={()=> { alert("Register To Access")}}>Book Your Ticket</button>
        </div>
    </div>
    );
}

export default About;