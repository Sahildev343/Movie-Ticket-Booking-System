import axios from "axios";
import { useState } from "react";
import Navbar from "../../comp/Navbar";
import { Link, useNavigate } from "react-router-dom";
import "./EmailComponent.css";

function EmailInput() {
    const [email, setEmail] = useState("");
    const [message, setMessage] = useState("");
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();

    const ChangeE = (e) => setEmail(e.target.value);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setMessage("");
        setIsLoading(true);

        try {
            const res = await axios.post(
                "http://localhost:8080/users/email",
                { email },
                { withCredentials: true } // 🔹 Allows session cookies
            );

            const responseData = res.data.trim().toLowerCase();

            if (responseData === "mail sent") {
                setMessage("✅ OTP Sent! Check your email.");
                setTimeout(() => navigate("/otpverify"), 3000);
            } else if (responseData === "email not registered") {
                setMessage("❌ Email not registered. Please check again.");
            } else {
                setMessage("⚠️ Unexpected response from the server.");
            }
        } catch (error) {
            setMessage("❌ Something went wrong. Please try again.");
            console.error("Error:", error);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div>
            <Navbar />
            <div className="forgot-password-container">
                <div className="email-form-box">
                    <h3 className="forget-password-text">Forgot Password?</h3>
                    <p>Enter your registered email and we'll send you an OTP to verify 🔒</p>

                    <form onSubmit={handleSubmit}>
                        <div className="input-group">
                            <input
                                type="email"
                                id="email"
                                name="email"
                                onChange={ChangeE}
                                value={email}
                                required
                            />
                            <label htmlFor="email">Email</label>
                        </div>

                        <button type="submit" className="reset-btn" disabled={isLoading}>
                            {isLoading ? "Sending..." : "Send OTP"}
                        </button>

                        {isLoading && <div className="loading-spinner"></div>}
                    </form>

                    {message && <p className="response-message">{message}</p>}

                    <p className="back-to-login">
                        <Link to="/signin">⬅ Back to Sign In</Link>
                    </p>
                </div>
            </div>
        </div>
    );
}

export default EmailInput;
