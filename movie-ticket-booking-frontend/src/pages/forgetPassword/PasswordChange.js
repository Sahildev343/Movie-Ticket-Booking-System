import React, { useState } from 'react';
import axios from 'axios';
import './ResetPassword.css'; // Make sure you link the corresponding CSS file for styling
import { Link } from 'react-router-dom';

const ResetPassword = () => {
  const [newPassword, setNewPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [loading, setLoading] = useState(false);
  const [successMessage, setSuccessMessage] = useState('');

  const handlePasswordReset = async (e) => {
    e.preventDefault();
  
    if (newPassword !== confirmPassword) {
      setErrorMessage("Passwords do not match.");
      return;
    }
  
    setLoading(true);
    setErrorMessage('');
    setSuccessMessage('');
  
    try {
      const response = await axios.put(
        "http://localhost:8080/users/changePassword",
        {
          newPassword,
          confirmPassword
        },
        {
          withCredentials: true,  // Ensure session cookies are sent
        }
      );
  
      if (response.status === 200) {
        setSuccessMessage('Password successfully reset!');
      }
    } catch (error) {
      setErrorMessage('Error resetting password. Please try again.');
    } finally {
      setLoading(false);
    }
  };
  
  

  return (
    <div className="reset-password-container">
      <div className="reset-form-box">
        <h2 className="reset-password-text">Reset Password</h2>

        {/* Display error message if passwords do not match */}
        {errorMessage && <div className="reset-response-message error">{errorMessage}</div>}

        {/* Display success message */}
        {successMessage && <div className="reset-response-message success">{successMessage}</div>}

        <form onSubmit={handlePasswordReset}>
          <div className="reset-input-group">
            <input
              type="password"
              id="newPassword"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              required
            />
            <label htmlFor="newPassword">New Password</label>
          </div>

          <div className="reset-input-group">
            <input
              type="password"
              id="confirmPassword"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
            />
            <label htmlFor="confirmPassword">Confirm Password</label>
          </div>

          {loading ? (
            <div className="reset-loading-spinner"></div>
          ) : (
            <button type="submit" className="reset-btn">
              Reset Password
            </button>
          )}
        </form>

        <div className="reset-back-to-login">
          <Link to={'/signin'}>Back to Sign In</Link>
        </div>
      </div>
    </div>
  );
};

export default ResetPassword;
