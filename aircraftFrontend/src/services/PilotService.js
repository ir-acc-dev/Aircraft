import axios from "axios";

const PILOT_BASE_URL = 'http://localhost:8080/api/pilots'

export const listPilots = () => {
    return axios.get(PILOT_BASE_URL);
}

export const createPilot = (pilot) => {
    return axios.post(PILOT_BASE_URL, pilot)
}

export const getPilot = (pilotId) => {
    return axios.get(PILOT_BASE_URL + "/" + pilotId)
}

export const deletePilot = (pilotId) => {
    return axios.delete(PILOT_BASE_URL + '/' + pilotId)
}