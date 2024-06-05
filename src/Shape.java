public class Shape {
    private int id;
    private String name;
    private V3[][] shape2DArray;

    public Shape(V3[][] shape2DArray) {
        this.shape2DArray = shape2DArray;
    }

    public Shape(String name, V3[][] shape2DArray) {
        this.name = name;
        this.shape2DArray = shape2DArray;
    }

    public Shape(int id, String name, V3[][] shape2DArray) {
        this.id = id;
        this.name = name;
        this.shape2DArray = shape2DArray;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public V3[][] getShape2DArray() {
        return shape2DArray;
    }

    public void setShape2DArray(V3[][] shape2DArray) {
        this.shape2DArray = shape2DArray;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
