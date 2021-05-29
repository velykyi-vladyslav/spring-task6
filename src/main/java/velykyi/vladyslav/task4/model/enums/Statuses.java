package velykyi.vladyslav.task4.model.enums;

public enum Statuses {
    CREATED("created"),
    READY("ready"),
    CLOSED("closed"),
    CANCELED("canceled"),
    ONGOING("ongoing");

    private final String url;

    Statuses(String url) {
        this.url = url;
    }

    public String get() {
        return url;
    }
}
