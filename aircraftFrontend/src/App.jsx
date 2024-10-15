import './App.css'
import {BrowserRouter, Routes, Route} from "react-router-dom";
import PilotList from "./components/PilotList.jsx";
import PilotEntry from "./components/PilotEntry.jsx";
import HeaderComponent from "./components/HeaderComponent.jsx";
import Home from "./components/Home.jsx";
import AircraftList from "./components/AircraftList.jsx";
import AircraftEntry from "./components/AircraftEntry.jsx";

function App() {


  return (
    <>
        <BrowserRouter>
            <HeaderComponent/>

            <Routes>
                <Route path = '/' element={<Home />}></Route>
                <Route path = '/pilots' element={<PilotList />}></Route>
                <Route path = '/add-pilot' element={<PilotEntry />}></Route>
                <Route path = '/aircrafts' element={<AircraftList />}></Route>
                <Route path = '/add-aircraft' element={<AircraftEntry />}></Route>
            </Routes>

        </BrowserRouter>
    </>
  )
}

export default App
