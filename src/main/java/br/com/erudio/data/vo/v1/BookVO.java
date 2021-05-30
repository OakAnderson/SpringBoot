package br.com.erudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonPropertyOrder({"id", "title", "author", "price", "launchDate"})
public class BookVO extends RepresentationModel<BookVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private OffsetDateTime launchDate;
    private String author;
    private double price;
    private String title;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public OffsetDateTime getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(OffsetDateTime launchDate) {
        this.launchDate = launchDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookVO bookVO = (BookVO) o;
        return Double.compare(bookVO.price, price) == 0 && Objects.equals(key, bookVO.key) && Objects.equals(launchDate, bookVO.launchDate) && Objects.equals(author, bookVO.author) && Objects.equals(title, bookVO.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, launchDate, author, price, title);
    }
}
