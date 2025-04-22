import logo from './logo.svg';
import {BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import './App.css';
import Navbar from './comp/Navbar';
import SignUp from './pages/SignUp';
import SignIn from './pages/SignIn';
import Booking from './comp/TheaterSelection';
import About from './pages/About';
import ShowTimeSelection from './pages/ShowTimeSelection';
import SeatSelection from './comp/SeatSelection';
import WelcomePage from './pages/WelcomePage';
import NowShowing from './pages/NowShowingMovie';
import AdminHome from './Admin/AdminHomePage';
import EmailInput from './pages/forgetPassword/EmailComponent';
import OTPVerify from './pages/forgetPassword/OTPVerify';
import ResetPassword from './pages/forgetPassword/PasswordChange';
import AdminManageMovies from './Admin/AdminManageMovies';
import { useEffect, useState } from 'react';
import BookingInfo from './comp/BookingInfo';
import UserProfile from './pages/UserProfilesCard';






function App() {

  const [userEmail, setUserEmail] = useState(localStorage.getItem("userEmail"));

  useEffect(() => {
    const handleStorageChange = () => {
      setUserEmail(localStorage.getItem("userEmail"));
    };

    window.addEventListener("storage", handleStorageChange);
    return () => window.removeEventListener("storage", handleStorageChange);
  }, []);

  return (
    <div className="App">

     <BrowserRouter>
    
     <Routes>
      <Route path='/' element = {<WelcomePage />}></Route>
      <Route path='/user/home' element = {<Home />}></Route>
     
      {/* <Route path="/movie/:id" element={<MovieDetails />} /> */}
      <Route path='/signup' element = {<SignUp />} ></Route>
      <Route path='/signin' element = {<SignIn />} ></Route>
      <Route path='/about' element = {<About />} ></Route>
      <Route path='/select-theater/:movieId' element = {<Booking />} />
      <Route path='/select-showtime/:theaterId' element = {<ShowTimeSelection />} />
      <Route path='/select-seats/:showTimeId' element = { <SeatSelection 
            userEmail={userEmail} 
            onBookingSuccess={() => console.log("Booking Success!")} 
        />} />
      <Route path='/nowShowing/view' element = {<NowShowing />} />
      <Route path='/admin/home' element = {<AdminHome />} />
      <Route path='/forgot-password' element = {<EmailInput />} />
      <Route path='/otpverify' element = {<OTPVerify />} />
      <Route path='/resetpassword' element = {<ResetPassword />} />
      <Route path='/admin/movies' element = {<AdminManageMovies />} />
      <Route path="/my-bookings" element={<BookingInfo />} />
      <Route path="/profile" element={<UserProfile />} />

      
     
      
     
     </Routes>
     </BrowserRouter>
    </div>
  );
}

export default App;
