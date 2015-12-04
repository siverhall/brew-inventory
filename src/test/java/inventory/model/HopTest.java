package inventory.model;

public class HopTest extends JSONTest<Hop> {

    @Override
    protected Class<Hop> getClazz() {
        return Hop.class;
    }

    @Override
    protected String getFixture() {
        return "fixtures/hop.json";
    }

    @Override
    protected Hop createObject() {
        Hop hop = new Hop();
        hop.setId(1);
        hop.setAlpha(12.0);
        hop.setName("Amarillo");
        hop.setAmount(200);
        hop.setHopType(Hop.HopType.PELLET);
        return hop;
    }
}
