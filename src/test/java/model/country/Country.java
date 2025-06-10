package model.country;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private String name;
    private String code;
    private float gdp;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) && Objects.equals(code, country.code) && gdp == country.gdp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, gdp);
    }

    @Override
    public String toString() {
        return String.format("Country {name: %s, code: %s, gdp: %s}", this.name, this.code, this.gdp);
    }
}
