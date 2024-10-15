import axios from "axios";

const AIRCRAFT_BASE_URL = 'http://localhost:8080/api/aircrafts'

export const listAircrafts = () => {
    return axios.get(AIRCRAFT_BASE_URL);
}

export const createAircraft = (aircraft) => {
    return axios.post(AIRCRAFT_BASE_URL, aircraft)
}

export const getAircraft = (aircraftId) => {
    return axios.get(AIRCRAFT_BASE_URL + "/" + aircraftId)
}

export const deleteAircraft = (aircraftId) => {
    return axios.delete(AIRCRAFT_BASE_URL + '/' + aircraftId)
}