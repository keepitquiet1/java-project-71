package hexlet.code;

public final class Value {
    private final String status;
    private final Object value;
    private Object newValue;

    public String getStatus() {
        return status;
    }

    public Object getValue() {
        return value;
    }

    public Object getNewValue() {
        return newValue;
    }

    public Value(String status, Object value, Object newValue) {
        this.status = status;
        this.value = value;
        this.newValue = newValue;
    }

    public Value(String status, Object value) {
        this.status = status;
        this.value = value;
    }


}
