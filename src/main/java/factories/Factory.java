package factories;


public interface Factory<P, E, I> {
    P create(E productEnum, I... optionalInputs);
}
