package cm.cryptoapiandscrapper.cryptoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MACDBullish implements Divergence {

    private String rank;
    private String name;
    private String symbol;
    private String price;
    private String last24H;
    private String last7D;
    private String vol;
    private String marketCap;
    private String CIRCSUPPLY;
}
