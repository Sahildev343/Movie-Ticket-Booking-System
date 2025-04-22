import { useState } from "react";
import { Link, useLocation } from "react-router-dom";
import "./Navbar.css"; 

function Navbar() {
    const [menuOpen, setMenuOpen] = useState(false);
    const location = useLocation();

    return (
        <nav className="navbar">
            <div className="logo">
                <Link to="/">🎬 Movie Booking</Link>
            </div>

            <div className="menu-icon" onClick={() => setMenuOpen(!menuOpen)}>
                {menuOpen ? "✖️" : "☰"}
            </div>

            <ul className={`nav-links ${menuOpen ? "active" : ""}`}>
                <li><Link to="/" className={location.pathname === "/home" ? "active" : ""}>Home</Link></li>
                <li><Link to="/about" className={location.pathname === "/about" ? "active" : ""}>About</Link></li>
                <li><Link to="/signup" className={location.pathname === "/signup" ? "active" : ""}>Sign Up</Link></li>
                <li><Link to="/signin" className={location.pathname === "/signin" ? "active" : ""}>Sign In</Link></li>
            </ul>
        </nav>
    );
}

export default Navbar;
