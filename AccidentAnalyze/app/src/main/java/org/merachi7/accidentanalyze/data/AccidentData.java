package org.merachi7.accidentanalyze.data;

public class AccidentData {
    private String construction_species_big_;
    private String construction_species_medium_;
    private String facility_big_;
    private String facility_medium_;
    private String facility_small_;
    private String hazard_object_big_;
    private String hazard_object_medium_;
    private String hazard_position_big_;
    private String hazard_position_medium_;
    private String hazard_position_small_;
    private String hazard_position_code_medium_;
    private String process_;
    private String damage_material_;
    private String damage_human_;
    private String accident_reason_;
    private String possibility_;
    private String severity_;
    private String method_design_;
    private String method_construction_;
    private String rowid_;

    public String getConstruction_species_big() {return construction_species_big_;}
    public String getConstruction_species_medium(){return construction_species_medium_;}
    public String getFacility_big(){return facility_big_;}
    public String getFacility_medium(){return facility_medium_;}
    public String getFacility_small(){return facility_small_;}
    public String getHazard_object_big(){return hazard_object_big_;}
    public String getHazard_object_medium(){return hazard_object_medium_;}
    public String getHazard_position_big(){return hazard_position_big_;}
    public String getHazard_position_medium(){return hazard_position_medium_;}
    public String getHazard_position_small(){return hazard_position_small_;}
    public String getHazard_position_code_medium(){return hazard_position_code_medium_;}
    public String getProcess(){return process_;}
    public String getDamage_material(){return damage_material_;}
    public String getDamage_human(){return damage_human_;}
    public String getAccident_reason(){return accident_reason_;}
    public String getPossibility(){return possibility_;}
    public String getSeverity(){return severity_;}
    public String getMethod_design(){return method_design_;}
    public String getMethod_construction_(){return method_construction_;}

    public void setConstruction_species_big(String construction_species_big){this.construction_species_big_ = construction_species_big;}

    public void setConstruction_species_medium(String construction_species_medium){this.construction_species_medium_ = construction_species_medium;}

    public void setFacility_big(String facility_big){this.facility_big_ = facility_big;}

    public void setFacility_medium(String facility_medium){this.facility_medium_ = facility_medium;}

    public void setFacility_small(String facility_small){this.facility_small_ = facility_small;}

    public void setHazard_object_big(String hazard_object_big){this.hazard_object_big_ = hazard_object_big; }

    public void setHazard_object_medium(String hazard_object_medium){this.hazard_object_medium_ = hazard_object_medium;}

    public void setHazard_position_big(String hazard_position_big){this.hazard_position_big_ = hazard_position_big;}

    public void setHazard_position_medium(String hazard_position_medium){this.hazard_position_medium_ = hazard_position_medium;}

    public void setHazard_position_small(String hazard_position_small){this.hazard_position_small_ = hazard_position_small;}

    public void setHazard_position_code_medium(String hazard_position_code_medium){this.hazard_position_code_medium_ = hazard_position_code_medium;}

    public void setProcess(String process){this.process_ = process;}

    public void setDamage_material(String damage_material){this.damage_material_ = damage_material;}

    public void setDamage_human(String damage_human){this.damage_human_ = damage_human;}

    public void setAccident_reason(String accident_reason){this.accident_reason_ = accident_reason;}

    public void setPossibility(String possibility){this.possibility_ = possibility;}

    public void setSeverity(String severity){this.severity_ = severity;}

    public void setMethod_design(String method_design){this.method_design_ = method_design;}

    public void setMethod_construction_(String method_construction){this.method_construction_ = method_construction;
    }
    public String getRowid() {
        return rowid_;
    }

    public void setRowid(String rowid){this.rowid_ = rowid;}
}