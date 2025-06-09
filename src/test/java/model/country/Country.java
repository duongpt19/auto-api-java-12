package model.country;

import java.util.Objects;

public class Country {
    private String name;
    private String code;
    private float gdp;

    public Country(){
    }

    public Country(String name, String code, float gdp) {
        this.name = name;
        this.code = code;
        this.gdp = gdp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getGdp() {
        return gdp;
    }

    public void setGdp(float gdp) {
        this.gdp = gdp;
    }

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
