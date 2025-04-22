import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios"; // Import Axios for API calls
import "./AdminHomePage.css";

const AdminHome = () => {
  const navigate = useNavigate();
  const [stats, setStats] = useState({
    totalMovies: 0,
    totalUsers: 0,
    totalBookings: 0,
  });

  // Fetch Admin Dashboard Stats
  useEffect(() => {
    axios.get("http://localhost:8080/admin/dashboard-stats")
      .then((response) => {
        setStats(response.data);
      })
      .catch((error) => {
        console.error("Error fetching admin stats:", error);
      });
  }, []);

  return (
    <div className="admin-container">
      {/* Navbar */}
      <nav className="admin-nav">
        <h2 className="admin-logo">🎬 Admin Panel</h2>
        <ul className="admin-menu">
          <li onClick={() => navigate("/admin/movies")}>Manage Movies</li>
          <li onClick={() => navigate("/admin/theaters")}>Manage Theaters</li>
          <li onClick={() => navigate("/admin/bookings")}>Manage Bookings</li>
          <li onClick={() => navigate("/admin/users")}>Manage Users</li>
          <li onClick={() => navigate("/admin/reports")}>Reports</li>
          <li className="logout-btn" onClick={() => navigate("/logout")}>Logout</li>
        </ul>
      </nav>

      {/* Statistics Cards */}
      <div className="admin-stats">
        <div className="stat-card movies">
          <h3>Total Movies</h3>
          <p>{stats.totalMovies}</p>
        </div>
        <div className="stat-card users">
          <h3>Total Users</h3>
          <p>{stats.totalUsers}</p>
        </div>
        <div className="stat-card bookings">
          <h3>Total Bookings</h3>
          <p>{stats.totalBookings}</p>
        </div>
       
      </div>
    </div>
  );
};

export default AdminHome;
