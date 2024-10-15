import {NavLink} from "react-router-dom"
import {AppBar, Toolbar, Typography, Button, Box} from "@mui/material";

const HeaderComponent = () => {
    return (
        <Box sx={{ marginBottom: 4 }}>
            <AppBar position="static">
                <Toolbar>
                    {/* Website/Brand Name */}
                    <Typography variant="h6" sx={{ flexGrow: 1 }}> SkyTrack </Typography>

                    {/* Navigation Links */}
                    <Box sx={{ display: 'flex' }}>
                        <Button component={NavLink} to="/" color="inherit"  sx={{ textTransform: 'none' }}> Home </Button>
                        <Button component={NavLink} to="/pilots" color="inherit" sx={{ textTransform: 'none' }}> Pilot </Button>
                        <Button component={NavLink} to="/aircrafts" color="inherit" sx={{ textTransform: 'none' }}> Aircraft </Button>
                    </Box>

                </Toolbar>
            </AppBar>

        </Box>

    );
};

export default HeaderComponent;