import {useEffect, useState} from "react";
import {deleteAircraft, listAircrafts} from "../services/AircraftService.js";
import {Box, Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";

const AircraftList = () => {

    const [aircraft, setAircraft] = useState([])
    const navigator = useNavigate();

    const getAllAircraft = () => {
        listAircrafts()
            .then((response) => {
                setAircraft(response.data)
            }).catch((error) => {
                console.error(error)
        })
    }

    useEffect(() => {
        getAllAircraft();
    }, []);

    const addAircraft = () => {
        navigator("/add-aircraft")
    };

    const removeAircraft = (id) => {
        deleteAircraft(id)
            .then(() => {
                getAllAircraft()
            }).catch(error => {
            console.error(error)
        })
    };
    return (
        <Box sx={{ padding: 3 }}>

            <Typography variant="h4" gutterBottom> Aircraft List</Typography>

            <TableContainer component={Paper}>

                <Table>

                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Airframe</TableCell>
                            <TableCell>Pilot ID</TableCell>
                            <TableCell>Pilot Name</TableCell>
                            <TableCell>Delete</TableCell>
                        </TableRow>
                    </TableHead>

                    <TableBody>
                        {aircraft.map((aircraft) => (
                            <TableRow key={aircraft.id}>
                                <TableCell>{aircraft.id}</TableCell>
                                <TableCell>{aircraft.airframe}</TableCell>
                                <TableCell>{aircraft.pilot.id}</TableCell>
                                <TableCell>{aircraft.pilot.firstName + " " + aircraft.pilot.lastName}</TableCell>
                                <TableCell>
                                    <Button variant='contained' color='error' sx={{ marginLeft: "10px" }} onClick={() => {removeAircraft(aircraft.id)}}> Delete </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>

                </Table>

            </TableContainer>

            <Button variant="contained" color="primary" sx={{ marginTop: "30px" }} onClick={addAircraft}> Add Aircraft </Button>

        </Box>
    );
};

export default AircraftList;