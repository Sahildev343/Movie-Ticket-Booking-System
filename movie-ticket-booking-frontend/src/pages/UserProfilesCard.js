import { useEffect, useState } from "react";
import axios from "axios";
import "./UserProfile.css";
import UserNavBar from "../comp/UserNAvBar";

function UserProfile() {
  const userId = localStorage.getItem("userId");
  const [userData, setUserData] = useState({
    name: "",
    email: "",
    mobile_no: "",
  });

  const [loading, setLoading] = useState(true);
  const [successMsg, setSuccessMsg] = useState("");

  useEffect(() => {
    if (userId) {
      axios.get(`http://localhost:8080/users/${userId}`)
        .then((res) => {
          setUserData(res.data);
          setLoading(false);
        })
        .catch((err) => {
          console.error("❌ Failed to fetch user:", err);
          setLoading(false);
        });
    }
  }, [userId]);

  const handleChange = (e) => {
    setUserData({ ...userData, [e.target.name]: e.target.value });
  };

  const handleUpdate = (e) => {
    e.preventDefault();
    axios.put(`http://localhost:8080/users/${userId}`, userData)
      .then(() => {
        setSuccessMsg("✅ Profile updated successfully!");
      })
      .catch((err) => {
        console.error("❌ Update failed:", err);
        setSuccessMsg("❌ Failed to update profile.");
      });
  };

  if (loading) return <p>Loading your profile...</p>;

  return (

    <div>
    <UserNavBar/>
   
    <div className="user-profile">
      <h2>👤 My Profile</h2>
      {successMsg && <p className="update-msg">{successMsg}</p>}
      <form onSubmit={handleUpdate}>
        <label>Name:</label>
        <input name="name" value={userData.name} onChange={handleChange} />

        <label>Email:</label>
        <input name="email" value={userData.email} readOnly  />

        <label>Phone:</label>
        <input name="mobile_no" value={userData.mobile_no} onChange={handleChange} />

        <button type="submit">Update Profile</button>
      </form>
    </div>
    </div>
  );
}

export default UserProfile;
