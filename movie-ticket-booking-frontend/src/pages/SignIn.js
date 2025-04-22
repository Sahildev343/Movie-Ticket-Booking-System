import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import './SignIn.css';
import Navbar from "../comp/Navbar";
import { toast, ToastContainer } from "react-toastify";

function SignIn() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!email || !password) {
            toast.error("Enter Credentials...");
            return;
        }

        axios.post(`http://localhost:8080/users/signin?email=${email}&password=${password}`)
    .then((response) => {
        const resData = response.data;

        if (resData && resData.userId && resData.email) {
            // ✅ Successful login
            console.log("Logged in successfully...");
            toast.success("Login Successfully");

            // Store both email and userId in localStorage
            localStorage.setItem("userEmail", resData.email);
            localStorage.setItem("userId", resData.userId);

            navigate('/user/home');
        } else {
            // ❌ Response didn't have expected data
            toast.error("Enter Valid Details");
        }
    })
    .catch((error) => {
        console.error("SignIn Error:", error);
        toast.error("Error during Sign In");
    });

    };

    const handleForgotPassword = () => {
        navigate("/forgot-password");
    };

    return (
        <div>
            <Navbar />
            <div className="signin-container">
                <div className="form-box">
                    <h2>Sign In</h2>
                    <p>Welcome back! Please enter your details 🎭</p>

                    <form onSubmit={handleSubmit}>
                        <div className="input-group">
                            <input
                                type="email"
                                id="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                                required
                            />
                            <label htmlFor="email">Email</label>
                        </div>
                        <div className="input-group">
                            <input
                                type="password"
                                id="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                            <label htmlFor="password">Password</label>
                        </div>
                        <button type="submit" className="signin-btn">Sign In</button>
                    </form>

                    <button className="forgot-password-btn" onClick={handleForgotPassword}>
                        Forgot Password?
                    </button>

                    <p className="signup-text">
                        Don't have an account? <Link to="/signup">signup</Link>
                    </p>
                </div>
            </div>
            <ToastContainer
  position="top-center"
  autoClose={2500}
  hideProgressBar={false}
  closeOnClick
  pauseOnHover
  draggable
  pauseOnFocusLoss
  className="custom-toast-container"
  toastClassName="custom-toast"
  bodyClassName="custom-toast-body"
  progressClassName="custom-toast-progress"
/>


        </div>
    );
}

export default SignIn;
