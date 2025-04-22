            import { useNavigate } from "react-router-dom";
            import '../pages/TheaterCard.css';

            function TheaterCard({ theater, onSelect }) {
                const navigate = useNavigate();
                if (!theater) {
                    return <p>Error: Theater data not found!</p>;
                }

            

                const handleSelectTheater = () => {
                    onSelect();
                    navigate(`/select-showtime/${theater.id}`);
                };

                return (
                    
                    <div className="theater-card" onClick={handleSelectTheater}>
                    <h3 className="theater-name">{theater.name}</h3>
                    <p className="theater-location">{theater.location}</p>
                    <button className="select-theater">Select Theater</button>
                  </div>
                  
                  
                );
            }


            export default TheaterCard;
