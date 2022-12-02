package org.merachi7.accidentanalyze.data;

public class AccidentData {
    private String construction_species_;
    private String process_;
    private String species_big_;
    private String species_small_;
    private String accident_species_;
    private String possibility_;
    private String severity_;
    private String rowid_;

    public void setConstruction_species(String construction_species) {
        this.construction_species_ = construction_species;
    }

    public void setProcess(String process) {
        this.process_ = process;
    }

    public void setSpecies_big(String species_big) {
        this.species_big_ = species_big;
    }

    public void setSpecies_small(String species_small) {
        this.species_small_ = species_small;
    }

    public void setAccident_species(String accident_species) {
        this.accident_species_ = accident_species;
    }

    public void setPossibility(String possibility) {
        this.possibility_ = possibility;
    }

    public void setSeverity(String severity) {
        this.severity_ = severity;
    }

    public void setRowid(String rowid) {
        this.rowid_ = rowid;
    }

    public String getConstruction_species() {
        return construction_species_;
    }

    public String getProcess() {
        return process_;
    }

    public String getSpecies_big() {
        return species_big_;
    }

    public String getSpecies_small() {
        return species_small_;
    }

    public String getAccident_species() {
        return accident_species_;
    }

    public String getPossibility() {
        return possibility_;
    }

    public String getSeverity() {
        return severity_;
    }

    public String getRowid() {
        return rowid_;
    }
}