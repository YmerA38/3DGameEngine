public class Key {
    private int key1;
    private int key2;

    public Key(int key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public int getKey1() {
        return key1;
    }

    public void setKey1(int key1) {
        this.key1 = key1;
    }

    public int getKey2() {
        return key2;
    }

    public void setKey2(int key2) {
        this.key2 = key2;
    }

    @Override
    public String toString(){
        return "Key1: "+key1+" Key2: "+key2;
    }
}
