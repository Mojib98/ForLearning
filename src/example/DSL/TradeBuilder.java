package example.DSL;

/**
 * @author M.R Khabireh
 * Date: 17/08/2026
 * Time: 14:07
 */
public class TradeBuilder {
    private final MethodChainingOrderBuilder builder;
    public final Trade trade = new Trade();
    protected TradeBuilder(MethodChainingOrderBuilder builder,
                           Trade.Type type, int quantity) {
        this.builder = builder;
        trade.setType( type );
        trade.setQuantity( quantity );
    }
    public StockBuilder stock(String symbol) {
        return new StockBuilder(builder, trade, symbol);
    }
}
