package entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Item {
    private int id;
    private String title;
    private int code;
    private String producer;
    private LocalDateTime dateOfLastUpdate;

    public Item(int id, String title, int code, String producer, LocalDateTime dateOfLastUpdate) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.producer = producer;
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public LocalDateTime getDateOfLastUpdate() {
        return dateOfLastUpdate;
    }

    public void setDateOfLastUpdate(LocalDateTime dateOfLastUpdate) {
        this.dateOfLastUpdate = dateOfLastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getId() == item.getId() && getCode() == item.getCode() && Objects.equals(getTitle(), item.getTitle()) && Objects.equals(getProducer(), item.getProducer()) && Objects.equals(getDateOfLastUpdate(), item.getDateOfLastUpdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getCode(), getProducer(), getDateOfLastUpdate());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", code=" + code +
                ", producer='" + producer + '\'' +
                ", dateOfLastUpdate=" + dateOfLastUpdate +
                '}';
    }

}
