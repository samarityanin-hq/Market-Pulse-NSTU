package academy.backend.interactive.seminar_3.animals;

public abstract class Parrot extends Animal {
    @Override
    public int getLegCount() {
        return 2;
    }

    public abstract String size();
}
