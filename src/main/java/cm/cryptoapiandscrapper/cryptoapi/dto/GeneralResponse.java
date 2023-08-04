package cm.cryptoapiandscrapper.cryptoapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponse<T> {
    private int code;
    private String message;
    private T body;
}
