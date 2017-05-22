package by.hospital.domain.comparator;

import by.hospital.domain.SickList;

import java.util.Comparator;

/**
 * Created by Admin on 20.05.2017.
 */
public class SortSickListByRoom implements Comparator<SickList> {
    @Override
    public int compare(SickList sickList1, SickList sickList2) {
        return sickList1.getRoom().compareTo(sickList2.getRoom());
    }
}
