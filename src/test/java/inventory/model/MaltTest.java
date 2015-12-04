package inventory.model;

public class MaltTest extends JSONTest<Malt> {
    @Override
    protected Class<Malt> getClazz() {
        return Malt.class;
    }

    @Override
    protected String getFixture() {
        return "fixtures/malt.json";
    }

    @Override
    protected Malt createObject() {
        Malt malt = new Malt();
        malt.setId(1);
        malt.setName("Pale ale malt");
        malt.setAmount(10);
        return malt;
    }


}
