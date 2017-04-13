package by.hospital.domain;

import by.hospital.domain.enumeration.TypePrescription;

/**
 * Created by Pasha on 12.04.2017.
 */
public class PrescriptionType implements Entity <Integer> {
    private int prescriptionTypeID;
    private TypePrescription typePrescription;

    @Override
    public Integer getPrimaryKey() {
        return prescriptionTypeID;
    }

    public TypePrescription getTypePrescripion() {
        return typePrescription;
    }

    public void setTypePrescripion(String typePrescripion) {
        this.typePrescription = TypePrescription.valueOf(typePrescripion);
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        prescriptionTypeID=primaryKey;
    }

    public String toString(){
        return typePrescription.getName();
    }
}
