package ch.silasberger.cardswebproto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "white_cards")
public class WhiteCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    private int id;

    @Column
    private String value;

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
}
