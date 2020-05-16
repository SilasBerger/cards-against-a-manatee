package ch.silasberger.cardswebproto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "black_cards")
public class BlackCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private int id;

    @Column
    private String value;

    @Column
    private int pick;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPick() {
        return pick;
    }

    public void setPick(int pick) {
        this.pick = pick;
    }
}
