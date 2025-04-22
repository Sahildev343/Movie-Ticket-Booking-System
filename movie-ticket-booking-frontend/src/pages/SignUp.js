import { useState } from 'react';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import './SignUp.css';
import Navbar from '../comp/Navbar';

function SignUp() {

    const [signup, setSignup] = useState({ name : "", email : "", password : "", mobile_no : "" });
    const navigate = useNavigate(); 

    const hChange = (e) => {
        const {name, value} = e.target;
        setSignup({...signup, [name]: value});
    };

   

    var handleSubmit = (e) => {
        e.preventDefault();
       
           
            axios.post("http://localhost:8080/users/register", signup)
                .then(() => {
                    navigate("/signin");
                })
                .catch((err) => {
                    console.log(err);
                });
        
    };

    return (
        <div>
            <Navbar />
            <div className="signup-container">
                <div className="form-box">
                    <h2>Sign Up</h2>
                    <p className='p-type'>Join us and book your favorite movies 🎬</p>

                    <form>
                        <div className="input-group">
                            <input
                                type="text"
                                id="name"
                                name="name"
                                onChange={hChange}
                                required
                            />
                            <label htmlFor="name">Full Name</label>
                        </div>

                        <div className="input-group">
                            <input
                                type="text"
                                id="mobile_no"
                                name="mobile_no"
                                onChange={hChange}
                                required
                            />
                            <label htmlFor="mobile_no">Mobile No</label>
                        </div>

                        <div className="input-group">
                            <input
                                type="email"
                                id="email"
                                name="email"
                                onChange={hChange}
                                required
                            />
                            <label htmlFor="email">Email</label>
                        </div>

                        <div className="input-group">
                            <input
                                type="password"
                                id="password"
                                name="password"
                                onChange={hChange}
                                required
                            />
                            <label htmlFor="password">Password</label>
                        </div>

                      

                        <button
                            type="submit"
                            className="signup-btn"
                            onClick={handleSubmit}
                        >
                            Sign Up
                        </button>

                        <p className="signin-text">
                            Already have an account? <Link to="/signin">Sign In</Link>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    );
}

export default SignUp;
