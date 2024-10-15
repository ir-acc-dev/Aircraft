import { useNavigate } from "react-router-dom";
import { TextField, Button, Box, Typography, Container } from '@mui/material';
import { createPilot } from "../services/PilotService.js";
import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup';
import * as Yup from 'yup';

// Define validation schema using Yup
const validationSchema = Yup.object().shape({
    firstName: Yup.string()
        .required('First name is required')
        .matches(/^[A-Za-z]+$/, 'First name can only contain letters'),
    lastName: Yup.string()
        .required('Last name is required')
        .matches(/^[A-Za-z]+$/, 'Last name can only contain letters'),
    age: Yup.number()
        .required('Age is required')
        .positive('Age must be a positive number')
        .integer('Age must be a whole number')
        .min(1, 'Age must be at least 1')
        .max(120, 'Age cannot exceed 120'),
});

const PilotEntry = () => {
    const navigator = useNavigate();

    // Use React Hook Form with validation schema
    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: yupResolver(validationSchema),
    });

    const onSubmit = (data) => {
        console.log(data);
        createPilot(data).then((response) => {
            console.log(response.data);
            navigator("/pilots");
        }).catch(error => {
            console.error(error);
        });
    };

    return (
        <Container maxWidth="sm">
            <Typography variant="h4" gutterBottom> Add Pilot </Typography>
            <Box
                component="form"
                onSubmit={handleSubmit(onSubmit)}
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    gap: 2, // Space between items
                }}
            >
                <TextField
                    label="First Name"
                    placeholder="Enter your first name"
                    {...register('firstName')}
                    error={!!errors.firstName}
                    helperText={errors.firstName ? errors.firstName.message : ''}
                    fullWidth
                />
                <TextField
                    label="Last Name"
                    placeholder="Enter your last name"
                    {...register('lastName')}
                    error={!!errors.lastName}
                    helperText={errors.lastName ? errors.lastName.message : ''}
                    fullWidth
                />
                <TextField
                    label="Age"
                    type="number"
                    placeholder="Enter your age"
                    {...register('age')}
                    error={!!errors.age}
                    helperText={errors.age ? errors.age.message : ''}
                    fullWidth
                />
                <Button variant="contained" color="primary" type="submit" fullWidth>
                    Submit
                </Button>
            </Box>
        </Container>
    );
};

export default PilotEntry;
