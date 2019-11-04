package tic1.commons.transfers;

public class SeatDTO {
    private long row;
    private long column;
    private long sala_id;


    public SeatDTO() {
    }

    public long getRow() {
        return row;
    }

    public void setRow(long row) {
        this.row = row;
    }

    public long getColumn() {
        return column;
    }

    public void setColumn(long column) {
        this.column = column;
    }

    public long getSala_id() {
        return sala_id;
    }

    public void setSala_id(long sala_id) {
        this.sala_id = sala_id;
    }
}
