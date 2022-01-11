package fileio;

import java.util.List;

public final class GameDataInput {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialDataInput initialData;
    private List<AnnualChangeInput> annualChanges;

    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public Double getSantaBudget() {
        return santaBudget;
    }

    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public InitialDataInput getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialDataInput initialData) {
        this.initialData = initialData;
    }

    public List<AnnualChangeInput> getAnnualChanges() {
        return annualChanges;
    }

    public void setAnnualChanges(final List<AnnualChangeInput> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
