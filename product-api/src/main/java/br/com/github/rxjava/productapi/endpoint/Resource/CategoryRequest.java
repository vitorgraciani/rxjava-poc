package br.com.github.rxjava.productapi.endpoint.Resource;

public class CategoryRequest {

    private String name;

    private String description;

    public CategoryRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
