package Proj.library.model;

public enum Genre {
    FANTASY("Фантастика"),
    SCIENCE_FICTION("Научная Фантастика"),
    DRAMA("Драма"),
    NOVEL("Роман");

    private final String genreTextDisplay;

    Genre(String text) {
        this.genreTextDisplay = text;
    }

    public String getGenreTextDisplay() {
        return genreTextDisplay;
    }
}
