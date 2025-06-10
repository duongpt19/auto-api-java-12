package model.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryPagination {
    private int page;
    private int size;
    private int total;
    private List<Country> data;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CountryPagination that = (CountryPagination) o;
        return page == that.page && size == that.size && total == that.total && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, size, total, data);
    }


}
