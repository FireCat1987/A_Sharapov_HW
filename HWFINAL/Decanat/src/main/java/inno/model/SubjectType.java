package inno.model;

public enum  SubjectType {
    OTHER("Общая наука"),
    ECONOMY("Экономика"),
    SCIENCE("Наука"),
    POLITICS("Политика");

    String description;

    SubjectType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
