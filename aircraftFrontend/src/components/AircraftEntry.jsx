import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { TextField, Button, Box, Typography, Container, MenuItem, CircularProgress } from '@mui/material';
import {listPilots} from "../services/PilotService.js";
import {createAircraft} from "../services/AircraftService.js";

const AircraftEntry = () => {
    const navigate = useNavigate();
    const [airframe, setAirframe] = useState('');
    const [pilotId, setPilotId] = useState('');
    const [pilots, setPilots] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchPilots = async () => {
            try {
                const response = await listPilots();
                setPilots(response.data);
            } catch (error) {
                console.error('Error fetching pilots:', error);
            } finally {
                setLoading(false);
            }
        };
        fetchPilots();
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();

        const aircraft = {
            airframe: airframe,
            pilot: {
                id: Number(pilotId)
            }
        };

        createAircraft(aircraft)
            .then((response) => {
                console.log(response.data);
                navigate("/aircrafts");
            })
            .catch(error => {
                console.error(error);
            });
    };

    return (
        <Container maxWidth="sm">

            <Typography variant="h4" gutterBottom> Add Aircraft </Typography>

            <Box component="form" onSubmit={handleSubmit} sx={{display: 'flex', flexDirection: 'column', gap: 2,}}>

                <TextField label="Airframe"
                    placeholder="Enter airframe name"
                    value={airframe}
                    onChange={(e) => setAirframe(e.target.value)}
                    required
                />

                <TextField
                    select
                    label="Select Pilot"
                    value={pilotId}
                    onChange={(e) => setPilotId(e.target.value)}
                    required
                    fullWidth
                >
                    <MenuItem value=''>Select Pilot</MenuItem>
                    {loading ? (
                        <MenuItem disabled>
                            <CircularProgress size={24} />
                        </MenuItem>
                    ) : (
                        pilots.map(pilot => (
                            <MenuItem key={pilot.id} value={pilot.id}>
                                {pilot.firstName} {pilot.lastName}
                            </MenuItem>
                        ))
                    )}
                </TextField>

                <Button variant="contained" color="primary" type="submit" fullWidth> Submit </Button>

            </Box>

        </Container>
    );
};

export default AircraftEntry;
