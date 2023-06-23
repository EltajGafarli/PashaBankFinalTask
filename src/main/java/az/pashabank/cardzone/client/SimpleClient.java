package az.pashabank.cardzone.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimpleClient {

    public static String parseCashAmount(String cash) {
        int i = cash.indexOf(':') + 1;
        StringBuilder sb = new StringBuilder();
        while (i < cash.length() - 1) {
            sb.append(cash.charAt(i));
            i++;
        }

        return sb.toString();
    }

    public double calculateCashback(double cash) {
        String url = "https://cardzone-cashback-api-c2f5b8105e2b.herokuapp.com/api/cashback?transactionAmount=";
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> forEntity = template.getForEntity(url + cash, String.class);
        String cashStr = forEntity.getBody();
        System.out.println("cashStr1: " + cashStr);
        return cashStr == null ? 0.0 : Double.parseDouble(parseCashAmount((cashStr)));
    }

}
