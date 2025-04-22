import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./OTPVerify.css";
import Navbar from "../../comp/Navbar";

function OTPVerify() {
    const [otp, setOtp] = useState("");
    const [message, setMessage] = useState("");
    const [loading, setLoading] = useState(false);
    const [resendMessage, setResendMessage] = useState("");
    const [resendDisabled, setResendDisabled] = useState(false);
    const navigate = useNavigate();

    const handleChange = (e) => {
        setOtp(e.target.value);
    };

    // Function to submit OTP
    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        setMessage("");

        axios.post("http://localhost:8080/users/check", { otp: Number(otp) }, { withCredentials: true })
    .then((res) => {
        setLoading(false);
        if (res.data === "OTP Verified") {
            setMessage("✅ OTP Verified! Redirecting...");
            setTimeout(() => navigate("/resetpassword"), 2000);
        } else {
            setMessage("❌ Invalid OTP. Please try again.");
        }
    })
    .catch((error) => {
        setLoading(false);
        console.error("Error verifying OTP:", error.response?.data || error.message);

        if (error.response?.status === 401) {
            setMessage("⚠️ Session expired. Please request a new OTP.");
        } else {
            setMessage("❌ OTP verification failed. Please try again.");
        }
    });
    };


  

    return (
        <div>
            <Navbar />
            <div className="otpverify-container">
                <div className="otpverify-form-box">
                    <h2>Verify OTP</h2>
                    <p>Enter the OTP sent to your email 📩</p>

                    <form onSubmit={handleSubmit}>
                        <div className="otpverify-input-group">
                            <input
                                type="text"
                                id="otp"
                                name="otp"
                                onChange={handleChange}
                                value={otp}
                                required
                                maxLength={6}
                                placeholder="Enter 6-digit OTP"
                            />
                        </div>
                        <button type="submit" className="otpverify-verify-btn" disabled={loading}>
                            {loading ? "Verifying..." : "Verify OTP"}
                        </button>
                    </form>

                   

                    {/* Display response messages */}
                    {message && <p className="otpverify-response-message">{message}</p>}
                  
                </div>
            </div>
        </div>
    );
}

export default OTPVerify;
