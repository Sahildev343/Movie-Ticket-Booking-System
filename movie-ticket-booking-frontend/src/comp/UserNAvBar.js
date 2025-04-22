import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";

function UserNavBar() {
  const [menuOpen, setMenuOpen] = useState(false);
  const navigate = useNavigate();

  // Get user email from localStorage
  const userEmail = localStorage.getItem("userEmail");

  const handleLogout = () => {
    console.log("Logging out user:", userEmail);
    localStorage.removeItem("userEmail");
    navigate("/signin");
  };

  return (
    <nav className="user-navbar">
      <div className="user-logo">
        <Link to="/user/home">🎬 BookMyCinema</Link>
      </div>

      <div className="user-menu-icon" onClick={() => setMenuOpen(!menuOpen)}>
        {menuOpen ? "✖️" : "☰"}
      </div>

      <ul className={`user-nav-links ${menuOpen ? "active" : ""}`}>
        {/* "My Bookings" Button */}
        {userEmail && (
          <li>
            <Link to="/profile" className="user-nav-link">
              My Profile
            </Link>
          </li>
        )}

        {userEmail && (
          <li>
            <Link to={`/my-bookings`} className="user-nav-link">
              My Bookings
            </Link>
          </li>
        )}

        {/* Logout Button */}
        <li>
          <button onClick={handleLogout} className="user-logout-button">
            Logout
          </button>
        </li>
      </ul>
    </nav>
  );
}

export default UserNavBar;
