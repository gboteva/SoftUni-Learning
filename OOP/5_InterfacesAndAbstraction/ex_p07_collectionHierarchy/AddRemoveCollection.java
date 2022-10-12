package ex_p07_collectionHierarchy;

public class AddRemoveCollection extends Collection implements AddRemovable, Addable {

    public AddRemoveCollection() {
        super();
    }

    @Override
    public String remove() {
        return super.getItems().remove(super.getItems().size() - 1);
    }


    @Override
    public int add(String string) {
        super.getItems().add(0, string);
        return super.getItems().indexOf(string);
    }
}
