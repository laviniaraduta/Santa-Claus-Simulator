package fileio;

import java.util.List;

public class GameDataInput {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialDataInput initialData;
    private List<AnnualChangeInput> annualChanges;

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public InitialDataInput getInitialData() {
        return initialData;
    }

    public void setInitialData(InitialDataInput initialData) {
        this.initialData = initialData;
    }

    public List<AnnualChangeInput> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(List<AnnualChangeInput> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
