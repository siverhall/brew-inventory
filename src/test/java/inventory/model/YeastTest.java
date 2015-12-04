package inventory.model;

public class YeastTest extends JSONTest<Yeast> {

    @Override
    protected Class<Yeast> getClazz() {
        return Yeast.class;
    }

    @Override
    protected String getFixture() {
        return "fixtures/yeast.json";
    }

    @Override
    protected Yeast createObject() {
        Yeast yeast = new Yeast();
        yeast.setId(1);
        yeast.setName("US-05");
        yeast.setYeastType(Yeast.YeastType.DRY);
        yeast.setAmount(1);
        return yeast;
    }
}
