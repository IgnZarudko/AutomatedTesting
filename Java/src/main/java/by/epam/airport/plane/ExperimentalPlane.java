package by.epam.airport.plane;

import by.epam.airport.planetype.ConfidentialityLevel;
import by.epam.airport.planetype.ExperimentalType;

public class ExperimentalPlane extends Plane{

    private ExperimentalType experimentalType;
    private ConfidentialityLevel confidentialityLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance,
                             int maxLoadCapacity, ExperimentalType type, ConfidentialityLevel confidentialityLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.experimentalType = type;
        this.confidentialityLevel = confidentialityLevel;
    }

    public ConfidentialityLevel getConfidentialityLevel(){
        return confidentialityLevel;
    }

    public void setConfidentialityLevel(ConfidentialityLevel confidentialityLevel){
        this.confidentialityLevel = confidentialityLevel;
    }

    public ExperimentalType getExperimentalType(){
        return experimentalType;
    }

    public void setExperimentalType(ExperimentalType experimentalType){
        this.experimentalType = experimentalType;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "experimentalPlane{" +
                "model='" + getModel() + '\'' +
                '}';
    }
}
