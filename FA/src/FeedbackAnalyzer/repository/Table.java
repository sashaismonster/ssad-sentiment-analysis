package FeedbackAnalyzer.repository;

import FeedbackAnalyzer.dto.AbstractDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Table<T extends AbstractDto> {

    private final List<T> rowList = new ArrayList<>();

    /**
     * Add new row to table
     *
     * @param row - Object of type T to write in new row of Table
     * @return identifier for created row in Table
     */
    public UUID add(T row) {
        UUID id = row.getId() != null ? row.getId() : UUID.randomUUID();
        row.setId(id);
        this.rowList.add(row);
        return id;
    }

    /**
     * Get row from table by identifier
     *
     * @param id - identifier of row
     * @return Object of type T from Table
     */
    public T get(UUID id) {
        for (T row : this.rowList) {
            if (row.getId().equals(id)) {
                return row;
            }
        }
        return null;
    }

    /**
     * Get all rows from Table
     *
     * @return List of Object of type T
     */
    public List<T> getList() {
        return this.rowList;
    }

    /**
     * Initialize or add rows in Table
     *
     * @param rowList - list of object of type T, which need to add to Table
     */
    public void initialize(List<T> rowList) {
        rowList.forEach(row -> row.setId(row.getId() == null ? UUID.randomUUID() : row.getId()));
        this.rowList.addAll(rowList);
    }

}
