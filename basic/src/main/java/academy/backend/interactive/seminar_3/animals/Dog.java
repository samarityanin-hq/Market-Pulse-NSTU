package academy.backend.interactive.seminar_3.animals;

public abstract class Dog extends Animal {
    @Override
    public int getLegCount() {
        return 4;
    }

    abstract boolean isGoodBoy();
}
