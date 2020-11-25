package by.epam.airport;

import by.epam.airport.plane.ExperimentalPlane;
import by.epam.airport.planetype.MilitaryType;
import by.epam.airport.plane.MilitaryPlane;
import by.epam.airport.plane.PassengerPlane;
import by.epam.airport.plane.Plane;


import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;

public class Airport {
    private List<? extends Plane> planes;

    public Airport(List<? extends Plane> planes) {
        this.planes = planes;
    }

    public List<? extends Plane> getAllPlanes() {
        return planes;
    }

    public List<PassengerPlane> getPassengerPlanes() {

        return planes
                .stream()
                .filter(plane -> plane instanceof PassengerPlane)
                .map(plane -> (PassengerPlane) plane)
                .collect(Collectors.toList());
    }

    public List<MilitaryPlane> getMilitaryPlanes() {
        return planes
                .stream()
                .filter(plane -> plane instanceof MilitaryPlane)
                .map(plane -> (MilitaryPlane) plane)
                .collect(Collectors.toList());
    }

    public List<ExperimentalPlane> getExperimentalPlanes() {
        return planes
                .stream()
                .filter(plane -> plane instanceof ExperimentalPlane)
                .map(plane -> (ExperimentalPlane) plane)
                .collect(Collectors.toList());
    }

    public PassengerPlane getPassengerPlaneWithMaxPassengersCapacity() {
        return this.getPassengerPlanes()
                .stream()
                .max(Comparator.comparingInt(PassengerPlane::getPassengersCapacity))
                .get();
    }

    public List<MilitaryPlane> getMilitaryPlanesByMilitaryType(MilitaryType type) {
        return getMilitaryPlanes()
                .stream()
                .filter(militaryPlane -> militaryPlane.getMilitaryType() == type)
                .collect(Collectors.toList());
    }

    public void sortByMaxDistance() {
        planes.sort(Comparator.comparingInt(Plane::getMaxFlightDistance));
    }

    public void sortByMaxSpeed() {
        planes.sort(Comparator.comparingInt(Plane::getMaxSpeed));
    }

    public void sortByMaxLoadCapacity() {
        planes.sort(Comparator.comparingInt(Plane::getMaxLoadCapacity));
    }

    private void print(Collection<? extends Plane> collection) {
        for (Plane plane: planes){
            System.out.println(plane);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return planes.equals(airport.planes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planes);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "planes=" + planes +
                '}';
    }
}
