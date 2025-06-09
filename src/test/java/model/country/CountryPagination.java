package model.country;

import java.util.List;
import java.util.Objects;

public class CountryPagination {
    private int page;
    private int size;
    private int total;
    private List<Country> data;

    CountryPagination(){}

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Country> getData() {
        return data;
    }

    public void setData(List<Country> data) {
        this.data = data;
    }
}
