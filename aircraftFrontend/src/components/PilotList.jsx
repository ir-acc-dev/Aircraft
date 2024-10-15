import { useEffect, useState } from "react";
import {deletePilot, listPilots} from "../services/PilotService.js";
import { useNavigate } from "react-router-dom";
import {Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Button, Typography,Box} from "@mui/material";

const PilotList = () => {
    const [pilots, setPilots] = useState([]);
    const navigator = useNavigate();

    const getAllPilots = () => {
        listPilots()
            .then((response) => {
                setPilots(response.data);
            })
            .catch((error) => {
                console.error(error);
            });
    };

    useEffect(() => {
        getAllPilots();
    }, []);

    const addNewPilot = () => {
        navigator("/add-pilot");
    };

    const removePilot = (id) => {
        deletePilot(id)
            .then(() => {
                getAllPilots()
            }).catch(error => {
                console.error(error)
        })
    }

    return (
        <Box sx={{ padding: 3 }}>
            <Typography variant="h4" gutterBottom >
                Pilot List
            </Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>First Name</TableCell>
                            <TableCell>Last Name</TableCell>
                            <TableCell>Age</TableCell>
                            <TableCell>Delete</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {pilots.map((pilot) => (
                            <TableRow key={pilot.id}>
                                <TableCell>{pilot.id}</TableCell>
                                <TableCell>{pilot.firstName}</TableCell>
                                <TableCell>{pilot.lastName}</TableCell>
                                <TableCell>{pilot.age}</TableCell>
                                <TableCell> <Button variant='contained' color='error' sx={{ marginLeft: "10px"}} onClick={() => {removePilot(pilot.id)}}> Delete </Button> </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <Button variant="contained" color="primary" onClick={addNewPilot} sx={{ marginTop: "20px" }}>
                Add Pilot
            </Button>
        </Box>
    );
};

export default PilotList;
