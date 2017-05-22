package by.hospital.domain.comparator;

import by.hospital.domain.Prescription;

import java.util.Comparator;

/**
 * Created by Admin on 20.05.2017.
 */
public class SortPrescriptionByExec implements Comparator<Prescription> {
    @Override
    public int compare(Prescription prescription1, Prescription prescription2) {
        int compile1 = prescription1.getQuantity() - prescription1.getCompleted();
        int compile2 = prescription2.getQuantity() - prescription2.getCompleted();
        if (compile1 > compile2) {
            return 1;
        } else {
            if (compile1 < compile2) {
                return -1;
            } else return 0;
        }
    }
}
